package com.shuzijieshui.www.waterchain.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;

import java.util.List;

public class NewAlertDialog extends Dialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private FrameLayout flContainer;
    private EditText edtAppeal, edtSafepw;
    private TextView tvTransInfo;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;
    private String type;//决定本次dialog显示内容
    public static final String[] TYPES = new String[]{"appeal", "safepw", "transInfo"};
    private String appealContent = "";
    private String safepwContent = "";


    private int msgColor = -1, titleColor = -1;

    public NewAlertDialog(Context context) {
        super(context);
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }


    public NewAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_alertdialog1, null);
        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);


        flContainer = view.findViewById(R.id.fl_container);
        edtAppeal = view.findViewById(R.id.edt_appeal);
        edtSafepw = view.findViewById(R.id.edt_safepw);
        edtAppeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                appealContent = edtAppeal.getText().toString();
            }
        });
        edtAppeal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                safepwContent = edtSafepw.getText().toString();
            }
        });
        tvTransInfo = view.findViewById(R.id.tv_trans_info);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), FrameLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public NewAlertDialog setType(String type) {
        flContainer.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(type)) {
            if (type.equals(TYPES[0])) {
                edtAppeal.setVisibility(View.VISIBLE);
                edtSafepw.setVisibility(View.GONE);
                tvTransInfo.setVisibility(View.GONE);
            } else if (type.equals(TYPES[1])) {
                edtAppeal.setVisibility(View.GONE);
                edtSafepw.setVisibility(View.VISIBLE);
                tvTransInfo.setVisibility(View.GONE);
            } else if (type.equals(TYPES[2])) {
                edtAppeal.setVisibility(View.GONE);
                edtSafepw.setVisibility(View.GONE);
                tvTransInfo.setVisibility(View.VISIBLE);
            }
        }

        return this;
    }

    public NewAlertDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText("标题");
        } else if (titleColor != -1) {
            txt_title.setText(title);
//            txt_title.setTextSize(DpPxSPUtils.sp2px(context,14));
            TextPaint tp = txt_title.getPaint();
            tp.setFakeBoldText(false);
            txt_title.setTextColor(titleColor);
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    /**
     * 自定义弹窗样式
     *
     * @param msgColor
     * @param titleColor
     */
    public void setMsgType(int msgColor, int titleColor) {
        this.msgColor = msgColor;
        this.titleColor = titleColor;
    }

    public NewAlertDialog setMsg(String msg) {

        SpannableString spannableString = new SpannableString(msg);
        if ("".equals(msg)) {
            txt_msg.setVisibility(View.GONE);
        } else {
            showMsg = true;
            txt_msg.setText(msg);
        }
        return this;
    }

    @Override
    public void setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
    }

    public NewAlertDialog setPositiveButton(String text, final View.OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public NewAlertDialog setNegativeButton(String text, final View.OnClickListener listener) {
        if (!TextUtils.isEmpty(text)) {
            showNegBtn = true;
            if ("".equals(text)) {
                btn_neg.setText("取消");
            } else {
                btn_neg.setText(text);
            }
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });

        return this;
    }

    public String getAppealContent() {
        return appealContent;
    }

    public void setAppealContent(String appealContent) {
        this.appealContent = appealContent;
    }

    public String getSafepwContent() {
        return safepwContent;
    }

    public void setSafepwContent(String safepwContent) {
        this.safepwContent = safepwContent;
    }

    public NewAlertDialog setTransInfo(String info) {
        tvTransInfo.setText(info);
        return this;
    }


    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_pos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

    }

    @Override
    public void show() {
        setLayout();
        dialog.show();
    }

    public boolean isShow() {
        if (dialog != null && dialog.isShowing()) {
            return true;
        }
        return false;
    }

    @Override
    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
