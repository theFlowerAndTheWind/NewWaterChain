/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: TradeListsActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:07 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:07 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
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
import com.quanminjieshui.waterchain.beans.TradeListsResponseBean;
import com.quanminjieshui.waterchain.contract.model.CancleTradeModel;
import com.quanminjieshui.waterchain.contract.model.TradeListsModel;
import com.quanminjieshui.waterchain.contract.presenter.CancleTradePresenter;
import com.quanminjieshui.waterchain.contract.presenter.TradeListsPresenter;
import com.quanminjieshui.waterchain.contract.view.CancleTradeViewImpl;
import com.quanminjieshui.waterchain.contract.view.TradeListsViewImpl;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.ui.adapter.TradeListsAdapter;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: TradeListsActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:07 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:07 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeListsActivity extends BaseActivity implements
        TradeListsViewImpl,
        CancleTradeViewImpl,
        TradeListsAdapter.OnItemClickedListener {

    @BindView(R.id.left_ll)
    LinearLayout leftLl;
    @BindView(R.id.tv_title_center)
    TextView tvTitleCenter;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.xrv)
    XRecyclerView xrv;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.relative_hint)
    RelativeLayout relativeHint;

    Unbinder unbinder;
    private TradeListsPresenter tradeListsPresenter;
    private CancleTradePresenter cancleTradePresenter;
    private List<TradeListsResponseBean.TradeEntity> list = new ArrayList<>();
    private TradeListsAdapter tradeListsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        StatusBarUtil.setImmersionStatus(this, titleBar);
        tradeListsPresenter = new TradeListsPresenter(new TradeListsModel());
        tradeListsPresenter.attachView(this);
        cancleTradePresenter = new CancleTradePresenter(new CancleTradeModel());
        cancleTradePresenter.attachView(this);
        tradeListsPresenter.getTradeLists(this);
        initView();
    }

    private void initView() {
        tvTitleCenter.setText("全部委托");
        tradeListsAdapter = new TradeListsAdapter(this, list, this);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.setLoadingMoreEnabled(false);
        xrv.setPullRefreshEnabled(false);
        xrv.setAdapter(tradeListsAdapter);
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_trade_lists);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @Override
    public void onGetTradeListsSuccess(TradeListsResponseBean tradeListsResponseBean) {
        if (tradeListsResponseBean != null && tradeListsResponseBean.getLists() != null) {
            List<TradeListsResponseBean.TradeEntity> temp = tradeListsResponseBean.getLists();
            if (temp.size() <= 0) {
                xrv.setVisibility(View.GONE);
                relativeHint.setVisibility(View.VISIBLE);
                tvDetail.setText("您还没有任何交易！");
                tvDetail.setTextColor(getResources().getColor(R.color.text_black));
                tvDetail.setEnabled(false);
            } else {
                list.clear();
                list.addAll(tradeListsResponseBean.getLists());
                tradeListsAdapter.notifyDataSetChanged();
                xrv.setVisibility(View.VISIBLE);
                relativeHint.setVisibility(View.GONE);
                tvDetail.setEnabled(false);
            }
        }

    }

    @Override
    public void onGetTradeListsFailed(String msg) {
        xrv.setVisibility(View.GONE);
        relativeHint.setVisibility(View.VISIBLE);
        tvDetail.setText("刷新");
        tvDetail.setTextColor(getResources().getColor(R.color.primary_blue));
        tvDetail.setEnabled(true);

    }

    @Override
    public void onItemClicked(int id) {
        Intent intent = new Intent(this, TradeDetaiActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);

        HashMap<String,Object>params=new HashMap<>();
        params.put("id",id);

    }

    @Override
    public void onCancleClicked(int tid) {
        cancleTradePresenter.cancle(this, tid);
//        showLoadingDialog();
    }

    @Override
    public void onCancleSuccess() {
//        dismissDialog();
        //todo  显示浮层
    }

    @Override
    public void onCancleFailed(String msg) {
//        dismissDialog();
        ToastUtils.showCustomToast(msg);
    }

    @OnClick({R.id.tv_detail, R.id.left_ll})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_detail:
                if (tradeListsPresenter != null) {
                    tradeListsPresenter.getTradeLists(this);
                }
                break;
            case R.id.left_ll:
                goBack(view);
//                finish();//
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        tradeListsPresenter.detachView();
        cancleTradePresenter.detachView();
        super.onDestroy();
    }


}