package com.shuzijieshui.www.waterchain.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.beans.BannerListResponseBean;
import com.shuzijieshui.www.waterchain.beans.Factory;
import com.shuzijieshui.www.waterchain.beans.TradeCenterResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeLineResponseBean;
import com.shuzijieshui.www.waterchain.beans.FactoryListResponse;
import com.shuzijieshui.www.waterchain.contract.model.TradeCenterModel;
import com.shuzijieshui.www.waterchain.contract.presenter.BannerListPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.FactoryListPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.TradeCenterPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.TradeLinePresenter;
import com.shuzijieshui.www.waterchain.contract.view.BannerListViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.FactoryListViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.TradeCenterViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.TradeLineViewImpl;
import com.shuzijieshui.www.waterchain.ui.activity.EnterpriseActivity;
import com.shuzijieshui.www.waterchain.ui.activity.GoodsDetailActivity;
import com.shuzijieshui.www.waterchain.ui.activity.GoodsListsActivity;
import com.shuzijieshui.www.waterchain.ui.activity.InfoDetailActivity;
import com.shuzijieshui.www.waterchain.ui.activity.MainActivity;
import com.shuzijieshui.www.waterchain.ui.activity.WebViewActivity;
import com.shuzijieshui.www.waterchain.ui.adapter.FactoryListIndexAdapter;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.ui.widget.chart.ChartUtil;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;
import com.shuzijieshui.www.waterchain.utils.image.GlidImageManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by WanghongHe on 2018/12/3 10:54.
 * 首页
 */

public class HomeFragment extends BaseFragment implements BannerListViewImpl, FactoryListViewImpl, TradeLineViewImpl, TradeCenterViewImpl {


    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.rc_wash_list)
    XRecyclerView factoryList;
    @BindView(R.id.tv_wash_damend)
    TextView washDamend;
    @BindView(R.id.tv_transaction_center)
    TextView transactionCenter;
    @BindView(R.id.tv_cur_price)
    TextView tvCurPrice;
    @BindView(R.id.tv_price_limit)
    TextView tvPriceLimit;

    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private View rootView;
    private BannerListPresenter bannerListPresenter;
    private FactoryListPresenter factoryListPresenter;
    private TradeLinePresenter tradeLinePresenter;
    private TradeCenterPresenter tradeCenterPresenter;//获取跌涨幅，当前市价
    private FactoryListIndexAdapter factoryListAdapter;
    private List<BannerListResponseBean.BannerListEntity> banners = new ArrayList<>();
    MainActivity activity;
    private List<Factory> listEntities = new ArrayList<>();
    private int count = 0;//factoryList计数
    private boolean isRefresh = false;//是否刷新

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);
        factoryListPresenter = new FactoryListPresenter();
        factoryListPresenter.attachView(this);
        tradeLinePresenter = new TradeLinePresenter();
        tradeLinePresenter.attachView(this);
        showLoadingDialog();
        requestNetwork();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        }, 1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
            ButterKnife.bind(this, rootView);
        }
        alertChainDialog = new AlertChainDialog(getBaseActivity());
        initList();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity) {
            this.activity = (MainActivity) activity;
        }
    }

    @OnClick({R.id.tv_wash_damend, R.id.tv_transaction_center})
    public void OnClick(View view) {
        activity.hideFragment();
        switch (view.getId()) {
            case R.id.tv_wash_damend:
                activity.showWash();
                break;
            case R.id.tv_transaction_center:
                activity.showTransaction();
                break;
            default:
                break;
        }
    }

    private void initList() {
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, BannerListResponseBean.BannerListEntity>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, BannerListResponseBean.BannerListEntity model, int position) {
                GlidImageManager.getInstance().loadImageView(getBaseActivity(), model.getImg(), itemView, R.drawable.holder);
            }
        });

        factoryListAdapter = new FactoryListIndexAdapter(getBaseActivity(), listEntities);
        factoryList.setArrowImageView(R.drawable.iconfont_downgrey);
        factoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        factoryList.addItemDecoration(new RecyclerViewDivider(getBaseActivity(),LinearLayoutManager.HORIZONTAL,1,getResources().getColor(R.color.item_line)));
        factoryList.setAdapter(factoryListAdapter);
        factoryList.setPullRefreshEnabled(false);
        factoryList.setLoadingMoreEnabled(false);
        factoryList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (factoryListPresenter != null) {
                    isRefresh = true;
                    factoryListPresenter.getFactoryList(getBaseActivity(), count);
                }
            }

            @Override
            public void onLoadMore() {
                if (factoryListPresenter != null) {
                    isRefresh = false;
                    factoryListPresenter.getFactoryList(getBaseActivity(), count);
                }
            }
        });
        factoryListAdapter.setOnItemClickListener(new FactoryListIndexAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("enterpriseId", listEntities.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getBaseActivity(), EnterpriseActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, BannerListResponseBean.BannerListEntity>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, BannerListResponseBean.BannerListEntity model, int position) {
                String id = model.getUrl_id();
                String type = model.getUrl_type();
                Intent intent = new Intent();
                if (!TextUtils.isEmpty(type)) {
                    if (type.equals("factory")) {//企业详情
                        intent.setClass(getBaseActivity(), EnterpriseActivity.class);
                    } else if (type.equals("goods_list")) {//商品列表
                        intent.setClass(getBaseActivity(), GoodsListsActivity.class);
                    } else if (type.equals("goods_detail")) {//商品详情
                        intent.setClass(getBaseActivity(), GoodsDetailActivity.class);
                    } else if (type.equals("info_detail")) {//资讯详情
                        intent.setClass(getBaseActivity(), InfoDetailActivity.class);
                    } /*else if () {//抽奖活动详情  待定

                    }*/
                    if (!TextUtils.isEmpty(id)) intent.putExtra("id",id);
                    intent.putExtra("target","首页");
                    startActivity(intent);
                }
//                Intent intent = new Intent();
//                intent.setClass(getBaseActivity(), WebViewActivity.class);
//                intent.putExtra("URL", imgUrlList.get(position));
//                intent.putExtra("title", nameList.get(position));
//                startActivity(intent);
            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            requestNetwork();
        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            requestNetwork();
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        requestNetwork();
    }

    public void requestNetwork() {
        if (bannerListPresenter != null) {
            bannerListPresenter.getBannerList(getBaseActivity(), 3, 1);
        }
        if (factoryListPresenter != null) {
            factoryListPresenter.getFactoryList(getBaseActivity(), count);

        }
        if (tradeLinePresenter != null) {
            tradeLinePresenter.getTradeLine(getBaseActivity(), "today");
        }
        if (tradeCenterPresenter == null) {
            tradeCenterPresenter = new TradeCenterPresenter(new TradeCenterModel());
            tradeCenterPresenter.attachView(this);
        }
        tradeCenterPresenter.getTradeCenter(getBaseActivity());
        //showLoadingDialog();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bannerListPresenter != null) {
            bannerListPresenter.detachView();
        }
        if (factoryListPresenter != null) {
            factoryListPresenter.detachView();
        }
        if (tradeLinePresenter != null) {
            tradeLinePresenter.detachView();
        }
        if (tradeCenterPresenter != null) tradeCenterPresenter.detachView();
    }

    @Override
    public void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean) {
        if (tradeLineResponseBean != null) {
            final List<TradeLineResponseBean.ChartDataEntity> data = tradeLineResponseBean.getData();
            final List<String> xaxis = tradeLineResponseBean.getXasix();
            final List<Entry> entries = ChartUtil.getEntries(data);
            final List<Long> longXaxis = ChartUtil.getLongXaxis(xaxis);
            ChartUtil.initLineChart(lineChart, entries, longXaxis, "today");
        }
    }

    @Override
    public void onTradeLineFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }


    @Override
    public void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list) {
        banners.clear();
        banners.addAll(list);
//        List<String>tips=new ArrayList<>();
//        for (BannerListResponseBean.BannerListEntity listEntity : list) {
//            tips.add(listEntity.getName());
//        }
        mContentBanner.setData(banners, null);

    }

    @Override
    public void onBannerListFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onFactoryListSuccess(FactoryListResponse factoryListResponse) {
        if (factoryListResponse != null) {
            List<Factory> factoryListEntities = factoryListResponse.getLists();
            if (isRefresh) listEntities.clear();
            listEntities.addAll(factoryListEntities);
            count = listEntities.size();
            factoryListAdapter.notifyDataSetChanged();
            factoryList.refreshComplete();
        }
        isRefresh = false;//每次请求后必须执行
    }

    @Override
    public void onFactoryListFailed(String msg) {
        isRefresh = false;//每次请求后必须执行
        ToastUtils.showCustomToast(msg, 0);
    }


    @Override
    public void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean) {
        if (tradeCenterResponseBean != null) {
            tvCurPrice.setText(new StringBuilder("1 水方 = ").append(tradeCenterResponseBean.getCur_price()).append("节水指标").toString());
            String price_limit_color = tradeCenterResponseBean.getPrice_limit_color();
            if (price_limit_color.equals("red")) {
                tvPriceLimit.setTextColor(getResources().getColor(R.color.primary_red));
                tvPriceLimit.setText(new StringBuilder("+").append(tradeCenterResponseBean.getPrice_limit()).append("%").toString());
            } else if (price_limit_color.equals("green")) {
                tvPriceLimit.setTextColor(getResources().getColor(R.color.text_green));
                tvPriceLimit.setText(new StringBuilder().append(tradeCenterResponseBean.getPrice_limit()).append("%").toString());
            } else {
                tvPriceLimit.setTextColor(getResources().getColor(R.color.text_black));
                tvPriceLimit.setText(tradeCenterResponseBean.getPrice_limit());
            }
        }
    }

    @Override
    public void onTradeCenterFailed(String msg) {

    }

    @Override
    public void onBuySuccess(Object o) {
    }

    @Override
    public void onBuyFailed(String msg) {
    }

    @Override
    public void onSellSuccess(Object o) {
    }

    @Override
    public void onSellFailed(String msg) {
    }
}
