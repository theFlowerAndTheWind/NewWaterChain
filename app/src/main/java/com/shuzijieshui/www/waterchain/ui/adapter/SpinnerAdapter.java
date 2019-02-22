/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.adapter
 * @ClassName: SpinnerAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 10:56 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 10:56 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.shuzijieshui.www.waterchain.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;

import java.util.List;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.ui.adapter
 * @ClassName: SpinnerAdapter
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/25 10:56 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/25 10:56 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SpinnerAdapter extends ArrayAdapter {
    private Context context;
    private List<String> items;

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.items = objects;
        this.context = context;
    }

    //列表中的文字
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    android.R.layout.simple_spinner_item, parent, false);
        }

        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(items.get(position));
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(context.getResources().getColor(R.color.text_black));
        tv.setTextSize(14);//单位为sp
        return convertView;
    }

    //框内的文字
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(
                    android.R.layout.simple_spinner_item, parent, false);
        }

        // android.R.id.text1 is default text view in resource of the android.
        // android.R.layout.simple_spinner_item is default layout in resources of android.

        TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
        tv.setText(items.get(position));
        tv.setGravity(Gravity.LEFT);
        tv.setTextColor(context.getResources().getColor(R.color.text_gray));
        tv.setTextSize(14);
//        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(20, 0, 0, 0);
//        tv.getParent()
        return convertView;
    }
}
