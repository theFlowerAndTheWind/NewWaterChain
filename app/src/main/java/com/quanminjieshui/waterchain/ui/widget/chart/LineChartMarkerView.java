/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LineChartMarkerView
 * Author: sxt
 * Date: 2019/1/4 2:13 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.quanminjieshui.waterchain.ui.widget.chart;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.quanminjieshui.waterchain.R;

import java.text.DecimalFormat;

/**
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.widget.Chart
 * @ClassName: LineChartMarkerView
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/4 2:13 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/4 2:13 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LineChartMarkerView extends MarkerView {

    private TextView tvContent;
    private DecimalFormat format = new DecimalFormat("##0");

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     */
    public LineChartMarkerView(Context context) {
        super(context, R.layout.layout_marker_view);
        tvContent=findViewById(R.id.tvContent);
    }

    //显示的内容
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(format(e.getX())+"\n"+format.format(e.getY())+"辆");
        super.refreshContent(e, highlight);
    }

    //标记相对于折线图的偏移量
    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }

    //时间格式化（显示今日往前30天的每一天日期）
    public String  format(float x)
    {
        CharSequence format = DateFormat.format("MM月dd日",
                System.currentTimeMillis()-(long) (30-(int)x)*24*60*60*1000);
        return format.toString();
    }
}

