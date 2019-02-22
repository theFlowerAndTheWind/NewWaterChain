package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CreateOrderResponseBean;
import com.shuzijieshui.www.waterchain.beans.FactoryServiceResponseBean;
import com.shuzijieshui.www.waterchain.beans.TotalPriceResponseBean;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderReqParams;
import com.shuzijieshui.www.waterchain.contract.presenter.CreateOrderPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.TotalPricePresenter;
import com.shuzijieshui.www.waterchain.contract.view.CreateOrderViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.TotalPriceViewImpl;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.ui.widget.popup.CreateOrderListPopupWindow;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.TimeUtils;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:确认订单
 */

public class ConfirmOrderActivity extends BaseActivity implements TotalPriceViewImpl, CreateOrderViewImpl {

    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.img)
    ImageView service_img;
    @BindView(R.id.tv_service_title)
    TextView tv_service_title;
    @BindView(R.id.tv_services_desc)
    TextView tv_serivces_desc;
    @BindView(R.id.wash_detail_tv)
    TextView wash_detail_tv;
    @BindView(R.id.wash_detail_count)
    TextView wash_detail_count;
    @BindView(R.id.img_pay_cate_1)
    ImageView fullPayImg;
    @BindView(R.id.img_pay_cate_2)
    ImageView combinedPayImg;
    @BindView(R.id.payChannel_wx)
    ImageView wxPayImg;
    @BindView(R.id.payChannel_zfb)
    ImageView zfbPayImg;
    @BindView(R.id.order_detail)
    Button orderDetail;
    @BindView(R.id.btn_create_order)
    Button btnCreateOrder;
    @BindView(R.id.cb_agreement)
    CheckBox cbAgreement;
    @BindView(R.id.rl_trans_infos)
    RelativeLayout rlTransInfos;
    @BindView(R.id.tv_receiver_name)
    TextView tvReceiverName;
    @BindView(R.id.tv_receiver_tel)
    TextView tvReceiverTel;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.tv_info_txt)
    TextView tvInfoTxt;

    private AlertChainDialog alertChainDialog;
    private AlertChainDialog alertChainPayDialog;
    private TotalPricePresenter totalPricePresenter;
    private CreateOrderPresenter createOrderPresenter;
    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> washFatoryCageGory = new ArrayList<>();
    private String payType = "1";//pay_type	支付渠道	字符串(string)1支付宝 2微信
    private String countDown = "00分00秒";
    private int payCate = 1;//支付类型   1全额支付   2组合支付
    private String tradeDetailStr = "";
    private int fsid = 0;
    private int totalCount = 0;//洗涤物品总数
    private CreateOrderListPopupWindow popupWindow;
    private CountDownTimer TimeCount;
    private boolean isAgree = true, runningCode = false;
    private int isCanPay = 0;
    private CreateOrderReqParams params = new CreateOrderReqParams();
    // 声明平移动画
    private TranslateAnimation animation;

    private MyTimeHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);

        totalPricePresenter = new TotalPricePresenter();
        totalPricePresenter.attachView(this);
        createOrderPresenter = new CreateOrderPresenter();
        createOrderPresenter.attachView(this);

        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getData(intent);

    }


    private void initView() {
        tvTitleCenter.setText("确认订单");
        initPayTimeDialog();
        handler = new MyTimeHandler(this);
        if (payType.equals("1")) {
            wxPayImg.setVisibility(View.GONE);
            zfbPayImg.setVisibility(View.VISIBLE);
        } else if (payType.equals("2")) {
            wxPayImg.setVisibility(View.VISIBLE);
            zfbPayImg.setVisibility(View.GONE);
        }
        if (payCate == 1) {
            fullPayImg.setVisibility(View.VISIBLE);
            combinedPayImg.setVisibility(View.GONE);
        } else if (payCate == 2) {
            fullPayImg.setVisibility(View.GONE);
            combinedPayImg.setVisibility(View.VISIBLE);
        }
        cbAgreement.setChecked(true);
        cbAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isAgree = isChecked;
                if (!isAgree) {
                    btnCreateOrder.setEnabled(false);
                } else if (isAgree && isCanPay == 1) {
                    btnCreateOrder.setEnabled(true);
                }
            }
        });
        getData(getIntent());
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_order);
    }

    @OnClick({R.id.left_ll, R.id.order_detail, R.id.btn_create_order, R.id.wash_delivery_rl, R.id.wash_demand_rl, R.id.fullPayment,
            R.id.combinedPayment, R.id.pay_channel_wx_rl, R.id.pay_channel_zfb_rl,R.id.tv_agreement})
    public void onClick(View v) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.left_ll:
//                exitPay();
                goBack(v);
                break;
            case R.id.order_detail:
                if (totalCount > 0) {
                    showPopupWindow();
                } else {
                    ToastUtils.showCustomToastMsg("请选择洗涤需求", 150);
                }
                break;
            case R.id.btn_create_order:
                if (payCate == 2 && isCanPay != 1) {
                    ToastUtils.showCustomToastMsg("您的可用水方不足", 150);
                    return;
                }
                if (!isAgree) {
                    ToastUtils.showCustomToastMsg("请阅读并同意《节水链平台用户协议》", 150);
                    return;
                }
                params.setFsid(fsid);
                params.setTrade_detail(tradeDetailStr);
                params.setPay_cate(payCate);
                params.setPay_type(payType);

                btnCreateOrder.setEnabled(true);
                createOrderPresenter.createOrder(ConfirmOrderActivity.this, params);
                break;
            case R.id.wash_delivery_rl://配送信息
                bundle.putInt("jumpAction", R.id.wash_delivery_rl);
                bundle.putParcelable("params", params);
                intent.putExtras(bundle);
                intent.setClass(ConfirmOrderActivity.this, DistributionInfoActivity.class);
                startActivity(intent);

                break;
            case R.id.wash_demand_rl://洗涤需求
                bundle.putParcelableArrayList("washFatoryCageGory", washFatoryCageGory);
                for (FactoryServiceResponseBean.WashFatoryCageGory entry : washFatoryCageGory) {
                    LogUtils.e(entry.getC_name() + " ***confirm send---  " + entry.getPiceCount());
                }
                intent.putExtras(bundle);
                intent.setClass(ConfirmOrderActivity.this, WashDemandActivity.class);
                startActivity(intent);
                break;
            case R.id.combinedPayment://组合支付
                fullPayImg.setVisibility(View.GONE);
                combinedPayImg.setVisibility(View.VISIBLE);
                payCate = 2;
                if (alertChainDialog != null) {
                    alertChainDialog.builder().setCancelable(false);
                    alertChainDialog.setTitle("组合支付")
                            .setMsg("组合支付，JSL按前一交易日的收盘价的1.5倍计算")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();

                }
                if (totalCount > 0 && !TextUtils.isEmpty(tradeDetailStr)) {
                    reqTotalPrice();
                }
                break;
            case R.id.fullPayment://全额支付
                fullPayImg.setVisibility(View.VISIBLE);
                combinedPayImg.setVisibility(View.GONE);
                payCate = 1;
                if (totalCount > 0 && !TextUtils.isEmpty(tradeDetailStr)) {
                    reqTotalPrice();
                }
                break;
            case R.id.pay_channel_wx_rl:
                wxPayImg.setVisibility(View.VISIBLE);
                zfbPayImg.setVisibility(View.GONE);
                payType = "2";
                break;
            case R.id.pay_channel_zfb_rl:
                wxPayImg.setVisibility(View.GONE);
                zfbPayImg.setVisibility(View.VISIBLE);
                payType = "1";
                break;

            case R.id.tv_agreement:
                Intent i = new Intent(this, WebViewActivity.class);
                i.setClass(this, WebViewActivity.class);
                i.putExtra(WebViewActivity.WEBVIEW_ACT_TITLE,"协议");
                i.putExtra(WebViewActivity.GET_URL_TYPE,"contract");
                startActivity(i);
                break;
            default:
                break;
        }
    }

    private void exitPay() {
        if (alertChainPayDialog != null) {
            //dialog msg消息体比较特殊 请勿更改！！！
            alertChainPayDialog.builder().setCancelable(false);
            alertChainPayDialog.setTitle("确认离开支付页面").setMsg("您的订单在 " + countDown + " 后未支付将会被取消，请尽快支付。")
                    .setNegativeButton("确认离开", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimeCount.cancel();
                            hideSoftKeyboard(ConfirmOrderActivity.this);
                            finish();
                        }
                    }).setPositiveButton("继续支付", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show();
            countDownPayTime();
        }
    }

    public static class MyTimeHandler extends Handler {

        WeakReference<ConfirmOrderActivity> weakReference;

        public MyTimeHandler(ConfirmOrderActivity activity) {
            weakReference = new WeakReference<ConfirmOrderActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final ConfirmOrderActivity activity = weakReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case 1:
                    if (activity.alertChainPayDialog.isShow()) {
                        activity.alertChainPayDialog.setMsg("您的订单在 " + activity.countDown + " 后未支付将会被取消，请尽快支付。");
                        activity.handler.sendEmptyMessageDelayed(1, 1000);

                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void initPayTimeDialog() {
        alertChainDialog = new AlertChainDialog(this);
        alertChainPayDialog = new AlertChainDialog(this);
    }

    public void countDownPayTime() {
        TimeCount = new CountDownTimer(30 * 60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                runningCode = true;
                countDown = TimeUtils.getToMSTime((int) (millisUntilFinished / 1000));
                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFinish() {
                runningCode = false;
                TimeCount.onFinish();
                alertChainPayDialog.dismiss();
            }

        };

        if (runningCode) {
            return;
        } else {
            TimeCount.start();
        }

    }

    private void initPopupWindow(TotalPriceResponseBean totalPriceResponseBean) {
        if (popupWindow == null) {
            popupWindow = new CreateOrderListPopupWindow(ConfirmOrderActivity.this, totalPriceResponseBean);
        }
    }

    private void showPopupWindow() {
        View parent = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.activity_confirm_order, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }


    @Override
    public void onReNetRefreshData(int viewId) {

    }

    public void getData(Intent intent) {


        if (intent == null) {
            return;
        }
        switch (intent.getIntExtra("class", -1)) {
            case 1://FactoryServiceActivity
                FactoryServiceResponseBean.WashFatoryDetail washFatoryDetail = intent.getParcelableExtra("WashFatoryDetail");
                if (washFatoryDetail == null) {
                    return;
                }
                fsid = washFatoryDetail.getId();
                GlidImageManager.getInstance().loadImageView(this, washFatoryDetail.getImg(), service_img, R.drawable.ic_default_image);

                washFatoryCageGory = intent.getParcelableArrayListExtra("WashFatoryCageGoryList");

                tv_service_title.setText(washFatoryDetail.getS_name());
                tv_serivces_desc.setText(washFatoryDetail.getDescription());

                break;
            case 2://WashDemandActivity
                final ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> extraList = intent.getParcelableArrayListExtra("washFatoryCageGory");
                tradeDetailStr = "";
                totalCount = 0;
                washFatoryCageGory.clear();
                washFatoryCageGory.addAll(extraList);
                for (FactoryServiceResponseBean.WashFatoryCageGory entry : washFatoryCageGory) {
                    LogUtils.e(entry.getC_name() + "  ***confirm receive--- " + entry.getPiceCount());
                }
                String trade_detail_tv = "";
                String fscid = "";
                String c_name = "";
                int piceCount = 0;
                for (FactoryServiceResponseBean.WashFatoryCageGory entry : washFatoryCageGory) {
                    c_name = entry.getC_name();
                    fscid = entry.getFscid() + "";
                    piceCount = entry.getPiceCount();
                    if (piceCount > 0) {
                        trade_detail_tv += c_name + "*" + piceCount + "  ";
                        tradeDetailStr += fscid + "_" + piceCount + "||";
                        totalCount += piceCount;
                    }
                }
                if (tradeDetailStr.length() > 2)
                    tradeDetailStr = tradeDetailStr.substring(0, tradeDetailStr.length() - 2);
                if (totalCount > 0) {
                    wash_detail_count.setText("共" + totalCount + "件");
                    wash_detail_tv.setText(trade_detail_tv);
                }
                reqTotalPrice();
                break;
            case 3://WashAddressActivity
                CreateOrderReqParams extraParams = intent.getParcelableExtra("params");
                if (extraParams != null) {
                    String contact_name = extraParams.getContact_name();
                    String contact_tel = extraParams.getContact_tel();
                    int express = extraParams.getExpress();
                    params.setExpress(express);
                    params.setContact_name(contact_name);
                    params.setContact_tel(contact_tel);
                    params.setProvince(extraParams.getProvince());
                    params.setCity(extraParams.getCity());
                    params.setAddress(extraParams.getAddress());
                    params.setPickup_time(extraParams.getPickup_time());
                    if (TextUtils.isEmpty(contact_name) && TextUtils.isEmpty(contact_tel) && express != 1 && express != 2) {
                        rlTransInfos.setVisibility(View.GONE);
                    } else {
                        rlTransInfos.setVisibility(View.VISIBLE);
                        tvReceiverName.setText(contact_name);
                        tvReceiverTel.setText(contact_tel);
                        if (express == 1) {
                            tvExpress.setText("企业配送");
                        } else if (express == 2) {
                            tvExpress.setText("自取");
                        }
                    }
                    if (TextUtils.isEmpty(extraParams.getProvince())
                            && TextUtils.isEmpty(extraParams.getCity())
                            && TextUtils.isEmpty(extraParams.getAddress())) {

                    } else {
                        tvInfoTxt.setText(extraParams.getProvince() + extraParams.getCity() + extraParams.getAddress());
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

//            exitPay();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (totalPricePresenter != null) {
            totalPricePresenter.detachView();
        }
        if (createOrderPresenter != null) {
            createOrderPresenter.detachView();
        }
    }

    /**
     * 洗涤需求填写后执行
     * 若用户没有选择支付方式，选择支付方式后再次执行
     */
    private void reqTotalPrice() {
        if (totalPricePresenter == null) {
            totalPricePresenter = new TotalPricePresenter();
            totalPricePresenter.attachView(this);
        }
        if (payCate == 1 || payCate == 2) {
            if (totalCount > 0 && !TextUtils.isEmpty(tradeDetailStr)) {
                totalPricePresenter.getTotalPrice(this, payCate, tradeDetailStr);
                params.setPay_cate(payCate);
                params.setTrade_detail(tradeDetailStr);
                params.setFsid(fsid);
                params.setPay_cate(payCate);
            } else {
                ToastUtils.showCustomToastMsg("您还没有选择洗涤需求", 150);
            }
        } else {
            ToastUtils.showCustomToastMsg("请选择支付方式", 150);
        }
    }


    @Override

    public void onTotalPriceSuccess(TotalPriceResponseBean totalPriceResponseBean) {
        if (totalPriceResponseBean != null) {
            initPopupWindow(totalPriceResponseBean);
            isCanPay = totalPriceResponseBean.getCan_pay();
            String payPrice = totalPriceResponseBean.getPay_price();
            String payJsl = totalPriceResponseBean.getPay_jsl();
            String price = "¥" + payPrice;
            if (!TextUtils.isEmpty(payJsl) && Float.valueOf(payJsl) > 0) {
                price = price + "+" + payJsl + "水方";
            }
            orderDetail.setText(price);

            if (payCate == 2 && isCanPay == 0) {
                btnCreateOrder.setEnabled(false);
            } else if (isCanPay == 1 && isAgree) {
                btnCreateOrder.setEnabled(true);
            }
        }
    }

    @Override
    public void onTotalPriceFailed(String msg) {
        ToastUtils.showCustomToastMsg(msg, 150);
    }

    @Override
    public void onCreateOrderSuccess(CreateOrderResponseBean createOrderResponseBean) {
        ToastUtils.showCustomToast("创建订单成功", 1);
        startActivity(new Intent(ConfirmOrderActivity.this, PaySuceessActivity.class));
        //todo 支付宝、微信支付
    }

    @Override
    public void onCreateOrderFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
