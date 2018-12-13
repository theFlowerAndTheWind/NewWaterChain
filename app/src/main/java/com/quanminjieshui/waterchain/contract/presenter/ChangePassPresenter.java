package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.ChangePassModel;
import com.quanminjieshui.waterchain.contract.view.ChangePassViewImpl;

/**
 * Created by WanghongHe on 2018/12/10 18:56.
 * user_login 用户注册手机号
 */

public class ChangePassPresenter extends BasePresenter<ChangePassViewImpl> {

    private ChangePassModel changePassModel;

    public ChangePassPresenter(ChangePassModel changePassModel){
        this.changePassModel = changePassModel;
    }

    public void changePass(BaseActivity activity, String user_login, String old_pass, String new_pass){
        if(changePassModel==null){
            changePassModel = new ChangePassModel();
        }

        changePassModel.changePass(activity, user_login, old_pass, new_pass, new ChangePassModel.ChangePassCallBack() {
            @Override
            public void success() {
                if(mView!=null){
                    mView.changePassSuccess();
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.changePassFiled(msg);
                }

            }
        });
    }
}
