package com.quanminjieshui.waterchain.utils;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.WaterChainApplication;

/**
 * Created by WanghongHe on 2018/11/9 10:40.
 * 统一应用工具类
 */

public class ToastUtils {
    /**
     * 一般的toast提示
     *
     * @param textStr 提示信息
     */
    public static void showCustomToast(String textStr) {
        View layout = LayoutInflater.from(WaterChainApplication.getInstance()).inflate(R.layout.layout_toast, null);
        TextView text = (TextView) layout.findViewById(R.id.toast_text_tv);
        text.setText(textStr);
        Toast toast = new Toast(WaterChainApplication.getInstance());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    /**
     * 自定义顶部的toast提示
     *
     * @param message 提示信息
     * @param yOffset 向下的偏移量
     */
    public static void showCustomToastMsg(String message, int yOffset) {
        View layout = LayoutInflater.from(WaterChainApplication.getInstance()).inflate(R.layout.layout_discover_toast, null);
        TextView textView = (TextView) layout.findViewById(R.id.toast_msg_tv);
        textView.setText(message);
        Toast toast = new Toast(WaterChainApplication.getInstance());
        toast.setGravity(Gravity.TOP, 0, yOffset);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void showSuccessToast(String msg) {
        Toast toast = new Toast(WaterChainApplication.getInstance());
        View view = View.inflate(WaterChainApplication.getInstance(), R.layout.layout_view_toast, null);
        ((TextView) view.findViewById(R.id.tv_content)).setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);
        toast.show();
    }
}
