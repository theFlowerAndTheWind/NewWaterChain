package com.quanminjieshui.waterchain.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.model.LoginModel;
import com.quanminjieshui.waterchain.contract.presenter.LoginPresenter;
import com.quanminjieshui.waterchain.contract.presenter.OrderDetailPresenter;
import com.quanminjieshui.waterchain.contract.presenter.OrderListPresenter;
import com.quanminjieshui.waterchain.contract.presenter.TradeCenterPresenter;
import com.quanminjieshui.waterchain.contract.presenter.TradeDetailPresenter;
import com.quanminjieshui.waterchain.contract.view.LoginViewImpl;
import com.quanminjieshui.waterchain.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by WanghongHe on 2018/12/3 11:53.
 */

//public class LoginActivity extends BaseActivity implements LoginViewImpl,OrderDetailViewImpl,OrderListViewImpl,TradeDetailViewImpl,TradeCenterViewImpl {
public class LoginActivity extends BaseActivity implements LoginViewImpl {
    private LoginPresenter loginPresenter;
    private OrderDetailPresenter orderDetailPresenter;
    private OrderListPresenter orderListPresenter;
    private TradeDetailPresenter tradeDetailPresenter;
    private TradeCenterPresenter tradeCenterPresenter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginPresenter = new LoginPresenter(new LoginModel());
        loginPresenter.attachView(this);
//        orderDetailPresenter = new OrderDetailPresenter();
//        orderDetailPresenter.attachView(this);
//        orderListPresenter = new OrderListPresenter();
//        orderListPresenter.attachView(this);
//        tradeDetailPresenter = new TradeDetailPresenter();
//        tradeDetailPresenter.attachView(this);
//        tradeCenterPresenter = new TradeCenterPresenter();
//        tradeCenterPresenter.attachView(this);
        StatusBarUtil.setImmersionStatus(this, title_bar);
        initView();
    }

    @OnClick({R.id.btn_login, R.id.tv_register, R.id.tv_reset, R.id.left_ll})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
//                tradeDetailPresenter.getTradeDetail(this,1);
//                tradeCenterPresenter.getTradeCenter(this);
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
//        orderDetailPresenter.orderDetail(this);
//        orderListPresenter.getOrderList(this);
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
            edt_mobile.setText("");
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
        showToast("login success！");
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void onLoginFailed(String msg) {
        showToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null) {
            loginPresenter.detachView();
        }
    }

//    @Override
//    public void onOrderDetailSuccess(OrderDetailResponseBean orderDetailResponseBeans) {
//        LogUtils.d("ceshi"+orderDetailResponseBeans);
//
//    }
//
//    @Override
//    public void onOrderDetailFailed(String msg) {
//        LogUtils.d("ceshi"+msg);
//    }
//
//    @Override
//    public void onOrderListSuccess(OrderListResponseBean orderListBean) {
//        LogUtils.d("ceshi"+orderListBean);
//    }
//
//    @Override
//    public void onOrderListFailed(String msg) {
//        LogUtils.d("ceshi-list"+msg);
//    }
//
//    @Override
//    public void onTradeDetailSuccess(TradeDetailResponseBean tradeDetailResponseBean) {
//
//    }
//
//    @Override
//    public void onTradeDetailFailed(String msg) {
//
//    }
//
//    @Override
//    public void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean) {
//
//    }
//
//    @Override
//    public void onTradeCenterFailed(String msg) {
//
//    }
}
