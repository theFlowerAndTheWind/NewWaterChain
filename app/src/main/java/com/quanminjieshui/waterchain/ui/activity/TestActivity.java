/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestActivity
 * Author: ccdc_android
 * Date: 2018/12/14 11:06 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.InfoListsResponseBean;
import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.test
 * @ClassName: TestActivity
 * @Description: 用于各接口测试
 * @Author: sxt
 * @CreateDate: 2018/12/14 11:06 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/14 11:06 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TestActivity extends BaseActivity {
//    @BindView(R.id.btn_request)
    Button btnRequest;

LineChart lineChart;
    View[] views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRequest = findViewById(R.id.btn_request);
        lineChart=(LineChart) findViewById(R.id.lineChart);
        initLineChart();
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_test);


    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_request})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btn_request:
//                HashMap<String, Object> params = new HashMap<>();
//                params.put("type","year");
//                RetrofitFactory.getInstance().createService()
//                        .tradeLine(RequestUtil.getRequestHashBody(params, false))
//                        .compose(TestActivity.this.<BaseEntity>bindToLifecycle())
//                        .compose(ObservableTransformerUtils.<BaseEntity>io())
//                        .subscribe(new BaseObserver(TestActivity.this) {
//
//                            @Override
//                            protected void onSuccess(Object bean) throws Exception {
////                                LogUtils.e("tag",
////                                        bean.getLists().get(0).getContent());
//                            }
//
//                            @Override
//                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//
//                            }
//                        });

                try {
                    String dateTime = "2016-12-31 00:00:00";
                    String date = "2016-12-31";
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));
                    final long timeInMillis = calendar.getTimeInMillis();
                    LogUtils.e("tag", "****2016-12-31 00:00:00****" + timeInMillis);
                    calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                    final long timeInMillis1 = calendar.getTimeInMillis();
                    LogUtils.e("tag", "****2016-12-31****" + timeInMillis1);
                } catch (ParseException e) {
                    e.printStackTrace();

                }

                break;
        }
    }


    private void initLineChart()
    {
        //显示边界
        lineChart.setDrawBorders(false);
        //设置数据
        List<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++)
//        {
//            entries.add(new Entry(i, (float) list.get(i)));
//        }


        entries.add(new Entry(1,2));
        entries.add(new Entry(1,3));
        entries.add(new Entry(1,5));
        entries.add(new Entry(2,1));
        entries.add(new Entry(3,3));
        entries.add(new Entry(3,2));
        entries.add(new Entry(3,6));


        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#F15A4A"));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //不显示圆点
//        lineDataSet.setDrawCircles(false);
        //线条平滑
//        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
        LineData data = new LineData(lineDataSet);
        //无数据时显示的文字
        lineChart.setNoDataText("暂无数据");
        //折线图不显示数值
        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
//        xAxis.setLabelCount(list.size() / 6, false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
//        xAxis.setAxisMaximum((float) list.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
//        xAxis.setValueFormatter(new IAxisValueFormatter()
//        {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis)
//            {
//                int IValue = (int) value;
//                CharSequence format = DateFormat.format("MM/dd",
//                        System.currentTimeMillis()-(long)(list.size()-IValue)*24*60*60*1000);
//                return format.toString();
//            }
//        });
        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
//        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
//        yAxis.setAxisMaximum(Collections.max(list) + 1);

        //y轴
//        yAxis.setValueFormatter(new IAxisValueFormatter()
//        {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis)
//            {
//                int IValue = (int) value;
//                return String.valueOf(IValue);
//            }
//        });
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //折线图点的标记
//        MyMarkerView mv = new MyMarkerView(this);
//        lineChart.setMarker(mv);
        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }



}