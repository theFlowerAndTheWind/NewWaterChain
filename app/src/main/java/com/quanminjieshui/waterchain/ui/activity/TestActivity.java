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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.ui.widget.chart.ChartUtil;

import java.util.HashMap;
import java.util.List;

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
    EditText edt;
    Button btnRequest;
    Button btnShow;

    LineChart lineChart;
    View[] views;
    private TradeLineResponseBean tradeLineResponseBean;
    private String tradeLineType = "today";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRequest = findViewById(R.id.btn_request);
        btnShow = findViewById(R.id.btn_show);
        edt = findViewById(R.id.edt);
        lineChart = (LineChart) findViewById(R.id.lineChart);


//        String time="2019-01-07 15:12:00";
//        final long l = ChartUtil.time2MilliseSecond(time);
//        final String string = ChartUtil.long2String(l, "");
//        LogUtils.e("tag",l+"  ********** "+string);

    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_test);


    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.btn_request, R.id.btn_show})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.btn_request:
                tradeLineType = edt.getText().toString();
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
                            }

                            @Override
                            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {

                            }
                        });


                break;

            case R.id.btn_show:
                if (tradeLineResponseBean != null) {
                    final List<TradeLineResponseBean.ChartDataEntity> data = tradeLineResponseBean.getData();
                    final List<String> xaxis = tradeLineResponseBean.getXasix();
                    final List<Entry> entries = ChartUtil.getEntries(data);
                    final List<Long> longXaxis = ChartUtil.getLongXaxis(xaxis);
                    initLineChart(entries, longXaxis);
                }
                break;
        }
    }


    private void initLineChart(List<Entry> entries, List<Long> longXaxis) {
        //显示边界
        lineChart.setDrawBorders(false);

        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#3DAAFF"));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
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
        xAxis.setLabelCount(longXaxis.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(ChartUtil.getXaxisMinimum(longXaxis));
        xAxis.setAxisMaximum(ChartUtil.getXaxisMaximum(longXaxis, tradeLineType));
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                final String string = ChartUtil.MilliseSecond2DateString((long) value, tradeLineType);
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

        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }


}