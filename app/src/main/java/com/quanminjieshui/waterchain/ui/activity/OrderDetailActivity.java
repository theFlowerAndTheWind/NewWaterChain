package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.quanminjieshui.waterchain.ui.adapter.OrderDetailServiceCateAdapter;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.divider)
    View divider;
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
    @BindView(R.id.divider_pay_jsl)
    View dividerPayJsl;


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


    private View[] views1;
    private View[] views2;
    private View[] views3;//节水量linear、divider
    private View[] views4;//复用module_recyle_item_order_lists内容，部分需要影藏

    private OrderDetailPresenter orderDetailPresenter;
    private int id;//订单id
    private int fid;//工厂id；
    private String f_name;//工厂名称
    private String status;
    private String pay_cate;


    private List<OrderDetailResponseBean.ServiceCateEntry> serviceCateEntries = new ArrayList<>();
    private OrderDetailServiceCateAdapter adapter;

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
        views1 = new View[]{llPayCate, llPayType, llPayPrice, llUpdatetime, space2};
        views2 = new View[]{llContactName, llContactTel, llAddress, llExpress};
        views3 = new View[]{llPayJsl, dividerPayJsl};
        views4 = new View[]{tvStatus, divider};

        adapter = new OrderDetailServiceCateAdapter(this, serviceCateEntries);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.setAdapter(adapter);
        xrv.setLoadingMoreEnabled(false);
    }

    @OnClick({R.id.img_title_left, R.id.container})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.img_title_left:
                goBack(view);
//                finish();
                break;
            case R.id.container:
                if (fid > 0) {
                    Intent intent = new Intent(OrderDetailActivity.this, EnterpriseActivity.class);
                    intent.putExtra("enterpriseId", fid);
                    intent.putExtra("f_name", f_name);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_order_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if (orderDetailPresenter != null) {
            orderDetailPresenter.orderDetail(this, id);
        }
    }

    @Override
    public void onOrderDetailSuccess(OrderDetailResponseBean bean) {
        if (bean == null) return;
        fid = bean.getFid();
        f_name=bean.getF_name();
        status = bean.getStatus();
        pay_cate = bean.getPay_cate();


        tvFactoryName.setText(f_name);
        GlidImageManager.getInstance().loadImageUri(this, bean.getImg(), img, R.mipmap.default_img);
        tvService.setText(bean.getS_name());
        tvTotalPrice.setText(bean.getTotal_price());

        serviceCateEntries.clear();
        serviceCateEntries.addAll(bean.getService_cate());
        adapter.notifyDataSetChanged();
        xrv.refreshComplete();

        if (status.equals("取件中") || status.equals("洗涤中") || status.equals("已完成") || status.equals("已取消")) {
            tvOrderSn.setText(bean.getOrder_sn());
            tvStatus1.setText(status);
            tvCreatetime.setText(bean.getCreatetime());
            tvPayCate.setText(bean.getPay_cate());
            tvPayType.setText(bean.getPay_type());
            tvPayPrice.setText(bean.getPay_price());
            tvPayJsl.setText(bean.pay_jsl);
            tvUpdatetime.setText(bean.getUpdatetime());


            tvContactName.setText(bean.getContact_name());
            tvContactTel.setText(bean.getContact_tel());
            tvAddress.setText(bean.getAddress());
            tvExpress.setText(bean.getExpress());

        }

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
        goneViews(views4);
        goneView(space1);
        goneViews(views1);
        goneViews(views3);
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
        goneViews(views4);
        showView(space1);
        showViews(views1);
        if (pay_cate.equals("组合支付")) {
            showViews(views3);
        } else {
            goneViews(views3);
        }
        showViews(views2);
        showView(llPickupTime);
        goneView(tvCountDown);
        goneView(btnPay);
        showView(btnAffirm);
    }

    private void showDone(boolean flag) {
        goneViews(views4);
        showView(space1);
        showViews(views1);
        if (pay_cate.equals("组合支付")) {
            showViews(views3);
        } else {
            goneViews(views3);
        }
        showViews(views2);
        showView(llPickupTime);
        goneView(tvCountDown);
        goneView(btnPay);
        goneView(btnAffirm);
    }

    private void showCancled(boolean flag) {
        goneViews(views4);
        showView(space1);
        showViews(views1);
        if (pay_cate.equals("组合支付")) {
            showViews(views3);
        } else {
            goneViews(views3);
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
