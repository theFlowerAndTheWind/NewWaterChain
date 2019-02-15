/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: GoodsActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:40 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:40 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.GoodsResposeBean;
import com.quanminjieshui.waterchain.contract.model.GoodsModel;
import com.quanminjieshui.waterchain.contract.presenter.GoodsPresenter;
import com.quanminjieshui.waterchain.contract.view.GoodsViewImpl;
import com.quanminjieshui.waterchain.ui.adapter.GoodsAdapter;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.activity
 * @ClassName: GoodsActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 12:40 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 12:40 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class GoodsActivity extends BaseActivity implements GoodsViewImpl {


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
    private GoodsPresenter goodsPresenter;
    private GoodsAdapter goodsAdapter;
    private List<GoodsResposeBean> list;
    private int counter = 0;
    private boolean isRefresh = false;

    @OnClick({R.id.img_title_left})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_title_left:
                goBack(v);
                finish();
                break;
            default:
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, titleBar);

        goodsPresenter = new GoodsPresenter(new GoodsModel());
        goodsPresenter.attachView(this);

        getGoodsOrders();

        initView();
    }

    private void initView() {
        tvTitleCenter.setText("我的兑换");
        list = new ArrayList<>();
        goodsAdapter = new GoodsAdapter(this, list);
        xrv.setArrowImageView(R.drawable.iconfont_downgrey);
        xrv.setLayoutManager(new LinearLayoutManager(this));
        xrv.setAdapter(goodsAdapter);
        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                counter = 0;
                getGoodsOrders();
            }

            @Override
            public void onLoadMore() {
                getGoodsOrders();
            }
        });
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_goods);
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if (goodsPresenter != null) {
            showLoadingDialog();
            goodsPresenter.getGoods(this, counter);
        }
    }

    @Override
    public void onGetGoodsSuccess(List<GoodsResposeBean> respList) {
        if(isRefresh)xrv.loadMoreComplete();
        xrv.refreshComplete();
        if (respList != null) {
            if (respList.size() == 0) {
                if (counter == 0) {
                    relativeHint.setVisibility(View.VISIBLE);
                    xrv.setVisibility(View.GONE);
                    tvDetail.setText("您还没有兑换过任何物品！");
                } else {
                    counter = list.size();
                }

            } else {
                relativeHint.setVisibility(View.GONE);
                xrv.setVisibility(View.VISIBLE);
                if(isRefresh)list.clear();
                list.addAll(respList);
                goodsAdapter.notifyDataSetChanged();
                counter = list.size();
            }
        }

        dismissLoadingDialog();
        isRefresh = false;
    }

    @Override
    public void onGetGoodsFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);

        dismissLoadingDialog();
        isRefresh = false;
    }

    private void getGoodsOrders() {
        if (goodsPresenter == null) {
            goodsPresenter = new GoodsPresenter(new GoodsModel());
            goodsPresenter.attachView(this);
        }
        showLoadingDialog();
        goodsPresenter.getGoods(this, counter);
    }

}
