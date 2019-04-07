/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ChartUtil
 * Author: sxt
 * Date: 2019/1/4 6:00 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.shuzijieshui.www.waterchain.ui.widget.chart;

import android.graphics.Color;
import android.util.Log;

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
import com.shuzijieshui.www.waterchain.beans.TradeLineResponseBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class ChartUtil1 {

    public static void initLineChart(LineChart lineChart, List<Entry> entries, List<Long> longXaxis, final String tradeLineType) {
        //显示边界
        lineChart.setDrawBorders(false);

        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, null);


        //线颜色
        lineDataSet.setColor(Color.parseColor("#3DAAFF"));
        lineDataSet.setCircleColor(Color.parseColor("#3DAAFF"));
        lineDataSet.setDrawCircles(true);
        lineDataSet.setLineWidth(1f);//设置线宽
        lineDataSet.setCircleRadius(1f);//设置焦点圆心的大小

        LineData data = new LineData(lineDataSet);
        //无数据时显示的文字
        lineChart.setNoDataText("暂无数据");
        //折线图不显示数值
        data.setDrawValues(false);
        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setTextSize(8f);//设置文字大小
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        float granularity=1f;
        if(tradeLineType.equals("today")){
        }else if(tradeLineType.equals("week")){
        }else if(tradeLineType.equals("month")){
            granularity=1000f*60*60*24*28;//
        }else if(tradeLineType.equals("year")){
        }
        xAxis.setGranularity(granularity);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(longXaxis.size(), false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(longXaxis.get(0));
//        xAxis.setAxisMaximum(longXaxis.get(longXaxis.size()-1));
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        if(longXaxis.size()>6){
            xAxis.setLabelRotationAngle(45);
        }
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                final String string = MilliseSecond2DateString((long) value, tradeLineType);
                return string;
            }
        });

        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false); //右侧Y轴不显示

        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setTextSize(8f);//设置文字大小
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置y轴坐标之间的最小间隔
        yAxis.setGranularity(0.00001f);
        //设置y轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
//        yAxis.setLabelCount(6, false);
        //设置y轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(getMaxYValue(entries));
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置y轴值为字符串
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.format("%.5fT",value);
            }
        });

        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);

        lineChart.setDragEnabled(false);// 拖拽禁止
        lineChart.setScaleEnabled(false);// 缩放禁止
        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }

    public static List<Entry> getEntries(List<TradeLineResponseBean.ChartDataEntity> data) {
        List<Entry> list = new ArrayList<>();

        if (data != null) {
            for (TradeLineResponseBean.ChartDataEntity chartDataEntity : data) {
                float tdate = (float) time2MilliseSecond(chartDataEntity.getTdate());
                float price = Float.valueOf(chartDataEntity.getPrice());
                list.add(new Entry(tdate, price));
            }
        }
        return list;
    }


    /**
     * 将"2019-01-01 00:00:00"/"2019-01-01"转为秒（float）
     * tradeLine  type为today时，xasix格式为2019-01-01 00:00:00
     * type为week或month时，格式为2019-01-01
     *
     * @param time
     * @return
     */
    public static long time2MilliseSecond(String time) {
        try {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            time.trim();
            if(time.length()==6){
                pattern="yyyyMM";
            }else if (time.length() == 10) {
                pattern = "yyyy-MM-dd";
            } else if (time.length() == 19) {
                pattern = "yyyy-MM-dd HH:mm:ss";
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat(pattern).parse(time));
            return calendar.getTimeInMillis();

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String MilliseSecond2DateString(long millisecond, String type) {
        String pattern = "yyyy-MM-dd hh:mm";

        if (type.equals("today")) {
            pattern = "hh:mm";
        } else if (type.equals("week")) {
            pattern = "MM-dd";
        } else if (type.equals("month")) {
            millisecond+=1296000000;//1000*60*60*24*15  以每月15号取数
            pattern = "yyyyMM";
        }else if(type.equals("year")){
            type.equals("year");
        }
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    /**
     * X轴最小值
     *
     * @param longXaxis
     * @return
     */
    public static long getXaxisMinimum(List<Long> longXaxis) {
        if (longXaxis != null) {
            if (longXaxis.size() > 0) {
                return longXaxis.get(0);//最小值
            } else {
                return 0;
            }
        }
        return 0;
    }

    /**
     * 将string的xasix转为long类型的
     *
     * @param xaxis 后台返回list<string>
     * @return
     */
    public static List<Long> getLongXaxis(List<String> xaxis) {
        List<Long> longXaxis = new LinkedList<>();
        for (String axis : xaxis) {
            long l = time2MilliseSecond(axis);
            longXaxis.add(l);
        }
        Collections.sort(longXaxis);
        return longXaxis;
    }

    private static float getMaxYValue(List<Entry> list){
        float temp=-1;
        for(Entry entry:list){
            float y = entry.getY();
            if(y>temp){
                temp=y;
            }
        }
        return temp;
    }

}