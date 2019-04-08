package com.shuzijieshui.www.waterchain.ui.activity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.OrderDetail;
import com.shuzijieshui.www.waterchain.contract.model.OrderDetailModel;
import com.shuzijieshui.www.waterchain.contract.presenter.OrderDetailPresenter;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import butterknife.BindView;

public class OrderDetailActivity1 extends BaseActivity implements CommonViewImpl {

    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;

    @BindView(R.id.tv_factory_name)
    TextView tvFName;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_service)
    TextView tvSName;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_status)
    TextView tvStatus;

    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_status_view)
    TextView tvStatusView;
    @BindView(R.id.tv_add_time)
    TextView tvAddTime;
    @BindView(R.id.tv_pay_cate)
    TextView tvPayCate;
    @BindView(R.id.tv_pay_price)
    TextView tvPayPrice;
    @BindView(R.id.tv_pay_jsl)
    TextView tvPayJsl;
    @BindView(R.id.tv_add_time1)
    TextView tvAddTime1;

    private String id;
    private OrderDetailPresenter orderDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        getIntentExtra();
        initview();
        orderDetailPresenter = new OrderDetailPresenter(new OrderDetailModel());
        orderDetailPresenter.attachView(this);
        getOrderDetail();
    }

    private void initview() {
        tvTitleCenter.setText("订单详情");
        tvStatus.setVisibility(View.GONE);
    }

    private void getIntentExtra() {
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_detail1);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        showLoadingDialog();
        getOrderDetail();
    }

    private void getOrderDetail() {
        if (orderDetailPresenter == null) {
            orderDetailPresenter = new OrderDetailPresenter(new OrderDetailModel());
            orderDetailPresenter.attachView(this);
        }
        orderDetailPresenter.getOrderDetail(this, Integer.valueOf(id));
    }


    @Override
    public void onRequestSucc(Object o) {
        if (o != null) {
            OrderDetail orderDetail = (OrderDetail) o;
            tvFName.setText(orderDetail.getF_name());
            GlidImageManager.getInstance().loadImageView(this, orderDetail.getImg(), img, R.mipmap.default_img);
            tvSName.setText(orderDetail.getS_name());
            tvTotalPrice.setText("¥" + orderDetail.getTotal_price());
            tvCount.setText(orderDetail.getCount());
            tvOrderSn.setText(orderDetail.getOrder_sn());
            tvStatusView.setText(orderDetail.getStatus_view());
            tvAddTime.setText(orderDetail.getAdd_time());
            tvPayCate.setText(orderDetail.getPay_cate());
            tvPayPrice.setText("¥" + orderDetail.getPay_price());
            tvPayJsl.setText(orderDetail.getPay_jsl());
            tvAddTime1.setText(orderDetail.getAdd_time());
        }

        dismissLoadingDialog();
    }

    @Override
    public void onRequestFail(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orderDetailPresenter != null) {
            orderDetailPresenter.detachView();
        }
    }
}
