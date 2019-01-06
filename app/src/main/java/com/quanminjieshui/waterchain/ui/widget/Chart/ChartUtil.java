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

package com.quanminjieshui.waterchain.ui.widget.Chart;

import android.graphics.DashPathEffect;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void initLineChart(LineChart lineChart, List<Entry> entries, final String type) {
        for (Entry entry : entries) {
            Log.e("tag", String.valueOf(entry.getX()) + "******" + entry.getY());
        }
//        //显示边界
//        lineChart.setDrawBorders(false);
//
        //一个LineDataSet就是一条线
//        //线颜色
//        lineDataSet.setColor(Color.parseColor("#F15A4A"));
//        //线宽度
//        lineDataSet.setLineWidth(1.6f);
//        //不显示圆点
//        lineDataSet.setDrawCircles(false);
//        //线条平滑
//        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//        //设置折线图填充
////        lineDataSet.setDrawFilled(true);
//        //无数据时显示的文字
//        lineChart.setNoDataText("暂无数据");
//        //折线图不显示数值
//        data.setDrawValues(false);
//        //得到X轴
//        XAxis xAxis = lineChart.getXAxis();
//        //设置X轴的位置（默认在上方)
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        //设置X轴坐标之间的最小间隔
//        xAxis.setGranularity(1f);
////        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
////        xAxis.setLabelCount(list.size() / 6, false);
////        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
////        xAxis.setAxisMinimum(0f);
////        xAxis.setAxisMaximum((float) list.size());
//        //不显示网格线
//        xAxis.setDrawGridLines(false);
//        // 标签倾斜
//        xAxis.setLabelRotationAngle(45);
//        //设置X轴值为字符串
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
////                int IValue = (int) value;
////                CharSequence format = new SimpleFormatter.format("MM-dd\r\nHH:mm",
////                        System.currentTimeMillis() - (long) (list.size() - IValue) * 24 * 60 * 60 * 1000);
////                return format.toString();
//                if (value == -1.0f) {
//                    return MilliseSecond2DateString(value, type);
//                }
//                return "";
//            }
//        });
//        //得到Y轴
//        YAxis yAxis = lineChart.getAxisLeft();
//        YAxis rightYAxis = lineChart.getAxisRight();
//        //设置Y轴是否显示
//        rightYAxis.setEnabled(false); //右侧Y轴不显示
//        //设置y轴坐标之间的最小间隔
//        //不显示网格线
//        yAxis.setDrawGridLines(false);
//        //设置Y轴坐标之间的最小间隔
//        yAxis.setGranularity(1);
//        //设置y轴的刻度数量
////        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
////        yAxis.setLabelCount(Collections.max(list) + 2, false);
//        //设置从Y轴值
//        yAxis.setAxisMinimum(0f);
////        //+1:y轴多一个单位长度，为了好看
////        yAxis.setAxisMaximum(Collections.max(list) + 1);
//
//        //y轴
//        yAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
////                float IValue = (int) value;
//                return String.valueOf(value);
//            }
//        });
//        //图例：得到Lengend
//        Legend legend = lineChart.getLegend();
//        //隐藏Lengend
//        legend.setEnabled(false);
//        //隐藏描述
//        Description description = new Description();
//        description.setEnabled(false);
//        lineChart.setDescription(description);
//        //折线图点的标记
////        MyMarkerView mv = new MyMarkerView(this);
////        lineChart.setMarker(mv);

/*****************************************************/
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
//        xAxis.setLabelCount(list.size() / 6, false);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum((float) entries.size());
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(45);
        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            String pattern = "yyyy-MM-dd HH:mm";
            SimpleDateFormat mFormat;

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (pattern.equals("today")) {
                    pattern = "yyyy-MM-dd HH:mm";
                } else if (pattern.equals("week") || pattern.equals("year")) {
                    pattern = "yyyy-MM-dd";
                }
                mFormat = new SimpleDateFormat(pattern);
                if (value == -1.0f) {
                    Log.e("tag", "****************-1");
                    return mFormat.format(new Date((long) value));
                }

                return "";
            }
        });
/*************************************************************************/

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
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });
//        //图例：得到Lengend
//        Legend legend = lineChart.getLegend();
//        //隐藏Lengend
//        legend.setEnabled(false);
//        //隐藏描述
//        Description description = new Description();
//        description.setEnabled(false);
//        lineChart.setDescription(description);


/**************************************************************************/

        lineChart.setVisibleXRangeMaximum(20); // allow 20 values to be displayed at once on the x-axis, not more
        lineChart.moveViewToX(10); // set the left edge of the chart to x-index 10


        LineDataSet set = null;
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0) {
            set = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set.setValues(entries);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            set = new LineDataSet(entries, "");

            if (entries == null) return;
            set.setDrawIcons(false);
            set.enableDashedLine(10f, 5f, 0f);
            set.enableDashedHighlightLine(10f, 5f, 0f);
//            set.setColor(ColorTemplate.VORDIPLOM_COLORS[4 - i]);
//            set.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[4 - i]);
            set.setLineWidth(1f);
            set.setCircleRadius(3f);
            set.setDrawCircleHole(false);
            set.setValueTextSize(9f);
            //set1.setDrawFilled(true);
            set.setFormLineWidth(1f);
            set.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set.setFormSize(15.f);
            set.setDrawValues(false);

//            if (Utils.getSDKInt() >= 18) {
//                // fill drawable only supported on api level 18 and above
//                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
//                set1.setFillDrawable(drawable);
//            } else {
//                set1.setFillColor(Color.BLACK);
//            }

        }

        // create a data object with the datasets
        LineData data = new LineData(set);


        //设置数据
        lineChart.setData(data);
        //图标刷新
        lineChart.invalidate();
    }

    public static List<Entry> getDatas(TradeLineResponseBean bean) {

        List<Entry> list = new ArrayList<>();
        Map<Long, Float> map = new HashMap<>();
        if (bean != null) {
            List<TradeLineResponseBean.ChartDataEntity> data = bean.getData();
            List<String> xasix = bean.getXasix();
            if (data != null && data.size() > 0) {
                for (TradeLineResponseBean.ChartDataEntity chartDataEntity : data) {
                    long tdate = time2MilliseSecond(chartDataEntity.getTdate());
                    float price = Float.valueOf(chartDataEntity.getPrice());
                    map.put(tdate, price);
                }
            }
            if (xasix != null && xasix.size() > 0) {
                for (String x : xasix) {
                    long xTDate = time2MilliseSecond(x);
                    if (!map.containsKey(xTDate)) {
                        map.put(xTDate, -1.0f);//遇到y为0时，x轴显示；遇到x==xasix中数值时，x轴显示
                    }
                }
            }
            for (Map.Entry<Long, Float> entry : map.entrySet()) {
                list.add(new Entry(entry.getKey(), entry.getValue()));
            }
        }
//        Collections.sort(list);
        return list;
    }


    /**
     * 将"2019-01-01 00:00:00"/"2019-01-01"转为秒（float）
     * tradeLine  type为today时，xasix格式为2019-01-01 00:00:00
     * type为week活year时，格式为2019-01-01
     *
     * @param time
     * @return
     */
    public static long time2MilliseSecond(String time) {
        try {
            String pattern = "yyyy-MM-dd HH:mm:ss";
            time.trim();
            if (time.length() == 10) {//"weekyear"会快一些
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
        }else if(type.equals("year")){
            pattern = "yyyy-MM-dd";
        }
        Date date = new Date(millisecond / 1000);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


}