package com.shuzijieshui.www.waterchain.ui.activity;

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

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CheckUserPayResponseBean;
import com.shuzijieshui.www.waterchain.beans.GoodsDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.request.CheckUserPayReqBean;
import com.shuzijieshui.www.waterchain.contract.presenter.CheckUserPayPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.GoodsDetailPresenter;
import com.shuzijieshui.www.waterchain.contract.view.CheckUserPayViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.GoodsDetailViewImpl;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.utils.StatusBarUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.Util;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:商品详情
 */

public class GoodsDetailActivity extends BaseActivity implements GoodsDetailViewImpl, CheckUserPayViewImpl {

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
    private String jslPrice;

    private GoodsDetailResponseBean goodsDetailResponseBean;
    private CheckUserPayResponseBean checkUserPayResponseBean;
    public static final String EXTRA_GOODSDETAILRESPONSEBEAN = "goodsDetailResponseBean";
    public static final String EXTRA_CHECKUSERPAYRESPONSEBEAN = "checkUserPayResponseBean";

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

    @OnClick({R.id.left_ll, R.id.goods_exchange})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                finish();
                break;
            case R.id.goods_exchange:
                if (!Util.isFastDoubleClick()) {
                    doCheckUserPayRequest();

//                    jump2ConfirmGoodsOrderAct();
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
        goodsDetailResponseBean = beans;
        if (!Util.isEmpty(beans) && !TextUtils.isEmpty(beans.getName())) {
            relativeHint.setVisibility(View.GONE);
            gooodsContent.setVisibility(View.VISIBLE);
            goodsExchange.setVisibility(View.VISIBLE);
            tvGoodsTitle.setText(beans.getName());
            jslPrice = beans.getJsl();
            tvGoodsDemandNumber.setText("所需水方：" + jslPrice);
            tvGoodsPrice.setText("市场价 ¥ " + beans.getPrice());
            tvGoodsStockNumber.setText("库存数量：" + beans.getNow_stock() + " / " + beans.getStock());
            tvGoodsIntroduce.setText("商品介绍");
            cateId = beans.getCate_id();
            CharSequence charSequence;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                charSequence = Html.fromHtml(beans.getDescription(), Html.FROM_HTML_MODE_LEGACY);
            } else {
                charSequence = Html.fromHtml(beans.getDescription());
            }
            tvGoodsDescribe.setText(charSequence);
            GlidImageManager.getInstance().loadImageView(this, beans.getImg(), img, R.mipmap.default_img);
        } else {
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
        dismissLoadingDialog();

        if (beans != null) {
            checkUserPayResponseBean = beans;
//            checkUserPayResponseBean.setCan_order(1);
//            checkUserPayResponseBean.setUser_jsl("100000");

            String title = null;
            String msg = null;
            String pos = null;
            String neg = null;
            if (beans.getCan_order() == 1) {
//                String price = new StringBuilder(jslPrice).append("水方").toString();
                float fPrice=Float.valueOf(beans.getGoods_pay());
                float fUserJsl=Float.valueOf(beans.getUser_jsl());
                float fPayJsl=Float.valueOf(beans.getPay_jsl());
                String price = new StringBuilder(String.format("%.5f",fPrice)).append("水方").toString();
//                String pay_gyj = new StringBuilder(beans.getPay_gyj()).append("水方").toString();
//                String user_gyj = new StringBuilder(beans.getUser_gyj()).append("水方").toString();
                String pay_jsl = new StringBuilder(String.format("%.5f",fPayJsl)).append("水方").toString();
                String user_jsl = new StringBuilder(String.format("%.5f",fUserJsl)).append("水方").toString();

                msg = new StringBuilder()
                        .append("活动价格：").append(price)
                        .append("\n账户余额：").append(user_jsl)/*.append("(公益金账户:").append(user_gyj).append(")")*/
                        .append("\n需支付：").append(pay_jsl)/*.append("(账户余额：").append(user_jsl).append(")")*/
                        .toString();
                title = "确认兑换该活动";
                pos = "确定";
                neg = "取消";
            } else {
                title = "余额不足";
                if (cateId == 1) msg = "余额不足以支付该商品";
                else if (cateId == 2) msg = "余额不足以支付该活动";
                neg = "知道了";
            }
            showDialog(title, msg, pos, neg);
        }

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
                    jump2ConfirmGoodsOrderAct();
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

    private void jump2ConfirmGoodsOrderAct() {
        Intent intent = new Intent(this, ConfirmGoodsOrderActivity.class);
        intent.putExtra(ConfirmGoodsOrderActivity.INT_EXTRA, 1);
        intent.putExtra(EXTRA_GOODSDETAILRESPONSEBEAN, goodsDetailResponseBean);
        intent.putExtra(EXTRA_CHECKUSERPAYRESPONSEBEAN, checkUserPayResponseBean);
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
        if (getIntent() != null) {
            detailPresenter.getGoodsDetail(this, getIntent().getIntExtra("id", -1));
            showLoadingDialog();
        }
    }

    private void doCheckUserPayRequest() {
        if (getIntent() != null) {
            CheckUserPayReqBean bean = new CheckUserPayReqBean();
            bean.setId(getIntent().getIntExtra("id", -1));
            bean.setCount(1);//活动类商品传1 活动2
            checkUserPayPresenter.checkUserPay(this, bean);
            showLoadingDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (detailPresenter != null) {
            detailPresenter.detachView();
        }
        if (checkUserPayPresenter != null) {
            checkUserPayPresenter.detachView();
        }
    }
}
