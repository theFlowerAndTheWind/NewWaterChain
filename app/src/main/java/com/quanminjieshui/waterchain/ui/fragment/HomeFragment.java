package com.quanminjieshui.waterchain.ui.fragment;


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

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.beans.ServiceListResponseBean;
import com.quanminjieshui.waterchain.contract.presenter.BannerListPresenter;
import com.quanminjieshui.waterchain.contract.presenter.ServiceListPresneter;
import com.quanminjieshui.waterchain.contract.view.BannerListViewImpl;
import com.quanminjieshui.waterchain.contract.view.ServiceListViewImpl;
import com.quanminjieshui.waterchain.ui.adapter.ServiceListAdapter;
import com.quanminjieshui.waterchain.ui.view.AlertChainDialog;
import com.quanminjieshui.waterchain.utils.LogUtils;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by WanghongHe on 2018/12/3 10:54.
 * 首页
 */

public class HomeFragment extends BaseFragment implements BannerListViewImpl,ServiceListViewImpl {


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
    private ServiceListAdapter serviceListAdapter;
    private List<ServiceListResponseBean.serviceListEntity> listEntities = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bannerListPresenter = new BannerListPresenter();
        bannerListPresenter.attachView(this);
        serviceListPresneter = new ServiceListPresneter();
        serviceListPresneter.attachView(this);

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
                Glide.with(getBaseActivity())
                        .load(model)
                        .placeholder(R.drawable.holder)
                        .error(R.drawable.holder)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });

        mContentBanner.setData(Arrays.asList("网络图片路径1", "网络图片路径2", "网络图片路径3"), Arrays.asList("提示文字1", "提示文字2", "提示文字3"));

        serviceListAdapter = new ServiceListAdapter(getBaseActivity(),listEntities);
        serviceList.setArrowImageView(R.drawable.iconfont_downgrey);
        serviceList.setLayoutManager(new LinearLayoutManager(getActivity()));
//        serviceList.addItemDecoration(new RecyclerViewDivider(getBaseActivity(),LinearLayoutManager.HORIZONTAL,1,getResources().getColor(R.color.item_line)));
        serviceList.setAdapter(serviceListAdapter);

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
                ToastUtils.showCustomToast("立即下单跳转"+position);

            }
        });
    }

    public void initLineChart(){
        //创建描述信息
        Description description =new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息
        lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(true);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        lineChart.setBackgroundColor(getResources().getColor(R.color.white));
        lineChart.setGridBackgroundColor(getResources().getColor(R.color.white));
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘

        /**
         * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
         */
        ArrayList<Entry> values1 = new ArrayList<>();

        values1.add(new Entry(4,10));
        values1.add(new Entry(6,15));
        values1.add(new Entry(9,20));
        values1.add(new Entry(12,5));
        values1.add(new Entry(15,30));

        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;
        LineDataSet set2;

        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "测试数据1");
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            set1.setValueFormatter(new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(getBaseActivity(), R.drawable.btn_red_bg_selector);
                set1.setFillDrawable(drawable);//设置范围背景填充
            } else {
                set1.setFillColor(Color.BLACK);
            }

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();

            //获取此图表的x轴
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setEnabled(false);//设置轴启用或禁用 如果禁用以下的设置全部不生效
            xAxis.setDrawAxisLine(true);//是否绘制轴线
            xAxis.setDrawGridLines(true);//设置x轴上每个点对应的线
            xAxis.setDrawLabels(true);//绘制标签  指x轴上的对应数值
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x轴的显示位置
            //xAxis.setTextSize(20f);//设置字体
            //xAxis.setTextColor(Color.BLACK);//设置字体颜色
            //设置竖线的显示样式为虚线
            //lineLength控制虚线段的长度
            //spaceLength控制线之间的空间
            xAxis.enableGridDashedLine(10f, 10f, 0f);
//        xAxis.setAxisMinimum(0f);//设置x轴的最小值
//        xAxis.setAxisMaximum(10f);//设置最大值
            xAxis.setAvoidFirstLastClipping(true);//图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
            xAxis.setLabelRotationAngle(10f);//设置x轴标签的旋转角度
//        设置x轴显示标签数量  还有一个重载方法第二个参数为布尔值强制设置数量 如果启用会导致绘制点出现偏差
//        xAxis.setLabelCount(10);
//        xAxis.setTextColor(Color.BLUE);//设置轴标签的颜色
//        xAxis.setTextSize(24f);//设置轴标签的大小
//        xAxis.setGridLineWidth(10f);//设置竖线大小
//        xAxis.setGridColor(Color.RED);//设置竖线颜色
//        xAxis.setAxisLineColor(Color.GREEN);//设置x轴线颜色
//        xAxis.setAxisLineWidth(5f);//设置x轴线宽度
//        xAxis.setValueFormatter();//格式化x轴标签显示字符

            /**
             * Y轴默认显示左右两个轴线
             */
            //获取右边的轴线
            YAxis rightAxis=lineChart.getAxisRight();
            //设置图表右边的y轴禁用
            rightAxis.setEnabled(false);
            //获取左边的轴线
            YAxis leftAxis = lineChart.getAxisLeft();
            //设置网格线为虚线效果
            leftAxis.enableGridDashedLine(10f, 10f, 0f);
            //是否绘制0所在的网格线
            leftAxis.setDrawZeroLine(false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(bannerListPresenter!=null){
            bannerListPresenter.getBannerList(getBaseActivity(),3,1);
        }
        if(serviceListPresneter!=null){
            serviceListPresneter.getServiceList(getBaseActivity());
        }
        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                ToastUtils.showCustomToast("点击了" + position);
            }
        });
//        showLoadingDialog();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(bannerListPresenter!=null){
                bannerListPresenter.getBannerList(getBaseActivity(),3,1);
            }
            if(serviceListPresneter!=null){
                serviceListPresneter.getServiceList(getBaseActivity());
            }
            mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
                @Override
                public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                    ToastUtils.showCustomToast("点击了" + position);
                }
            });
//            showLoadingDialog();
        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(bannerListPresenter!=null){
                bannerListPresenter.getBannerList(getBaseActivity(),3,1);
            }
            if(serviceListPresneter!=null){
                serviceListPresneter.getServiceList(getBaseActivity());
            }
            mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
                @Override
                public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                    ToastUtils.showCustomToast("点击了" + position);
                }
            });
//            showLoadingDialog();
        }
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
    }

    @Override
    public void onReNetRefreshData(int viewId) {
        if(bannerListPresenter!=null){
            bannerListPresenter.getBannerList(getBaseActivity(),3,1);
        }
        if(serviceListPresneter!=null){
            serviceListPresneter.getServiceList(getBaseActivity());
        }
        dismissLoadingDialog();
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
    public void onBannerListSuccess(List<Object> list) {
        dismissLoadingDialog();
        LogUtils.d(list.toArray());

    }

    @Override
    public void onBannerListFailed(String msg) {
        dismissLoadingDialog();
    }

    @Override
    public void onServiceListSuccess(List<ServiceListResponseBean.serviceListEntity> serviceListEntities) {
        dismissLoadingDialog();
        LogUtils.d("serviceListEntities；"+serviceListEntities.toArray());
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
