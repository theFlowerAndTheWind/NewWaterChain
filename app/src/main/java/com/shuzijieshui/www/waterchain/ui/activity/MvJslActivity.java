package com.shuzijieshui.www.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.model.MvJslModel;
import com.shuzijieshui.www.waterchain.contract.presenter.MvJslPresenter;
import com.shuzijieshui.www.waterchain.contract.view.MvJslViewImpl;
import com.shuzijieshui.www.waterchain.utils.ToastUtils;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class MvJslActivity extends BaseActivity implements MvJslViewImpl {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_cancle)
    ImageView imgCancle;
    @BindView(R.id.edt_user_login)
    EditText edtUserLogin;
    @BindView(R.id.edt_user_name)
    EditText edtUserName;
    @BindView(R.id.edt_total)
    EditText edtTotal;
    @BindView(R.id.tv_have)
    TextView tvHave;
    @BindView(R.id.edt_reason)
    EditText edtReason;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.trans_container)
    FrameLayout transContainer;

    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable grayBorder;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable redBorder;

    private String user_login;
    private String user_name;
    private String total;
    private String reason;

    private float jsl;

    private MvJslPresenter mvJslPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        getIntentExtra();
        mvJslPresenter = new MvJslPresenter(new MvJslModel());
        mvJslPresenter.attachView(this);
    }

    private void getIntentExtra() {
        Intent intent = getIntent();
        jsl = intent.getFloatExtra(UserAssetActivity.EXTRA_JSL, 0);
        tvHave.setText(jsl + "");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_mvjsl);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    private void setFullScreen() {
        //窗口对齐屏幕宽度
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;//设置对话框置顶显示
        win.setAttributes(lp);
    }

    @OnClick({R.id.btn_commit, R.id.img_cancle})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_commit:
                user_login = edtUserLogin.getText().toString();
                user_name = edtUserName.getText().toString();
                total = edtTotal.getText().toString();
                reason = edtReason.getText().toString();
                mvJsl();
                break;
            case R.id.img_cancle:
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        // 参数1：MainActivity进场动画，参数2：SecondActivity出场动画
        overridePendingTransition(0, R.anim.actionsheet_dialog_out);
    }

    private void mvJsl() {
        if (mvJslPresenter == null) {
            mvJslPresenter = new MvJslPresenter(new MvJslModel());
            mvJslPresenter.attachView(this);
        }

        if (TextUtils.isEmpty(user_login)) {
            edtUserLogin.setBackground(redBorder);
            edtUserLogin.setText("");
            return;
        }
        if (TextUtils.isEmpty(user_name)) {
            edtUserName.setBackground(redBorder);
            edtUserName.setText("");
            return;
        }
        if (TextUtils.isEmpty(reason)) {
            edtReason.setBackground(redBorder);
            edtReason.setText("");
            return;
        }
        try {
            if (!TextUtils.isEmpty(total)) {
                if (total.endsWith(".")) {
                    total = total + "0";
                }
                float totalFlt = Float.valueOf(total);
                if (totalFlt > jsl) {
                    ToastUtils.showCustomToastMsg("转账数量不可大于可用数量", 150);
                    edtTotal.setBackground(redBorder);
                    edtTotal.setText("");
                    return;
                }
            } else {
                edtTotal.setBackground(redBorder);
                edtTotal.setText("");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        showLoadingDialog();
        mvJslPresenter.mvJsl(this, user_login, user_name, total, reason);
        edtUserLogin.setBackground(grayBorder);
        edtUserName.setBackground(grayBorder);
        edtTotal.setBackground(grayBorder);
        edtReason.setBackground(grayBorder);
    }


    @Override
    public void success() {
        dismissLoadingDialog();
        ToastUtils.showCustomToast("申请成功，请耐心等待审核", 1);
    }

    @Override
    public void failed(String msg) {
        dismissLoadingDialog();
        ToastUtils.showCustomToast(msg, 0);

    }
}
