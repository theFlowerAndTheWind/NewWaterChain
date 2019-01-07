/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TradeDetaiActivity
 * Author: sxt
 * Date: 2019/1/2 8:18 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeDetailResponseBean;
import com.quanminjieshui.waterchain.contract.model.TradeDetailModel;
import com.quanminjieshui.waterchain.contract.presenter.TradeDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.TradeDetailViewImpl;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.ui.adapter.TradeDetailAdapter;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: TradeDetaiActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/2 8:18 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/2 8:18 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeDetaiActivity extends BaseActivity implements TradeDetailViewImpl {
    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    View titleBar;
    @BindView(R.id.tv_action_type)
    TextView tvActionType;
    @BindView(R.id.tv_deal_total)
    TextView tvDealTotal;
    @BindView(R.id.tv_avg_price)
    TextView tvAvgPrice;
    @BindView(R.id.tv_old_total)
    TextView tvOldTotal;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.xrv)
    XRecyclerView xrv;

    Unbinder unbinder;
    private int id;//交易报单id
    private TradeDetailPresenter tradeDetailPresenter;
    private TradeDetailAdapter tradeDetailAdapter;
    private List<TradeDetailResponseBean.TradeDetailEntry> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        showLoadingDialog();
        tradeDetailPresenter = new TradeDetailPresenter(new TradeDetailModel());
        tradeDetailPresenter.attachView(this);
        tradeDetailPresenter.getTradeDetail(this, id);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("交易明细");
        tradeDetailAdapter = new TradeDetailAdapter(this, list);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.setLoadingMoreEnabled(false);
        xrv.setPullRefreshEnabled(false);
        xrv.setAdapter(tradeDetailAdapter);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_trade_detail);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.left_ll:
                goBack(view);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onTradeDetailSuccess(TradeDetailResponseBean tradeDetailResponseBean) {
        dismissLoadingDialog();
        if (tradeDetailResponseBean != null) {
            String action_type = tradeDetailResponseBean.getAction_type();
            if (action_type.equals("贡献")) {
                tvActionType.setTextColor(getResources().getColor(R.color.primary_red));
            } else if (action_type.equals("获取")) {
                tvActionType.setTextColor(getResources().getColor(R.color.text_green));
            }
            tvActionType.setText(action_type);
            tvAvgPrice.setText(tradeDetailResponseBean.getAvg_price());
            tvDealTotal.setText(tradeDetailResponseBean.getDeal_total());
            tvFee.setText(tradeDetailResponseBean.getFee());
            final List<TradeDetailResponseBean.TradeDetailEntry> trade_detail = tradeDetailResponseBean.getTrade_detail();
            if(trade_detail!=null){
                list.clear();
                list.addAll(trade_detail);
                tradeDetailAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onTradeDetailFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg);

    }
}