/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FindPassActivity
 * Author: sxt
 * Date: 2018/12/9 2:09 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.quanminjieshui.waterchain.ui.activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.model.FindPassModel;
import com.quanminjieshui.waterchain.contract.presenter.FindPassPresenter;
import com.quanminjieshui.waterchain.contract.view.FindPassViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ClassName: FindPassActivity
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/9 2:09 AM
 */
public class FindPassActivity extends BaseActivity implements FindPassViewImpl {

    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.img_title_left)
    ImageView img_title_left;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.edt_sms)
    EditText edt_sms;
    @BindView(R.id.tv_get_sms)
    TextView tv_get_sms;
    @BindView(R.id.edt_pwd)
    EditText edt_pwd;
    @BindView(R.id.edt_confirm)
    EditText edt_confirm;
    @BindView(R.id.btn_find)
    Button btn_find;


    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edt_border;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edt_border_illegal;


    @BindString(R.string.key_edt_name_mobile)
    String keyMobile;
    @BindString(R.string.key_edt_name_pwd)
    String keyPwd;
    @BindString(R.string.key_edt_name_sms)
    String keySms;

    private String mobile;
    private String sms;
    private String pwd;
    private String confirm;

    private FindPassPresenter presenter;

    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onFinish() {//计时完毕时触发
            tv_get_sms.setText("发送验证码");
            tv_get_sms.setEnabled(true);
            tv_get_sms.setBackground(getDrawable(R.drawable.blue_border_bg_shape));
            tv_get_sms.setTextColor(getResources().getColor(R.color.primary_blue));
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            tv_get_sms.setText(String.valueOf(millisUntilFinished / 1000));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FindPassPresenter(new FindPassModel());
        presenter.attachView(this);

        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    private void initView() {
        tv_title_center.setText("忘记密码");
    }

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_find_pass);
    }

    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @OnClick({R.id.tv_get_sms, R.id.btn_find,R.id.img_title_left})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_get_sms:
                mobile = edt_mobile.getText().toString();
                presenter.verify(mobile);
                presenter.getSms(this, mobile);
                break;
            case R.id.btn_find:
                mobile = edt_mobile.getText().toString();
                sms = edt_sms.getText().toString();
                pwd = edt_pwd.getText().toString();
                confirm = edt_confirm.getText().toString();
                presenter.verify(mobile, pwd, confirm, sms);
                presenter.reset(this, mobile, pwd, confirm, sms);
                break;
            case R.id.img_title_left:
                goBack(view);
                break;
            default:
                break;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onEdtContentsLegal() {
        edt_mobile.setBackground(edt_border);
        edt_sms.setBackground(edt_border);
        edt_pwd.setBackground(edt_border);
        edt_confirm.setBackground(edt_border);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onEdtContentsIllegal(Map<String, Boolean> verify) {
        if (verify.get(keyMobile) != null && !verify.get(keyMobile)) {
            edt_mobile.setBackground(edt_border_illegal);
            edt_mobile.setText("");
        } else {
            edt_mobile.setBackground(edt_border);
        }
        if (verify.get(keySms) != null && !verify.get(keySms)) {
            edt_sms.setBackground(edt_border_illegal);
            edt_sms.setText("");
        } else {
            edt_sms.setBackground(edt_border);
        }
        if (verify.get(keyPwd) != null && !verify.get(keyPwd)) {
            edt_pwd.setBackground(edt_border_illegal);
            edt_confirm.setBackground(edt_border_illegal);
            edt_pwd.setText("");
            edt_confirm.setText("");
        } else {
            edt_pwd.setBackground(edt_border);
            edt_confirm.setBackground(edt_border);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onGetSmsSuccess() {
        new FindPassActivity.TimeCount(61000, 1000).start();
        tv_get_sms.setEnabled(false);
        tv_get_sms.setBackground(getDrawable(R.drawable.blue_bg_shape));
        tv_get_sms.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onGetSmsFailed(String msg) {

    }

    @Override
    public void onFindPassSuccess() {

    }

    @Override
    public void onFindPassFaild(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
