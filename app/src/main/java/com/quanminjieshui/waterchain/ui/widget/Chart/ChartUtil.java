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

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.util.Log;
import android.util.TimeUtils;

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
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;
import com.quanminjieshui.waterchain.beans.TradeLineResponseBean;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

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




    public static void initLineChart(LineChart lineChart, List<Entry> entries, final String type) {
        for (Entry entry : entries) {
            Log.e("tag", String.valueOf(entry.getX()) + "******" + entry.getY());
        }


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
                    Log.e("tag","tdate:"+tdate+"   price:"+price+"   "+MilliseSecond2DateString(tdate,"today"));
                }
            }
            if (xasix != null && xasix.size() > 0) {
                for (String x : xasix) {
                    long xTDate = time2MilliseSecond(x);
                    if (!map.containsKey(xTDate)) {
//                        map.put(xTDate, -1.0f);//遇到y为0时，x轴显示；遇到x==xasix中数值时，x轴显示
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
            } else if (time.length()== 19) {
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
        String pattern = "yyyy-MM-dd HH:mm";

        if (type.equals("today")) {
            pattern = "yyyy-MM-dd HH:mm";
        } else if (type.equals("week") || type.equals("year")) {
            pattern = "yyyy-MM-dd";
        }
        Date date = new Date(millisecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


}