package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.CheckUserPayResponseBean;
import com.quanminjieshui.waterchain.beans.GoodsDetailResponseBean;
import com.quanminjieshui.waterchain.beans.request.CheckUserPayReqBean;
import com.quanminjieshui.waterchain.contract.presenter.CheckUserPayPresenter;
import com.quanminjieshui.waterchain.contract.presenter.GoodsDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.CheckUserPayViewImpl;
import com.quanminjieshui.waterchain.contract.view.GoodsDetailViewImpl;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;
import com.quanminjieshui.waterchain.utils.Util;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:商品详情
 */

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailViewImpl,CheckUserPayViewImpl{

    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.goods_title)
    TextView tvGoodsTitle;
    @BindView(R.id.goods_demand_number)
    TextView tvGoodsDemandNumber;
    @BindView(R.id.goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.goods_stock_number)
    TextView tvGoodsStockNumber;
    @BindView(R.id.goods_introduce)
    TextView tvGoodsIntroduce;
    @BindView(R.id.goods_describe)
    TextView tvGoodsDescribe;
    @BindView(R.id.goods_img)
    ImageView img;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;
    @BindView(R.id.goods_content)
    ScrollView gooodsContent;
    @BindView(R.id.goods_exchange)
    Button goodsExchange;
    private GoodsDetailPresenter detailPresenter;
    private CheckUserPayPresenter checkUserPayPresenter;
    private AlertChainDialog alertChainDialog;
    private int cateId;

    private GoodsDetailResponseBean goodsDetailResponseBean;
    public static final String EXTRA_GOODSDETAILRESPONSEBEAN="goodsDetailResponseBean";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        detailPresenter = new GoodsDetailPresenter();
        detailPresenter.attachView(this);
        checkUserPayPresenter = new CheckUserPayPresenter();
        checkUserPayPresenter.attachView(this);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("商品详情");
        tvDetail.setText("加载数据");
        tvDetail.setTextColor(getResources().getColor(R.color.primary_blue));
        alertChainDialog = new AlertChainDialog(this);
    }

    @OnClick({R.id.left_ll,R.id.goods_exchange})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                finish();
                break;
            case R.id.goods_exchange:
                if (!Util.isFastDoubleClick()){
                    doCheckUserPayRequest();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onGoodsDetailSuccess(GoodsDetailResponseBean beans) {
        goodsDetailResponseBean=beans;
        if (!Util.isEmpty(beans) && !TextUtils.isEmpty(beans.getName())){
            relativeHint.setVisibility(View.GONE);
            gooodsContent.setVisibility(View.VISIBLE);
            goodsExchange.setVisibility(View.VISIBLE);
            tvGoodsTitle.setText(beans.getName());
            tvGoodsDemandNumber.setText("所需水方："+beans.getJsl());
            tvGoodsPrice.setText("市场价 ¥ "+beans.getPrice());
            tvGoodsStockNumber.setText("库存数量："+beans.getNow_stock()+" / "+beans.getStock());
            tvGoodsIntroduce.setText("商品介绍");
            cateId = beans.getCate_id();
            CharSequence charSequence;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                charSequence = Html.fromHtml(beans.getDescription(),Html.FROM_HTML_MODE_LEGACY);
            } else {
                charSequence = Html.fromHtml(beans.getDescription());
            }
            tvGoodsDescribe.setText(charSequence);
            GlidImageManager.getInstance().loadImageView(this, beans.getImg(), img, R.mipmap.default_img);
        }else{
            relativeHint.setVisibility(View.VISIBLE);
            gooodsContent.setVisibility(View.GONE);
            goodsExchange.setVisibility(View.GONE);
        }

        dismissLoadingDialog();
    }

    @Override
    public void onGoodsDetailFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    @Override
    public void onCheckUserPaySuccess(CheckUserPayResponseBean beans) {
        if (beans.getCan_order().equals("1")){
            String activityPrice = beans.getPay_jsl()+"水方";
            String accountBalance = beans.getUser_jsl()+"水方";
            String exchangeAfterBalance = (Double.valueOf(beans.getUser_jsl())-Double.valueOf(beans.getPay_jsl()))+"水方";
            String message = "活动价格："+activityPrice+"\n账户余额："+accountBalance+"\n兑换后余额："+exchangeAfterBalance;
            if (alertChainDialog!=null){
                alertChainDialog.builder().setCancelable(false);
                alertChainDialog.setTitle("确认兑换该活动")
                        .setMsg(message)
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // TODO: 2019/2/2 兑换
                                jump2GoodsDetailAct();
                            }


                        }).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
            }
        }else{
            ToastUtils.showCustomToast("不足支付，不可兑换并创建订单");
        }

        dismissLoadingDialog();
    }

    private void jump2GoodsDetailAct(){
        Intent intent=new Intent(this,ConfirmGoodsOrderActivity.class);
        intent.putExtra(EXTRA_GOODSDETAILRESPONSEBEAN,goodsDetailResponseBean);
        startActivity(intent);
    }

    @Override
    public void onCheckUserPayFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doGoodsDetailRequest();
    }

    private void doGoodsDetailRequest() {
        if (getIntent()!=null){
            detailPresenter.getGoodsDetail(this,getIntent().getIntExtra("id",-1));
            showLoadingDialog();
        }
    }

    private void doCheckUserPayRequest(){
        if (getIntent()!=null){
            CheckUserPayReqBean bean = new CheckUserPayReqBean();
            bean.setId(getIntent().getIntExtra("id",-1));
            bean.setCount(cateId);//活动类商品传1 活动2
            checkUserPayPresenter.checkUserPay(this,bean);
            showLoadingDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (detailPresenter!=null){
            detailPresenter.detachView();
        }
        if (checkUserPayPresenter!=null){
            checkUserPayPresenter.detachView();
        }
    }
}
