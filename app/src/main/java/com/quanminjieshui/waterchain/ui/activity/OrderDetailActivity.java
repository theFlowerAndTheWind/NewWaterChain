package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.contract.model.OrderDetailModel;
import com.quanminjieshui.waterchain.contract.presenter.OrderDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.OrderDetailViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import butterknife.BindView;

public class OrderDetailActivity extends BaseActivity implements OrderDetailViewImpl {

    @BindView(R.id.img_title_left)
    ImageView imgTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;

    @BindView(R.id.tv_factory_name)
    TextView tvFactoryName;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.container)
    LinearLayout container;

    @BindView(R.id.xrv)
    XRecyclerView xrv;

    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_status1)
    TextView tvStatus1;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.space1)
    View space1;

    @BindView(R.id.tv_pay_cate)
    TextView tvPayCate;
    @BindView(R.id.ll_pay_cate)
    LinearLayout llPayCate;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.ll_pay_type)
    LinearLayout llPayType;
    @BindView(R.id.tv_pay_price)
    TextView tvPayPrice;
    @BindView(R.id.ll_pay_price)
    LinearLayout llPayPrice;
    @BindView(R.id.tv_updatetime)
    TextView tvUpdatetime;
    @BindView(R.id.ll_updatetime)
    LinearLayout llUpdatetime;
    @BindView(R.id.space2)
    View space2;


    @BindView(R.id.tv_pay_jsl)//全额支付时不需要显示
            TextView tvPayJsl;//
    @BindView(R.id.ll_pay_jsl)//
            LinearLayout llPayJsl;//


    @BindView(R.id.tv_contact_name)
    TextView tvContactName;
    @BindView(R.id.ll_contact_name)
    LinearLayout llContactName;
    @BindView(R.id.tv_contact_tel)
    TextView tvContactTel;
    @BindView(R.id.ll_contact_tel)
    LinearLayout llContactTel;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.ll_express)
    LinearLayout llExpress;


    @BindView(R.id.tv_pickup_time)
    TextView tvPickupTime;
    @BindView(R.id.ll_pickup_time)
    LinearLayout llPickupTime;


    @BindView(R.id.tv_count_down)
    TextView tvCountDown;


    @BindView(R.id.btn_pay)
    Button btnPay;


    @BindView(R.id.btn_affirm)
    Button btnAffirm;


    @BindView(R.id.ll_buttons)
    LinearLayout llButtons;


    private View[] views1 = {llPayCate, llPayType, llPayPrice, llUpdatetime, space2};
    private View[] views2 = {llContactName, llContactTel, llAddress, llExpress};

    private OrderDetailPresenter orderDetailPresenter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        orderDetailPresenter = new OrderDetailPresenter(new OrderDetailModel());
        orderDetailPresenter.attachView(this);
        orderDetailPresenter.orderDetail(this, id);

        StatusBarUtil.setImmersionStatus(this, titleBar);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("订单详情");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onOrderDetailSuccess(OrderDetailResponseBean orderDetailResponseBeans) {
        final String status = orderDetailResponseBeans.getStatus();
        if (!TextUtils.isEmpty(status)) {
            switch (status) {
                case "待付款":
                    showUnpaid();
                    break;
                case "取件中":
                case "洗涤中":
                    showTransportingOrWashing(true);
                    break;
                case "已完成":
                    showDone(true);
                    break;
                case "已取消":
                    showCancled(false);
                    break;
                default:

                    break;
            }
        }
    }

    @Override
    public void onOrderDetailFailed(String msg) {

    }

    /**
     * 未支付订单
     */
    private void showUnpaid() {
        goneView(space1);
        goneViews(views1);
        goneView(llPayJsl);
        goneViews(views2);
        goneView(llPickupTime);
        showView(tvCountDown);
        showView(btnPay);
        goneView(btnAffirm);
    }

    /**
     * 取件中  洗涤中
     *
     * @param flag 是否组合支付
     */
    private void showTransportingOrWashing(boolean flag) {
        showView(space1);
        showViews(views1);
        if (flag) {
            showView(llPayJsl);
        } else {
            goneView(llPayJsl);
        }
        showViews(views2);
        showView(llPickupTime);
        goneView(tvCountDown);
        goneView(btnPay);
        showView(btnAffirm);
    }

    private void showDone(boolean flag) {
        showView(space1);
        showViews(views1);
        if (flag) {
            showView(llPayJsl);
        } else {
            goneView(llPayJsl);
        }
        showViews(views2);
        showView(llPickupTime);
        goneView(tvCountDown);
        goneView(btnPay);
        goneView(btnAffirm);
    }

    private void showCancled(boolean flag) {
        showView(space1);
        showViews(views1);
        if (flag) {
            showView(llPayJsl);
        } else {
            goneView(llPayJsl);
        }
        showViews(views2);
        goneView(llPickupTime);
        goneView(tvCountDown);
        goneView(btnPay);
        goneView(btnAffirm);
    }


    private void showView(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private void showViews(View[] views) {
        for (View view : views) {
            showView(view);
        }
    }

    private void goneView(View view) {
        view.setVisibility(View.GONE);
    }

    private void goneViews(View[] views) {
        for (View view : views) {
            goneView(view);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderDetailPresenter.detachView();
    }
}
