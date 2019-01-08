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

package com.quanminjieshui.waterchain.ui.widget.chart;

import android.graphics.Color;

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
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.widget.Chart
 * @ClassName: ChartUtil
 * @Description: lineChart初始化、数据处理等操作
 * @Author: sxt
 * @CreateDate: 2019/1/4 6:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/4 6:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChartUtil {


//    /**
//     * 初始化图表
//     *
//     * @param chart 原始图表
//     * @return 初始化后的图表
//     */
//    public static LineChart initChart(LineChart chart) {
//        // 不显示数据描述
//        chart.getDescription().setEnabled(false);
//        // 没有数据的时候，显示“暂无数据”
//        chart.setNoDataText("暂无数据");
//        // 不显示表格颜色
//        chart.setDrawGridBackground(false);
//        // 不可以缩放
//        chart.setScaleEnabled(false);
//        // 不显示y轴右边的值
//        chart.getAxisRight().setEnabled(false);
//        // 不显示图例
//        Legend legend = chart.getLegend();
//        legend.setEnabled(false);
//        // 向左偏移15dp，抵消y轴向右偏移的30dp
//        chart.setExtraLeftOffset(-15);
//
//        XAxis xAxis = chart.getXAxis();
//        // 不显示x轴
//        xAxis.setDrawAxisLine(false);
//        // 设置x轴数据的位置
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTextColor(Color.WHITE);
//        xAxis.setTextSize(12);
//        xAxis.setGridColor(Color.parseColor("#30FFFFFF"));
//        // 设置x轴数据偏移量
//        xAxis.setYOffset(-12);
//
//        YAxis yAxis = chart.getAxisLeft();
//        // 不显示y轴
//        yAxis.setDrawAxisLine(false);
//        // 设置y轴数据的位置
//        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        // 不从y轴发出横向直线
//        yAxis.setDrawGridLines(false);
//        yAxis.setTextColor(Color.WHITE);
//        yAxis.setTextSize(12);
//        // 设置y轴数据偏移量
//        yAxis.setXOffset(30);
//        yAxis.setYOffset(-3);
//        yAxis.setAxisMinimum(0);
//
//        //Matrix matrix = new Matrix();
//        // x轴缩放1.5倍
//        //matrix.postScale(1.5f, 1f);
//        // 在图表动画显示之前进行缩放
//        //chart.getViewPortHandler().refresh(matrix, chart, false);
//        // x轴执行动画
//        //chart.animateX(2000);
//        chart.invalidate();
//        return chart;
//    }
//
//    /**
//     * 设置图表数据
//     *
//     * @param chart  图表
//     * @param values 数据
//     */
//    public static void setChartData(LineChart chart, List<Entry> values) {
//        LineDataSet lineDataSet;
//
//        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
//            lineDataSet = (LineDataSet) chart.getData().getDataSetByIndex(0);
//            lineDataSet.setValues(values);
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//        } else {
//            lineDataSet = new LineDataSet(values, "");
//            // 设置曲线颜色
//            lineDataSet.setColor(Color.parseColor("#FFFFFF"));
//            // 设置平滑曲线
//            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//            // 不显示坐标点的小圆点
//            lineDataSet.setDrawCircles(false);
//            // 不显示坐标点的数据
//            lineDataSet.setDrawValues(false);
//            // 不显示定位线
//            lineDataSet.setHighlightEnabled(false);
//
//            LineData data = new LineData(lineDataSet);
//            chart.setData(data);
//            chart.invalidate();
//        }
//    }
//
//    /**
//     * 更新图表
//     *
//     * @param chart     图表
//     * @param values    数据
//     * @param valueType 数据类型
//     */
//    public static void notifyDataSetChanged(LineChart chart, List<Entry> values,
//                                            final int valueType) {
//        chart.getXAxis().setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return xValuesProcess(valueType)[(int) value];
//            }
//        });
//
//        chart.invalidate();
//        setChartData(chart, values);
//    }
//
//    Dialog
//
//    /**
//     * x轴数据处理
//     *
//     * @param valueType 数据类型
//     * @return x轴数据
//     */
//    private static String[] xValuesProcess(int valueType) {
//        String[] week = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
//
//        if (valueType == dayValue) { // 今日
//            String[] dayValues = new String[7];
//            long currentTime = System.currentTimeMillis();
//            for (int i = 6; i >= 0; i--) {
//                dayValues[i] = TimeUtils.dateToString(currentTime, TimeUtils.dateFormat_day);
//                currentTime -= (3 * 60 * 60 * 1000);
//            }
//            return dayValues;
//
//        } else if (valueType == weekValue) { // 本周
//            String[] weekValues = new String[7];
//            Calendar calendar = Calendar.getInstance();
//            int currentWeek = calendar.get(Calendar.DAY_OF_WEEK);
//
//            for (int i = 6; i >= 0; i--) {
//                weekValues[i] = week[currentWeek - 1];
//                if (currentWeek == 1) {
//                    currentWeek = 7;
//                } else {
//                    currentWeek -= 1;
//                }
//            }
//            return weekValues;
//
//        } else if (valueType == monthValue) { // 本月
//            String[] monthValues = new String[7];
//            long currentTime = System.currentTimeMillis();
//            for (int i = 6; i >= 0; i--) {
//                monthValues[i] = TimeUtils.dateToString(currentTime, TimeUtils.dateFormat_month);
//                currentTime -= (4 * 24 * 60 * 60 * 1000);
//            }
//            return monthValues;
//        }
//        return new String[]{};
//    }

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

        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);


        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }

    public static List<Entry> getEntries(List<TradeLineResponseBean.ChartDataEntity> data) {
        List<Entry> list = new ArrayList<>();

        if (data != null) {
            for (TradeLineResponseBean.ChartDataEntity chartDataEntity : data) {
                long tdate = time2MilliseSecond(chartDataEntity.getTdate());
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
            if (time.length() == 10) {
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
        } else if (type.equals("month") || type.equals("year")) {
            pattern = "yy-MM-dd";
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
     * x轴最大值  该值不作为label，不显示在x轴上
     *
     * @param longXaxis
     * @param type
     * @return
     */
    public static long getXaxisMaximum(List<Long> longXaxis, String type) {
        if (longXaxis != null) {
            int size = longXaxis.size();
            if (size > 1) {
                long max = longXaxis.get(size - 1);
                long secondRateMax = longXaxis.get(size - 2);
                long interval = max - secondRateMax;
                return max + interval;
            } else if (size == 1) {
                if (type.equals("today")) {
                    return longXaxis.get(0) + 2 * 60 * 60 * 1000;//加两小时
                } else if (type.equals("week")) {
                    return longXaxis.get(0) + 24 * 60 * 60 * 1000;//加一天
                } else if (type.equals("month")) {
                    return longXaxis.get(0) + 31 * 24 * 60 * 60 * 1000;//加31天
                } else {
                    return longXaxis.get(0) + 24 * 60 * 60 * 1000;//加一天
                }
            } else {
                return System.currentTimeMillis();
            }
        }
        return System.currentTimeMillis();
    }

    public static int getXaxisLabelCount(List<String> xaxis) {
        final List<Long> longXaxis = getLongXaxis(xaxis);
        return longXaxis.size();//
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

}