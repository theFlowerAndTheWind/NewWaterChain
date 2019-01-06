package com.quanminjieshui.waterchain.ui.fragment;


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
import com.quanminjieshui.waterchain.beans.ServiceListResponseBean;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.BannerListPresenter;
import com.quanminjieshui.waterchain.contract.presenter.ServiceListPresneter;
import com.quanminjieshui.waterchain.contract.presenter.TradeLinePresenter;
import com.quanminjieshui.waterchain.contract.view.BannerListViewImpl;
import com.quanminjieshui.waterchain.contract.view.ServiceListViewImpl;
import com.quanminjieshui.waterchain.contract.view.TradeLineViewImpl;
import com.quanminjieshui.waterchain.ui.activity.FactoryServiceActivity;
import com.quanminjieshui.waterchain.ui.activity.WebViewActivity;
import com.quanminjieshui.waterchain.ui.adapter.ServiceListAdapter;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.ui.widget.Chart.ChartUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.TimeUtils;
import com.quanminjieshui.waterchain.utils.image.GlidImageManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by WanghongHe on 2018/12/3 10:54.
 * 首页
 */

public class HomeFragment extends BaseFragment implements BannerListViewImpl,ServiceListViewImpl,TradeLineViewImpl {


    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.rc_wash_list)
    XRecyclerView serviceList;

    private AlertChainDialog alertChainDialog;
    private Unbinder unbinder;
    private View rootView;
    private BannerListPresenter bannerListPresenter;
    private ServiceListPresneter serviceListPresneter;
    private TradeLinePresenter tradeLinePresenter;
    private ServiceListAdapter serviceListAdapter;
    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<String> imgUrlList = new ArrayList<>();
    List<TradeLineResponseBean.ChartDataEntity> tradeDataList = new ArrayList<>();
    List<String> tradeXasix = new ArrayList<>();
    private List<ServiceListResponseBean.serviceListEntity> listEntities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);
        serviceListPresneter = new ServiceListPresneter();
        serviceListPresneter.attachView(this);
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
        initLineChart();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void initList() {
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                GlidImageManager.getInstance().loadImageView(getBaseActivity(),model,itemView,R.drawable.holder);
            }
        });

        serviceListAdapter = new ServiceListAdapter(getBaseActivity(),listEntities);
        serviceList.setArrowImageView(R.drawable.iconfont_downgrey);
        serviceList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        serviceList.addItemDecoration(new RecyclerViewDivider(getBaseActivity(),LinearLayoutManager.HORIZONTAL,1,getResources().getColor(R.color.item_line)));
        serviceList.setAdapter(serviceListAdapter);
        serviceList.setPullRefreshEnabled(false);
        serviceList.setLoadingMoreEnabled(false);
        serviceList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (serviceListPresneter != null) {
                    serviceListPresneter.getServiceList(getBaseActivity());
                }
            }

            @Override
            public void onLoadMore() {
                if (serviceListPresneter != null) {
                    serviceListPresneter.getServiceList(getBaseActivity());
                }
            }
        });
        serviceListAdapter.setOnItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("fsid",listEntities.get(position).getId());
                intent.putExtras(bundle);
                intent.setClass(getBaseActivity(),FactoryServiceActivity.class);
                startActivity(intent);

            }
        });
    }

    public void initLineChart(){

        Description description =new Description();//创建描述信息
        description.setEnabled(false);//隐藏x轴描述信息
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("暂无数据");//没有数据时显示的文字
        lineChart.setNoDataTextColor(getResources().getColor(R.color.primary_blue));//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        lineChart.setBackgroundColor(getResources().getColor(R.color.white));
        lineChart.setGridBackgroundColor(getResources().getColor(R.color.white));
//        lineChart.setViewPortOffsets(20, 0, 20, 0);//强制设置view的显示区域
        lineChart.setDragEnabled(true);// 拖拽禁止
        lineChart.setScaleEnabled(true);// 缩放禁止
        Legend l = lineChart.getLegend();//图例
        l.setEnabled(false);   //是否使用 图例
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                Intent intent = new Intent();
                intent.setClass(getBaseActivity(), WebViewActivity.class);
                intent.putExtra("URL",imgUrlList.get(position));
                intent.putExtra("title",nameList.get(position));
                startActivity(intent);
            }
        });
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
        if(serviceListPresneter!=null){
            serviceListPresneter.getServiceList(getBaseActivity());
        }
        if(tradeLinePresenter!=null){
            tradeLinePresenter.getTradeLine(getBaseActivity(),"week");//首页需要展示当天的--暂时没有数据
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
        if(serviceListPresneter!=null){
            serviceListPresneter.detachView();
        }
        if(tradeLinePresenter!=null){
            tradeLinePresenter.detachView();
        }
    }

    @Override
    public void onTradeLineSuccess(TradeLineResponseBean tradeLineResponseBean) {
        tradeDataList = tradeLineResponseBean.getData();
        tradeXasix = tradeLineResponseBean.getXasix();
        LogUtils.d("tradeDataList",tradeDataList);
        LogUtils.d("tradeXasix",tradeXasix);
        setLineChartData();
    }

    @Override
    public void onTradeLineFailed(String msg) {

    }

    /**
     *  Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
     * "price":"0.00392",
     * "tdate":"2019-01-04 14:01:00"
     */
    public void setLineChartData(){


        ArrayList<Entry> values1 = new ArrayList<>();
        for(TradeLineResponseBean.ChartDataEntity tradeData:tradeDataList){
            Float x = Float.valueOf(TimeUtils.date2TimeStamp(tradeData.getTdate(),"yyyy-MM-dd HH:mm:ss"));
            values1.add(new Entry(x,tradeData.getPrice()));
            // TODO: 2019/1/7 时间转换
        }

        LineDataSet lineDataSet;//LineDataSet每一个对象就是一条连接线

        //判断图表中原来是否有数据
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            lineDataSet = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values1);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            lineDataSet = new LineDataSet(values1, "");
            lineDataSet.setColor(getResources().getColor(R.color.primary_blue));
            lineDataSet.setCircleColor(getResources().getColor(R.color.primary_blue));
            lineDataSet.setDrawCircles(true);
            lineDataSet.setLineWidth(1f);//设置线宽
            lineDataSet.setCircleRadius(1f);//设置焦点圆心的大小
            lineDataSet.setCircleColor(getResources().getColor(R.color.primary_blue));
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            lineDataSet.setHighlightLineWidth(1f);//设置点击交点后显示高亮线宽
            lineDataSet.setHighlightEnabled(true);//是否禁用点击高亮线
            lineDataSet.setHighLightColor(getResources().getColor(R.color.actionsheet_gray));//设置点击交点后显示交高亮线的颜色
            lineDataSet.setValueTextSize(0f);//设置显示值的文字大小 0则不展示
            lineDataSet.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据--精度
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            lineDataSet.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });
            if (Utils.getSDKInt() >= 18) {// fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getBaseActivity(), R.drawable.btn_red_bg_selector);
                lineDataSet.setFillDrawable(drawable);//设置范围背景填充
            } else {
                lineDataSet.setFillColor(Color.BLACK);
            }

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet); // add the datasets
            LineData data = new LineData(dataSets);//创建LineData对象 属于LineChart折线图的数据集合
            lineChart.setData(data);// 添加到图表中
            lineChart.invalidate();//绘制图表

            //获取此图表的x轴
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setDrawAxisLine(true);//是否绘制轴线
            xAxis.setDrawGridLines(false);//设置x轴上每个点对应的线
            xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
            xAxis.setTextColor(Color.BLACK);//设置字体颜色
            xAxis.enableGridDashedLine(10f, 5f, 0f);//设置竖线的显示样式为虚线(lineLength控制虚线段的长度|spaceLength控制线之间的空间)
            xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
            xAxis.setLabelRotationAngle(0f);//设置x轴标签的旋转角度
            xAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return ChartUtil.MilliseSecond2DateString((long) value,"today");
                }
            });

            //获取右边的轴线
            YAxis rightAxis=lineChart.getAxisRight();
            rightAxis.setEnabled(false);//设置图表右边的y轴禁用
            YAxis leftAxis = lineChart.getAxisLeft();//获取左边的轴线
            leftAxis.enableGridDashedLine(10f, 5f, 0f);//设置网格线为虚线效果
            leftAxis.setDrawZeroLine(false);//是否绘制0所在的网格线
            leftAxis.setDrawAxisLine(false);//是否绘制y轴线
            leftAxis.setValueFormatter(new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return String.valueOf(value)+"t";
                }
            });
        }
    }

    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
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
            mContentBanner.setData(imgList, nameList);
        }

    }

    @Override
    public void onBannerListFailed(String msg) {
        dismissLoadingDialog();
    }

    @Override
    public void onServiceListSuccess(List<ServiceListResponseBean.serviceListEntity> serviceListEntities) {
        dismissLoadingDialog();
        listEntities.clear();
        listEntities.addAll(serviceListEntities);
        serviceListAdapter.notifyDataSetChanged();
        serviceList.refreshComplete();
//        serviceList.loadMoreComplete();
    }

    @Override
    public void onServiceListFailed(String msg) {
        dismissLoadingDialog();
        LogUtils.d("serviceListEntities；"+msg);

    }
}
