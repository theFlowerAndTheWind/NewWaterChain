package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.LoginModel;
import com.quanminjieshui.waterchain.contract.view.LoginViewImpl;

import java.util.Map;

/**
 * Created by WanghongHe on 2018/12/3 11:04.
 */

public class LoginPresenter extends BasePresenter<LoginViewImpl> {
    private LoginModel loginModel;

    public LoginPresenter(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void verify(String mobile, String pwd) {
        loginModel.verify(mobile, pwd);
    }

    public void login(BaseActivity activity, String name, String password) {
        if (loginModel == null) {
            loginModel = new LoginModel();
        }


        loginModel.login(activity, name, password, new LoginModel.LoginCallback() {


            @Override
            public void onEdtContentsLegal() {
                if (mView != null) {
                    mView.onEdtContentsLegal();
                }
            }

            @Override
            public void onEdtContentsIllegal(Map<String, Boolean> verify) {
                if (mView != null) {
                    mView.onEdtContentsIllegal(verify);
                }
            }

            @Override
            public void loginSuccess() {
                if (mView != null) {
                    mView.onLoginSuccess();
                }
            }

            @Override
            public void loginFailed(String msg) {
                if (mView != null) {
                    mView.onLoginFailed(msg);
                }
            }
        });
    }
}
