package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.model.LoginModel;
import com.quanminjieshui.waterchain.contract.presenter.LoginPresenter;
import com.quanminjieshui.waterchain.contract.view.LoginViewImpl;
import com.quanminjieshui.waterchain.event.SelectFragmentEvent;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;
import com.quanminjieshui.waterchain.utils.ToastUtils;

import java.util.Map;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by WanghongHe on 2018/12/3 11:53.
 */

public class LoginActivity extends BaseActivity implements LoginViewImpl {
    private LoginPresenter loginPresenter;
    @BindView(R.id.title_bar)
    View title_bar;
    @BindView(R.id.left_ll)
    LinearLayout left_ll;
    @BindView(R.id.tv_title_center)
    TextView tv_title_center;
    @BindView(R.id.edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.edt_pwd)
    EditText edt_pwd;
    @BindView(R.id.btn_login)
    Button btn_login;

    @BindDrawable(R.drawable.gray_border_bg_shape)
    Drawable edt_border;
    @BindDrawable(R.drawable.red_border_illegal_bg_shape)
    Drawable edt_border_illegal;

    @BindString(R.string.key_edt_name_mobile)
    String keyMobile;
    @BindString(R.string.key_edt_name_pwd)
    String keyPwd;

    private String target = "main";//点击登录后目标页面标识

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        String extra = getIntent().getStringExtra("target");
        if (!TextUtils.isEmpty(extra)) {
            target = extra;
        }
        loginPresenter = new LoginPresenter(new LoginModel());
        loginPresenter.attachView(this);
        initView();
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_reset, R.id.left_ll})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String user_login = edt_mobile.getText().toString();
                String user_pass = edt_pwd.getText().toString();
                loginPresenter.verify(user_login, user_pass);
                loginPresenter.login(this, user_login, user_pass);
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tv_reset:
                startActivity(new Intent(LoginActivity.this, FindPassActivity.class));
                break;
            case R.id.left_ll:
                goBack(view);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化页面Ui
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initContentView() {
        setContentView(R.layout.activity_login);
    }

    private void initView() {
        tv_title_center.setText("用户登录");
    }

    /**
     * 网络状况改变情况下 重试刷新数据
     *
     * @param viewId
     */
    @Override
    public void onReNetRefreshData(int viewId) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onEdtContentsLegal() {
        edt_mobile.setBackground(edt_border);
        edt_pwd.setBackground(edt_border);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onEdtContentsIllegal(Map<String, Boolean> verifyResult) {
        if (verifyResult.get(keyMobile) != null && !verifyResult.get(keyMobile)) {
            edt_mobile.setBackground(edt_border_illegal);
            edt_mobile.setText("");//清空数据
        } else {
            edt_mobile.setBackground(edt_border);
        }
        if (verifyResult.get(keyPwd) != null && !verifyResult.get(keyPwd)) {
            edt_pwd.setBackground(edt_border_illegal);
            edt_pwd.setText("");
        } else {
            edt_pwd.setBackground(edt_border);
        }
    }

    @Override
    public void onLoginSuccess() {
        ToastUtils.showCustomToast("登录成功",1);
        if(target.equals("main")){//若需求为强制登录，用户第一次登录
            jump(MainActivity.class);
            EventBus.getDefault().post(new SelectFragmentEvent("首页"));
//            jump(TestActivity.class);
        }else if (target.equals("main_personal")) {//从"我的"跳过来，登录成功后跳回"我的"
            jump(MainActivity.class);
            EventBus.getDefault().post(new SelectFragmentEvent("我的"));
//            EventBus.getDefault().post(new LoginEvent("login_status_changed_main_personal_change_islogin"));//头像右侧用户昵称
//        startActivity(new Intent(LoginActivity.this, TestActivity.class));//调试接口使用
        } else if (target.equals("main_transaction")) {//从"交易中心"跳过来，登录成功后跳回"交易中心"
            jump(MainActivity.class);
            EventBus.getDefault().post(new SelectFragmentEvent("交易"));
//            EventBus.getDefault().post(new LoginEvent("login_status_changed_main_personal_change_islogin"));//带着token重新请求接口
        }

        finish();
    }

    @Override
    public void onLoginFailed(String msg) {
        ToastUtils.showCustomToast(msg,0);
    }

    private void jump(Class<?> cls) {
        startActivity(new Intent(LoginActivity.this, cls));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {
            loginPresenter.detachView();
        }
    }

}
