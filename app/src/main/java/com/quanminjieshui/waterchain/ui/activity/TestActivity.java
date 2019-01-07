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
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.InfoListsResponseBean;
import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.ui.widget.Chart.ChartUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
    private TradeLineResponseBean tradeLineResponseBean;
    private String tradeLineType = "today";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRequest = findViewById(R.id.btn_request);
        lineChart = (LineChart) findViewById(R.id.lineChart);
        HashMap<String, Object> params = new HashMap<>();
        params.put("type", tradeLineType);
        RetrofitFactory.getInstance().createService()
                .tradeLine(RequestUtil.getRequestHashBody(params, false))
                .compose(TestActivity.this.<BaseEntity<TradeLineResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TradeLineResponseBean>>io())
                .subscribe(new BaseObserver<TradeLineResponseBean>(TestActivity.this) {

                    @Override
                    protected void onSuccess(TradeLineResponseBean bean) throws Exception {
                        tradeLineResponseBean = bean;
//                        dismissDialog();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                    }
                });
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

                List<Entry> datas = ChartUtil.getDatas(tradeLineResponseBean);
                initLineChart(datas);

                break;
        }
    }


    private void initLineChart(List<Entry> entries) {
        //显示边界
        lineChart.setDrawBorders(false);
        //设置数据
//        :tdate:
//        1546221660000 price:
//        0.00341
//        tdate:
//        1546225260000 price:
//        0.00376
//        tdate:
//        1546228860000 price:
//        0.00331
//        tdate:
//        1546232460000 price:
//        0.00319
//        tdate:
//        1546236060000 price:
//        0.00376
//        tdate:
//        1546239660000 price:
//        0.0032
//        tdate:
//        1546243260000 price:
//        0.00366
//        tdate:
//        1546246860000 price:
//        0.00376
//        tdate:
//        1546250460000 price:
//        0.00372
//        tdate:
//        1546254060000 price:
//        0.00331
//        tdate:
//        1546257660000 price:
//        0.0032
//        tdate:
//        1546261260000 price:
//        0.00345
//        tdate:
//        1546308060000 price:
//        0.00308
//        tdate:
//        1546311660000 price:
//        0.00319
//        tdate:
//        1546315260000 price:
//        0.00309
//        tdate:
//        1546318860000 price:
//        0.00376
//        tdate:
//        1546322460000 price:
//        0.00331
//        tdate:
//        1546326060000 price:
//        0.00302
//        tdate:
//        1546329660000 price:
//        0.00289
//        tdate:
//        1546333260000 price:
//        0.00376
//        tdate:
//        1546336860000 price:
//        0.00289
//        tdate:
//        1546340460000 price:
//        0.00324
//        tdate:
//        1546344060000 price:
//        0.00342
//        tdate:
//        1546347660000 price:
//        0.00321
//        tdate:
//        1546394460000 price:
//        0.00321
//        tdate:
//        1546398060000 price:
//        0.00349
//        tdate:
//        1546401660000 price:
//        0.00326
//        tdate:
//        1546405260000 price:
//        0.00306
//        tdate:
//        1546408860000 price:
//        0.00334
//        tdate:
//        1546412460000 price:
//        0.00348
//        tdate:
//        1546412460000 price:
//        0.00348
//        tdate:
//        1546416060000 price:
//        0.00322
//        tdate:
//        1546419660000 price:
//        0.00295
//        tdate:
//        1546419660000 price:
//        0.00295
//        tdate:
//        1546423260000 price:
//        0.00376
//        tdate:
//        1546426860000 price:
//        0.00289
//        tdate:
//        1546430460000 price:
//        0.00295
//        tdate:
//        1546434060000 price:
//        0.00376
//        tdate:
//        1546434060000 price:
//        0.00376
//        tdate:
//        1546480860000 price:
//        0.00361
//        tdate:
//        1546480860000 price:
//        0.00361
//        tdate:
//        1546484460000 price:
//        24.0
//        tdate:
//        1546484460000 price:
//        24.0
//        tdate:
//        1546488060000 price:
//        0.00366
//        tdate:
//        1546491660000 price:
//        0.00361
//        tdate:
//        1546495260000 price:
//        0.00299
//        tdate:
//        1546498860000 price:
//        0.00322
//        tdate:
//        1546502460000 price:
//        0.00322
//        tdate:
//        1546506060000 price:
//        124.0
//        tdate:
//        1546509660000 price:
//        0.00322
//        tdate:
//        1546513260000 price:
//        0.00389
//        tdate:
//        1546516860000 price:
//        0.00412
//        tdate:
//        1546520460000 price:
//        0.00342
//        tdate:
//        1546520460000 price:
//        0.00342
//        tdate:
//        1546567260000 price:
//        0.00411
//        tdate:
//        1546570860000 price:
//        0.00451
//        tdate:
//        1546574460000 price:
//        0.00439
//        tdate:
//        1546578060000 price:
//        0.00426
//        tdate:
//        1546581660000 price:
//        0.00392
//        tdate:
//        1546585260000 price:
//        0.00428
//        tdate:
//        1546588860000 price:
//        0.00381
//        tdate:
//        1546592460000 price:
//        0.00424
//        tdate:
//        1546596060000 price:
//        0.00419
//        tdate:
//        1546599660000 price:
//        0.0035
//        tdate:
//        1546603260000 price:
//        0.00347
//        tdate:
//        1546606860000 price:
//        0.00334
//        tdate:
//        1546653660000 price:
//        0.00293
//        tdate:
//        1546657260000 price:
//        0.00329
//        tdate:
//        1546660860000 price:
//        0.00294
//        tdate:
//        1546664460000 price:
//        0.0032
//        tdate:
//        1546668060000 price:
//        0.00281


//        tdate:
//        1546671660000 price:
//        0.00314
//        tdate:
//        1546675260000 price:
//        0.00327
//        tdate:
//        1546678860000 price:
//        0.00288
//        tdate:
//        1546682460000 price:
//        0.00299
//        tdate:
//        1546686060000 price:
//        0.00313
//        tdate:
//        1546689660000 price:
//        0.00317
//        tdate:
//        1546693260000 price:
//        0.00322
        entries.clear();
        entries.add(new Entry(6, 0.00314f));//2019-01-05 15:01
        entries.add(new Entry(7, 0.00327f));//2019-01-05 16:01
        entries.add(new Entry(8, 0.00288f));//2019-01-05 17:01
        entries.add(new Entry(9, 0.00299f));//2019-01-05 18:01
        entries.add(new Entry(10, 0.00313f));//2019-01-05 19:01
        entries.add(new Entry(11, 0.00319f));//2019-01-05 20:01
        entries.add(new Entry(14, 0.00222f));//2019-01-05 21:01
        entries.add(new Entry(15, 0.00322f));//2019-01-05 21:01
        entries.add(new Entry(16, 0.00422f));//2019-01-05 21:01
        entries.add(new Entry(18, 0.00322f));//2019-01-05 21:01
        entries.add(new Entry(20, 0.00321f));//2019-01-05 21:01
        entries.add(new Entry(23, 0.00312f));//2019-01-05 21:01
        entries.add(new Entry(25, 0.00311f));//2019-01-05 21:01
        entries.add(new Entry(28, 0.00345f));//2019-01-05 21:01
        entries.add(new Entry(30, 0.00354f));//2019-01-05 21:01


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
        xAxis.setGranularity(1f); // one hour
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(6, false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(5f);
        xAxis.setAxisMaximum(35f);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                Log.e("tag", "***********------" + (long)value);


//                final String string = ChartUtil.MilliseSecond2DateString((long) value, "today");
                final String string = "你好"+value;
                return string;
            }
        });
        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置Y轴坐标之间的最小间隔
//        yAxis.setGranularity(1);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
//        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
//        yAxis.setAxisMinimum(0f);
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
//        Legend legend = lineChart.getLegend();
//        //隐藏Lengend
//        legend.setEnabled(false);
//        //隐藏描述
//        Description description = new Description();
//        description.setEnabled(false);
//        lineChart.setDescription(description);
        //折线图点的标记
//        MyMarkerView mv = new MyMarkerView(this);
//        lineChart.setMarker(mv);
        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }


}