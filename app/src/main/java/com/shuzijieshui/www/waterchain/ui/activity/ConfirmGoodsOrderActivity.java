package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CheckUserPayResponseBean;
import com.shuzijieshui.www.waterchain.beans.GoodsDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.UserAddressResponseBean;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderExchangeReqBean;
import com.shuzijieshui.www.waterchain.contract.model.CreateOrderExchangeModel;
import com.shuzijieshui.www.waterchain.contract.model.UserAddressModel;
import com.shuzijieshui.www.waterchain.contract.presenter.CreateOrderExchangePresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.UserAddressPresenter;
import com.shuzijieshui.www.waterchain.contract.view.CreateOrderExchangeViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.UserAddressViewImpl;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class ConfirmGoodsOrderActivity extends BaseActivity implements /*CheckUserPayViewImpl,*/ UserAddressViewImpl, CreateOrderExchangeViewImpl {
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_jsl_price)
    TextView tvJslPrice;
    @BindView(R.id.tv_counter)
    TextView tvCounter;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_edt)
    TextView tvEdt;
    @BindView(R.id.tv_receiver_name)
    TextView tvReceiverName;
    @BindView(R.id.tv_receiver_tel)
    TextView tvReceiverTel;
    @BindView(R.id.tv_receiver_address)
    TextView tvReceiverAddress;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    //    @BindView(R.id.tv_gyj)
//    TextView tvGyj;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.btn_plus)
    ImageView btnPlus;
    @BindView(R.id.btn_subtract)
    ImageView btnSubtract;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    //    @BindView(R.id.tv_no_nomey1)
//    TextView tvNoMomey1;
    @BindView(R.id.tv_no_nomey2)
    TextView tvNoMomey2;

    @BindDrawable(R.drawable.btn_gray_shape)
    Drawable grayBg;
    @BindDrawable(R.drawable.btn_blue_bg_selector)
    Drawable blueBg;

    private GoodsDetailResponseBean goodsDetailBean;
    private CheckUserPayResponseBean checkReusltBean;
    private int id;
    private int cate_id;//
    private int max;//本次最大可兑换量
    private String jsl;
    private String imgUrl;
    private int counter = 1;
    private String goodsPay;//水方单价
    private String userJsl;//用户持有水方
    private String payJsl;//需支付水方  此处为商品数量为1
    boolean jslEnough = true;

    private AlertChainDialog alertChainDialog;
    //    private CheckUserPayPresenter checkUserPayPresenter;
    private UserAddressPresenter userAddressPresenter;
    private CreateOrderExchangePresenter createOrderExchangePresenter;
    private CreateOrderExchangeReqBean params = new CreateOrderExchangeReqBean();
    public static final String INT_EXTRA = "int_extra_where_from";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getIntentData(getIntent());
        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();


//        checkUserPayPresenter = new CheckUserPayPresenter(new CheckUserPayModel());
//        checkUserPayPresenter.attachView(this);
        userAddressPresenter = new UserAddressPresenter(new UserAddressModel());
        userAddressPresenter.attachView(this);
        createOrderExchangePresenter = new CreateOrderExchangePresenter(new CreateOrderExchangeModel());
        createOrderExchangePresenter.attachView(this);
        getUserAddress();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getIntentData(intent);
        tvReceiverName.setText(params.getReceiver());
        tvReceiverTel.setText(params.getTel());
        tvReceiverAddress.setText(new StringBuilder().append(params.getProvince()).append(params.getCity()).append(params.getAddress()).toString());

    }

    private void getIntentData(Intent intent) {

        if (intent != null) {
            int intExtra = intent.getIntExtra(INT_EXTRA, 1);
            if (intExtra == 2) {//form UserGoodsInfoActivity   //存储用户设置的各信息
                CreateOrderExchangeReqBean bean = intent.getParcelableExtra("CreateOrderExchangeReqBean");
                if (bean != null) {
                    params.setReceiver(bean.getReceiver());
                    params.setTel(bean.getTel());
                    params.setAddress(bean.getAddress());
                    params.setProvince(bean.getProvince());
                    params.setCity(bean.getCity());
                }

            } else if (intExtra == 1) {//from GoodsDetailActivigty
                goodsDetailBean = intent.getParcelableExtra(GoodsDetailActivity.EXTRA_GOODSDETAILRESPONSEBEAN);
                if (goodsDetailBean != null) {
                    id = goodsDetailBean.getId();
                    cate_id = goodsDetailBean.getCate_id();
                    max = goodsDetailBean.getNow_stock();
                    jsl = goodsDetailBean.getJsl();

                    params.setGid(id);
                    params.setCount(counter);
                }

                checkReusltBean = intent.getParcelableExtra(GoodsDetailActivity.EXTRA_CHECKUSERPAYRESPONSEBEAN);
                if (checkReusltBean != null) {
                    goodsPay = checkReusltBean.getGoods_pay();
                    userJsl = checkReusltBean.getUser_jsl();
                    payJsl = checkReusltBean.getPay_jsl();
                    compute();
                }

            }

        }
    }


    private void initView() {
        tvTitleCenter.setText("确认订单");
        GlidImageManager.getInstance().loadImageView(this, goodsDetailBean.getImg(), img, R.mipmap.default_img);
        tvGoodsName.setText(goodsDetailBean.getName());
        tvJslPrice.setText(new StringBuilder(jsl).append(" 水方").toString());
        tvCounter.setText(String.valueOf(counter));
        tvTotalPrice.setText(new StringBuilder(jsl).append(" 水方").append(" * ").append(counter).toString());
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_confirm_goods_order);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll, R.id.btn_plus, R.id.btn_subtract, R.id.btn_commit, R.id.tv_edt})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                finish();
                break;
            case R.id.btn_plus:
                if (cate_id == 1) {//实物商品
                    counter += 1;
                    if (counter >= max) {
                        counter = max;
                    }
                } else if (cate_id == 2) {//活动
                    counter = 1;
                }
                compute();
                tvCounter.setText(String.valueOf(counter));
                tvTotalPrice.setText(new StringBuilder(jsl).append(" 水方").append(" * ").append(counter).toString());
                params.setCount(counter);
                break;
            case R.id.btn_subtract:
                counter -= 1;
                if (counter <= 1) {
                    counter = 1;
                }
                compute();
                tvCounter.setText(String.valueOf(counter));
                tvTotalPrice.setText(new StringBuilder(jsl).append(" 水方").append(" * ").append(counter).toString());
                params.setCount(counter);
                break;
            case R.id.btn_commit:
//                doCheckUserPay();
                if (!jslEnough) {
                    //donothing
                } else {
                    createOrder();
                }
                break;

            case R.id.tv_edt:
                Intent intent = new Intent(this, UserGoodsInfoActivity1.class);
                intent.putExtra(CreateOrderExchangeReqBean.class.getSimpleName(), params);
                startActivity(intent);
                break;
        }
    }

//    private void doCheckUserPay() {
//        if (checkUserPayPresenter == null) {
//            checkUserPayPresenter = new CheckUserPayPresenter(new CheckUserPayModel());
//            checkUserPayPresenter.attachView(this);
//        }
//        CheckUserPayReqBean bean = new CheckUserPayReqBean();
//        bean.setId(id);
//        bean.setCount(counter);
//        checkUserPayPresenter.checkUserPay(this, bean);
//    }

    private void getUserAddress() {
        if (userAddressPresenter == null) {
            userAddressPresenter = new UserAddressPresenter(new UserAddressModel());
            userAddressPresenter.attachView(this);
        }
        userAddressPresenter.getUserAddress(this);
    }

    private void createOrder() {
        if (params.getCount() <= 0) {
            ToastUtils.showCustomToastMsg("请选择正确的数量！",150);
            return;
        }
        if (TextUtils.isEmpty(params.getReceiver())) {
            ToastUtils.showCustomToastMsg("收件人姓名不能为空",150);
            return;
        }
        if (TextUtils.isEmpty(params.getTel())) {
            ToastUtils.showCustomToastMsg("手机号码姓名不能为空",150);
            return;
        }
        if (TextUtils.isEmpty(params.getAddress())) {
            ToastUtils.showCustomToastMsg("地址不能为空",150);
            return;
        }
        if (TextUtils.isEmpty(params.getProvince())) {
            ToastUtils.showCustomToastMsg("请选择所在省份",150);
            return;
        }
        if (TextUtils.isEmpty(params.getCity())) {
            ToastUtils.showCustomToastMsg("请选择所在城市",150);
            return;
        }

        if (createOrderExchangePresenter == null) {
            createOrderExchangePresenter = new CreateOrderExchangePresenter(new CreateOrderExchangeModel());
            createOrderExchangePresenter.attachView(this);
        }
        createOrderExchangePresenter.createOrderExchange(this, params);
    }


    private void showDialog(String title, String msg, String pos, String neg) {
        if (alertChainDialog == null) {
            alertChainDialog = new AlertChainDialog(this);
        }
        alertChainDialog.builder().setCancelable(false);
        alertChainDialog.setTitle(title);
        alertChainDialog.setMsg(msg);
        if (!TextUtils.isEmpty(pos))
            alertChainDialog.setPositiveButton(pos, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createOrder();
                }


            });

        if (!TextUtils.isEmpty(neg)) {
            alertChainDialog.setNegativeButton(neg, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        alertChainDialog.show();
    }

    private void compute() {
        float unitPrice = 0;//单价   单位：水方
        float user_jsl = 0;
        float pay_jsl = 0;

        try {
            if (!TextUtils.isEmpty(goodsPay)) {
                unitPrice = Util.str2Flt(Util.deleteComma(goodsPay));
            }
            if (!TextUtils.isEmpty(userJsl)) {
                user_jsl = Util.str2Flt(Util.deleteComma(userJsl));
            }
            if (!TextUtils.isEmpty(goodsPay)) {
                pay_jsl = Util.str2Flt(Util.deleteComma(goodsPay)) * counter;
            }

            if (pay_jsl > user_jsl) {
                jslEnough = false;
                tvNoMomey2.setVisibility(View.VISIBLE);
            } else {
                jslEnough = true;
                tvNoMomey2.setVisibility(View.GONE);
            }

            if (!jslEnough) {
                btnCommit.setBackground(grayBg);
            } else {
                btnCommit.setBackground(blueBg);
            }
            String str1 = new StringBuilder(String.format("%.0f", (unitPrice * counter))).append("水方").toString();
//            String str2 = new StringBuilder(String.format("%.5f", pay_gyj)).append("水方").toString();
            String str3 = new StringBuilder(String.format("%.0f", pay_jsl)).append("水方").toString();
            tvTotal.setText(str1);
//            tvGyj.setText(str2);
            tvBalance.setText(str3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUserAddressSuccess(UserAddressResponseBean beans) {
        if (beans != null) {
            params.setReceiver(beans.getReceiver());
            params.setTel(beans.getTel());
            params.setAddress(beans.getAddress());
            params.setProvince(beans.getProvince());
            params.setCity(beans.getCity());
            tvReceiverName.setText(beans.getReceiver());
            tvReceiverTel.setText(beans.getTel());
            tvReceiverAddress.setText(beans.getAddr());
        }
    }

    @Override
    public void onUserAddressFailed(String msg) {
        ToastUtils.showCustomToastMsg(msg, 150);
    }

    @Override
    public void onCreateOrderExchangeSuccess(Object o) {
        Intent intent = new Intent(this, PaySuccessActivity.class);
        intent.putExtra("from", ConfirmGoodsOrderActivity.class.getSimpleName());
        intent.putExtra("goodsName", goodsDetailBean.getName());
        startActivity(intent);
    }

    @Override
    public void onCreateOrderExchangeFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (checkUserPayPresenter != null) checkUserPayPresenter.detachView();
        if (userAddressPresenter != null) userAddressPresenter.detachView();
        if (createOrderExchangePresenter != null) createOrderExchangePresenter.detachView();
    }

}
