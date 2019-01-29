package com.quanminjieshui.waterchain.ui.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.BannerListResponseBean;
import com.quanminjieshui.waterchain.beans.FactoryListResponseBean;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.BannerListPresenter;
import com.quanminjieshui.waterchain.contract.presenter.FactoryListPresenter;
import com.quanminjieshui.waterchain.contract.presenter.TradeLinePresenter;
import com.quanminjieshui.waterchain.contract.view.BannerListViewImpl;
import com.quanminjieshui.waterchain.contract.view.FactoryListViewImpl;
import com.quanminjieshui.waterchain.contract.view.TradeLineViewImpl;
import com.quanminjieshui.waterchain.ui.activity.EnterpriseActivity;
import com.quanminjieshui.waterchain.ui.activity.MainActivity;
import com.quanminjieshui.waterchain.ui.activity.WebViewActivity;
import com.quanminjieshui.waterchain.ui.adapter.FactoryListIndexAdapter;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.ui.widget.chart.ChartUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.text.DecimalFormat;
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

public class HomeFragment extends BaseFragment implements BannerListViewImpl,FactoryListViewImpl,TradeLineViewImpl {


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

    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private View rootView;
    private BannerListPresenter bannerListPresenter;
    private FactoryListPresenter factoryListPresenter;
    private TradeLinePresenter tradeLinePresenter;
    private FactoryListIndexAdapter factoryListAdapter;
    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> imgUrlList = new ArrayList<>();
    MainActivity activity;
    private List<FactoryListResponseBean> listEntities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);
        factoryListPresenter = new FactoryListPresenter();
        factoryListPresenter.attachView(this);
        tradeLinePresenter = new TradeLinePresenter();
        tradeLinePresenter.attachView(this);
        requestNetwork();
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
        if (activity instanceof MainActivity){
            this.activity = (MainActivity) activity;
        }
    }

    @OnClick({R.id.tv_wash_damend,R.id.tv_transaction_center})
    public void OnClick(View view){
        activity.hideFragment();
        switch (view.getId()){
            case R.id.tv_wash_damend:
                activity.showWash();
                break;
            case R.id.tv_transaction_center:
                activity.showTransaction();
                break;
            default:break;
        }
    }

    private void initList() {
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlidImageManager.getInstance().loadImageView(getBaseActivity(),model,itemView,R.drawable.holder);
            }
        });

        factoryListAdapter = new FactoryListIndexAdapter(getBaseActivity(),listEntities);
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
                    factoryListPresenter.getFactoryList(getBaseActivity(),0);
                }
            }

            @Override
            public void onLoadMore() {
                if (factoryListPresenter != null) {
                    factoryListPresenter.getFactoryList(getBaseActivity(),0);
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
//产品要求不跳转
//        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
//            @Override
//            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
//                Intent intent = new Intent();
//                intent.setClass(getBaseActivity(), WebViewActivity.class);
//                intent.putExtra("URL",imgUrlList.get(position));
//                intent.putExtra("title",nameList.get(position));
//                startActivity(intent);
//            }
//        });
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            requestNetwork();
        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            requestNetwork();
        }
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        requestNetwork();
    }

    public void requestNetwork(){
        if(bannerListPresenter!=null){
            bannerListPresenter.getBannerList(getBaseActivity(),3,1);
        }
        if(factoryListPresenter!=null){
            factoryListPresenter.getFactoryList(getBaseActivity(),0);

        }
        if(tradeLinePresenter!=null){
            tradeLinePresenter.getTradeLine(getBaseActivity(),"today");
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
        if(bannerListPresenter!=null){
            bannerListPresenter.detachView();
        }
        if(factoryListPresenter!=null){
            factoryListPresenter.detachView();
        }
        if(tradeLinePresenter!=null){
            tradeLinePresenter.detachView();
        }
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

    }


    @Override
    public void onBannerListSuccess(List<BannerListResponseBean.BannerListEntity> list) {
        dismissLoadingDialog();
        imgList.clear();
        nameList.clear();
        imgUrlList.clear();
        for(BannerListResponseBean.BannerListEntity listEntity :list){
            imgList.add(listEntity.getImg());
            nameList.add(listEntity.getName());
            imgUrlList.add(listEntity.getUrl());
            mContentBanner.setData(imgList, null);
        }

    }

    @Override
    public void onBannerListFailed(String msg) {
        dismissLoadingDialog();
    }

    @Override
    public void onFactoryListSuccess(List<FactoryListResponseBean> factoryListEntities) {
        dismissLoadingDialog();
        listEntities.clear();
        listEntities.addAll(factoryListEntities);
        factoryListAdapter.notifyDataSetChanged();
        factoryList.refreshComplete();
//        factoryList.loadMoreComplete();
    }

    @Override
    public void onFactoryListFailed(String msg) {
        dismissLoadingDialog();
        LogUtils.d("factoryListEntities；"+msg);
    }
}
