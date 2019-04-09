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
import com.shuzijieshui.www.waterchain.beans.ServiceListResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeLineResponseBean;
import com.shuzijieshui.www.waterchain.contract.model.ServiceListModel;
import com.shuzijieshui.www.waterchain.contract.presenter.BannerListPresenter;
import com.shuzijieshui.www.waterchain.contract.presenter.ServiceListPresneter;
import com.shuzijieshui.www.waterchain.contract.presenter.TradeLinePresenter;
import com.shuzijieshui.www.waterchain.contract.view.BannerListViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.ServiceListViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.TradeLineViewImpl;
import com.shuzijieshui.www.waterchain.ui.activity.EnterpriseActivity;
import com.shuzijieshui.www.waterchain.ui.activity.FactoryServiceActivity;
import com.shuzijieshui.www.waterchain.ui.activity.GoodsDetailActivity;
import com.shuzijieshui.www.waterchain.ui.activity.GoodsListsActivity;
import com.shuzijieshui.www.waterchain.ui.activity.InfoDetailActivity;
import com.shuzijieshui.www.waterchain.ui.activity.MainActivity;
import com.shuzijieshui.www.waterchain.ui.activity.ServiceDetailActivity;
import com.shuzijieshui.www.waterchain.ui.adapter.ServiceListAdapter;
import com.shuzijieshui.www.waterchain.ui.view.AlertChainDialog;
import com.shuzijieshui.www.waterchain.ui.widget.chart.ChartUtil1;
import com.shuzijieshui.www.waterchain.utils.Constants;
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
 * Created by sxt on 2019/3/26
 * 首页  "洗涤业务"改为"产品和服务"  脱盐水和废水处理
 * chart接口改变
 * <p>
 * 该页面在原来homepage基础上更改
 */

public class HomeFragment1 extends BaseFragment implements BannerListViewImpl, ServiceListViewImpl, TradeLineViewImpl {


    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.rc_wash_list)
    XRecyclerView serviceList;
    @BindView(R.id.tv_wash_damend)
    TextView washDamend;

    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private View rootView;
    private BannerListPresenter bannerListPresenter;
    private ServiceListPresneter serviceListPresneter;
    private TradeLinePresenter tradeLinePresenter;
    private ServiceListAdapter serviceListAdapter;
    private List<BannerListResponseBean.BannerListEntity> banners = new ArrayList<>();
    MainActivity activity;
    private List<ServiceListResponseBean.ServiceListEntity> listEntities = new ArrayList<>();
    private int count = 0;//factoryList计数
    private boolean isRefresh = false;//是否刷新

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);
        serviceListPresneter = new ServiceListPresneter(new ServiceListModel());
        serviceListPresneter.attachView(this);
        tradeLinePresenter = new TradeLinePresenter();
        tradeLinePresenter.attachView(this);
        showLoadingDialog();
        requestNetwork();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_1, container, false);
            unbinder = ButterKnife.bind(this, rootView);
        }
        alertChainDialog = new AlertChainDialog(getBaseActivity());
        initView();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity) {
            this.activity = (MainActivity) activity;
        }
    }

    private void initView() {

        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, BannerListResponseBean.BannerListEntity>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, BannerListResponseBean.BannerListEntity model, int position) {
                GlidImageManager.getInstance().loadImageView(getBaseActivity(), model.getImg(), itemView, R.drawable.holder);
            }
        });

        serviceListAdapter = new ServiceListAdapter(activity, listEntities);
        serviceList.setArrowImageView(R.drawable.iconfont_downgrey);
        serviceList.setLayoutManager(new LinearLayoutManager(getActivity()));
        serviceList.setAdapter(serviceListAdapter);
        serviceList.setPullRefreshEnabled(false);
        serviceList.setLoadingMoreEnabled(false);
//        serviceList.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                if (serviceListPresneter != null) {
//                    isRefresh = true;
//                    serviceListPresneter.getServiceList(getBaseActivity());
//                }
//            }
//
//            @Override
//            public void onLoadMore() {
//                if (factoryListPresenter != null) {
//                    isRefresh = false;
//                    factoryListPresenter.getFactoryList(getBaseActivity(), count);
//                }
//            }
//        });
        serviceListAdapter.setOnItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("fsid", listEntities.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getBaseActivity(), ServiceDetailActivity.class);

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
//                        intent.setClass(getBaseActivity(), EnterpriseActivity.class);
                    } else if (type.equals("goods_list")) {//商品列表
                        intent.setClass(getBaseActivity(), GoodsListsActivity.class);
                    } else if (type.equals("goods_detail")) {//商品详情
                        intent.setClass(getBaseActivity(), GoodsDetailActivity.class);
                    } else if (type.equals("info_detail")) {//资讯详情
                        intent.setClass(getBaseActivity(), InfoDetailActivity.class);
                    } /*else if () {//抽奖活动详情  待定

                    }*/
                    if (!TextUtils.isEmpty(id)) intent.putExtra("id", id);
                    intent.putExtra("target", Constants.TAB_TITLE[0]);
                    startActivity(intent);
                }
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
        showLoadingDialog();
        requestNetwork();
    }

    public void requestNetwork() {
        if (bannerListPresenter != null) {
            bannerListPresenter.getBannerList(getBaseActivity(), 3, 1);
        }
        if (serviceListPresneter != null) {
            serviceListPresneter.getServiceList(activity);
        }
        if (tradeLinePresenter != null) {
            tradeLinePresenter.getTradeLine(getBaseActivity(), "month");
        }
        //showLoadingDialog();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bannerListPresenter != null) bannerListPresenter.detachView();
        if (serviceListPresneter != null) serviceListPresneter.detachView();
        if (tradeLinePresenter != null) tradeLinePresenter.detachView();

    }


    @Override
    public void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list) {
        banners.clear();
        banners.addAll(list);
        mContentBanner.setData(banners, null);

    }

    @Override
    public void onBannerListFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean) {
        if (tradeLineResponseBean != null) {
            final List<TradeLineResponseBean.ChartDataEntity> data = tradeLineResponseBean.getData();
            final List<Entry> entries = ChartUtil1.getEntries(data);
            final List<String> xaxis = tradeLineResponseBean.getXasix();
            final List<Long> longXaxis = ChartUtil1.getLongXaxis(xaxis);
            ChartUtil1.initLineChart(lineChart, entries, longXaxis, "month");
        }
    }

    @Override
    public void onTradeLineFailed(String msg) {
        ToastUtils.showCustomToast(msg, 0);
    }

    @Override
    public void onServiceListSuccess(List<ServiceListResponseBean.ServiceListEntity> serviceListEntities) {
        if (serviceListEntities != null) {
            listEntities.clear();
            listEntities.addAll(serviceListEntities);
            count = listEntities.size();
            serviceListAdapter.notifyDataSetChanged();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadingDialog();
            }
        },500);
    }

    @Override
    public void onServiceListFailed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);
    }
}
