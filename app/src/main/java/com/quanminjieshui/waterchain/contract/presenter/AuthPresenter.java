package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BaseBean;
import com.quanminjieshui.waterchain.beans.request.CompanyAuthReqParams;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.AuthModel;
import com.quanminjieshui.waterchain.contract.view.AuthViewImpl;

import java.util.Map;

public class AuthPresenter extends BasePresenter<AuthViewImpl> {

    private AuthModel model;

    public AuthPresenter(AuthModel model) {
        this.model = model;
    }

    public void auth(BaseActivity activity, boolean user_type, BaseBean params){
        if(model==null){
            model=new AuthModel();
        }
        model.auth(activity, user_type, params, new AuthModel.AuthCallback() {
            @Override
            public void onEdtContentsLegal() {
                if(mView!=null){
                    mView.onEdtContentsLegal();
                }
            }

            @Override
            public void onEdtContentsIllegal(Map<String, Boolean> verify) {
                if(mView!=null){
                    mView.onEdtContentsIllegal(verify);
                }
            }

            @Override
            public void onCompanyAuthSuccess() {
                if(mView!=null){
                    mView.onCompanyAuthSuccess();
                }
            }

            @Override
            public void onCompanyAuthFailed(String msg) {
                if(mView!=null){
                    mView.onCompanyAuthFailed(msg);
                }
            }

            @Override
            public void onPersonalAuthSuccess() {
                if(mView!=null){
                    mView.onPersonalAuthSuccess();
                }
            }

            @Override
            public void onPersonalAuthFailed(String msg) {
                if(mView!=null){
                    mView.onPersonalAuthFailed(msg);
                }
            }
        });
    }

}
