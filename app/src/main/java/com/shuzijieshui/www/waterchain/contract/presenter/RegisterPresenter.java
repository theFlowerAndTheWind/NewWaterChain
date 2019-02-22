/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RegisterPresenter
 * Author: sxt
 * Date: 2018/12/8 2:51 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.RegisterResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.RegisterModel;
import com.shuzijieshui.www.waterchain.contract.view.RegisterViewImpl;

import java.util.Map;

/**
 * @ClassName: RegisterPresenter
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/8 2:51 PM
 */
public class RegisterPresenter extends BasePresenter<RegisterViewImpl> {

    private RegisterModel registerModel;

    public RegisterPresenter(RegisterModel registerModel) {
        this.registerModel = registerModel;
    }

    public void verify(String mobile) {
        registerModel.verify(mobile);
    }

    public void verify(final String mobile, final String pwd, final String confirm, final String sms, final String invitation, final boolean agreement) {
        registerModel.verify(mobile, pwd, confirm, sms, invitation, agreement);
    }

    public void getSms(BaseActivity activity, String mobile) {
        if (registerModel == null) {
            registerModel = new RegisterModel();
        }


        registerModel.getSms(activity, mobile, new RegisterModel.RegisterCallback() {


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
            public void onGetSmsSuccess() {
                if (mView != null) {
                    mView.onGetSmsSuccess();
                }
            }

            @Override
            public void onGetSmsFailed(String msg) {
                if (mView != null) {
                    mView.onGetSmsFailed(msg);
                }
            }

            @Override
            public void onRegisterSuccess(RegisterResponseBean registerResponseBean) {
            }

            @Override
            public void onRegisterFaild(String msg) {
            }
        });
    }

    public void register(BaseActivity activity, final String mobile, final String pwd, final String confirm, final String sms, final String invitation, final boolean agreement) {
        if (registerModel == null) {
            registerModel = new RegisterModel();
        }
        registerModel.register(activity, mobile, pwd, confirm, sms, invitation, agreement, new RegisterModel.RegisterCallback() {
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
            public void onGetSmsSuccess() {

            }

            @Override
            public void onGetSmsFailed(String msg) {

            }

            @Override
            public void onRegisterSuccess(RegisterResponseBean registerResponseBean) {
                if (mView != null) {
                    mView.onRegisterSuccess(registerResponseBean);
                }
            }

            @Override
            public void onRegisterFaild(String msg) {
                if (mView != null) {
                    mView.onRegisterFaild(msg);
                }
            }
        });
    }

}
