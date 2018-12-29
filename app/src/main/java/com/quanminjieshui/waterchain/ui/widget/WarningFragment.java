/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.widget
 * @ClassName: WarningFragment
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/26 10:27 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 10:27 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.ui.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.WaterChainApplication;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.ui.widget
 * @ClassName: WarningFragment
 * @Description: 提示类Dialog
 * @Author: sxt
 * @CreateDate: 2018/12/26 10:27 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/26 10:27 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("ValidFragment")
public class WarningFragment extends DialogFragment {
    private static final String TAG = "WarningFragment";

    private String msg;//提示内容
    private String title;//title
    private String positive;//positive文字
    private String negative;//negative文字
    private String tag;//区别标志
    private OnWarningDialogClickedListener mListener;

    public WarningFragment(String title, String msg, String positive, String negative, String tag, OnWarningDialogClickedListener mListener) {
        this.msg = msg;
        this.title = title;
        this.positive = positive;
        this.negative = negative;
        this.tag = tag;
        this.mListener = mListener;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveClicked(tag);
                    }
                })
                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onNegativeClicked(tag);
                    }
                })
                .create();
        dialog.show();//不执行该步骤，下面操作会报错
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getActivity().getResources().getColor(R.color.primary_blue));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getActivity().getResources().getColor(R.color.primary_blue));
        return dialog;
    }

    public interface OnWarningDialogClickedListener {
        public void onPositiveClicked(String tag);

        public void onNegativeClicked(String tag);
    }
}
