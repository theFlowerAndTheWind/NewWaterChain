package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.CreateOrderListBean;
import com.quanminjieshui.waterchain.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterchain.beans.FactoryServiceResponseBean;
import com.quanminjieshui.waterchain.beans.TotalPriceResponseBean;
import com.quanminjieshui.waterchain.beans.request.CreateOrderReqParams;
import com.quanminjieshui.waterchain.contract.presenter.CreateOrderPresenter;
import com.quanminjieshui.waterchain.contract.presenter.TotalPricePresenter;
import com.quanminjieshui.waterchain.contract.view.CreateOrderViewImpl;
import com.quanminjieshui.waterchain.contract.view.TotalPriceViewImpl;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.ui.widget.CreateOrderListPopupWindow;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.TimeUtils;
import com.quanminjieshui.waterchain.utils.Util;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:确认订单
 */

public class ConfirmOrderActivity extends BaseActivity implements TotalPriceViewImpl,CreateOrderViewImpl{

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

    private AlertChainDialog alertChainDialog;
    private AlertChainDialog alertChainPayDialog;
    private String [] trade_detail = {};
    private TotalPricePresenter totalPricePresenter;
    private CreateOrderPresenter createOrderPresenter;
    private ArrayList<FactoryServiceResponseBean.WashFatoryCageGory> washFatoryCageGory = new ArrayList<>();
    private String payType = "",payChannel = "",countDown = "00分00秒";
    private CreateOrderListPopupWindow popupWindow;
    private List<CreateOrderListBean> createOrderListBeans = new ArrayList<>();
    private CountDownTimer TimeCount;
    private boolean isChecked = true,runningCode = false;
    // 声明平移动画
    private TranslateAnimation animation;

    private MyTimeHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this,titleBar);

        totalPricePresenter = new TotalPricePresenter();
        totalPricePresenter.attachView(this);
        createOrderPresenter = new CreateOrderPresenter();
        createOrderPresenter.attachView(this);

        initView();
    }

    private void initView() {
        tvTitleCenter.setText("确认下单");
        initPayTimeDialog();

        handler = new MyTimeHandler(this);
        getData();
        initPopupWindow();//初始化poppup
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_order);
    }

    @OnClick({R.id.left_ll,R.id.order_detail,R.id.create_order,R.id.wash_delivery_rl,R.id.wash_demand_rl,R.id.fullPayment,
            R.id.combinedPayment,R.id.pay_channel_wx_rl,R.id.pay_channel_zfb_rl})
    public void onClick(final View v){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.left_ll:
                exitPay();
                break;
            case R.id.order_detail:
                View parent = LayoutInflater.from(ConfirmOrderActivity.this).inflate(R.layout.activity_confirm_order, null);
                popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
                break;
            case R.id.create_order:
                CreateOrderReqParams bean = new CreateOrderReqParams();
                bean.setCity("北京");
                bean.setAddress("海淀区");
                bean.setContact_name("何望红");
                bean.setContact_tel("13718478437");
//                bean.setExpress();
                createOrderPresenter.createOrder(ConfirmOrderActivity.this,bean);


                // TODO: 2019/1/1 支付
                startActivity(new Intent(ConfirmOrderActivity.this,PaySuceessActivity.class));
                break;
            case R.id.wash_delivery_rl://配送信息
                bundle.putParcelableArrayList("washFatoryCageGory",washFatoryCageGory);
                intent.putExtras(bundle);
                intent.setClass(ConfirmOrderActivity.this,DistributionInfoActivity.class);
                startActivity(intent);

                break;
            case R.id.wash_demand_rl://洗涤需求
                bundle.putParcelableArrayList("washFatoryCageGory",washFatoryCageGory);
                intent.putExtras(bundle);
                intent.setClass(ConfirmOrderActivity.this,WashDemandActivity.class);
                startActivity(intent);
                break;
            case R.id.combinedPayment://组合支付
                if(alertChainDialog!=null){
                    alertChainDialog.builder().setCancelable(false);
                    alertChainDialog.setTitle("组合支付")
                            .setMsg("组合支付，JSL按前一交易日的收盘价的1.5倍计算")
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    fullPayImg.setVisibility(View.GONE);
                                    combinedPayImg.setVisibility(View.VISIBLE);
                                    payType = "combined";
                                }
                            }).show();

                }
                break;
            case R.id.fullPayment://全额支付
                fullPayImg.setVisibility(View.VISIBLE);
                combinedPayImg.setVisibility(View.GONE);
                payType = "full";
                break;
            case R.id.pay_channel_wx_rl:
                wxPayImg.setVisibility(View.VISIBLE);
                zfbPayImg.setVisibility(View.GONE);
                payChannel = "wx";
                break;
            case R.id.pay_channel_zfb_rl:
                wxPayImg.setVisibility(View.GONE);
                zfbPayImg.setVisibility(View.VISIBLE);
                payChannel = "zfb";
                break;
            default:break;
        }
    }

    private void exitPay() {
        if(alertChainPayDialog!=null){
            //dialog msg消息体比较特殊 请勿更改！！！
            alertChainPayDialog.builder().setCancelable(false);
            alertChainPayDialog.setTitle("确认离开支付页面").setMsg("您的订单在 "+countDown+" 后未支付将会被取消，请尽快支付。")
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

    public static class MyTimeHandler extends Handler{

        WeakReference<ConfirmOrderActivity> weakReference;

        public MyTimeHandler(ConfirmOrderActivity activity){
            weakReference = new WeakReference<ConfirmOrderActivity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final ConfirmOrderActivity activity = weakReference.get();
            if(activity == null){return;}
            switch (msg.what) {
                case 1:
                    if(activity.alertChainPayDialog.isShow()){
                        activity.alertChainPayDialog.setMsg("您的订单在 "+activity.countDown+" 后未支付将会被取消，请尽快支付。");
                        activity.handler.sendEmptyMessageDelayed(1,1000);

                    }
                    break;
                default:break;
            }
        }
    }

    public void initPayTimeDialog(){
        alertChainDialog = new AlertChainDialog(this);
        alertChainPayDialog = new AlertChainDialog(this);
    }

    public void countDownPayTime(){
        TimeCount = new CountDownTimer(30*60 * 1000, 1000) {

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
        }else{
            TimeCount.start();
        }

    }

    private void initPopupWindow() {
        if (popupWindow == null) {
            popupWindow = new CreateOrderListPopupWindow(ConfirmOrderActivity.this, createOrderListBeans);
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    public void getData(){

        if(getIntent()==null){
            return;
        }
        switch (getIntent().getIntExtra("class",-1)){
            case 1://FactoryServiceActivity
                FactoryServiceResponseBean.WashFatoryDetail washFatoryDetail = getIntent().getParcelableExtra("WashFatoryDetail");
                if(washFatoryDetail==null)return;
                GlidImageManager.getInstance().loadImageView(this,washFatoryDetail.getImg(),service_img,R.drawable.ic_default_image);

                washFatoryCageGory = getIntent().getParcelableArrayListExtra("WashFatoryCageGory");

                tv_service_title.setText(washFatoryDetail.getS_name());
                tv_serivces_desc.setText(washFatoryDetail.getDescription());

                break;
            case 2://WashDemandActivity
                trade_detail = getIntent().getStringArrayExtra("trade_detail");
                if(!Util.isEmpty(trade_detail)){
                    String type = "",trade_detail_tv = "";
                    int pieceCount = 0,pieceItemCout = 0;
                    String [] tradeData;
                    for (String aTrade_detail : trade_detail) {
                        tradeData = aTrade_detail.split("_");
                        type = tradeData[2];
                        pieceItemCout = Integer.parseInt(tradeData[1]);
                        pieceCount += Integer.parseInt(tradeData[1]);
                        if(pieceItemCout>0){
                            trade_detail_tv += type + "*" + pieceItemCout + "  ";
                        }
                    }
                    wash_detail_tv.setText(trade_detail_tv);
                    wash_detail_count.setText("共"+pieceCount+"件");
                }
                break;
            case 3://WashAddressActivity
                break;
            default:break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exitPay();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (totalPricePresenter!=null){
            totalPricePresenter.detachView();
        }
        if (createOrderPresenter!=null){
            createOrderPresenter.detachView();
        }
    }

    @Override
    public void onTotalPriceSuccess(TotalPriceResponseBean totalPriceResponseBean) {

    }

    @Override
    public void onTotalPriceFailed(String msg) {

    }

    @Override
    public void onCreateOrderSuccess(CreateOrderResponseBean createOrderResponseBean) {

    }

    @Override
    public void onCreateOrderFailed(String msg) {

    }
}
