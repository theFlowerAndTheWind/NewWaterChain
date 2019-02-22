package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AuthDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.AuthDetailModel;
import com.shuzijieshui.www.waterchain.contract.view.AuthDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public class AuthDetailPresenter extends BasePresenter<AuthDetailViewImpl> {

    private AuthDetailModel authDetailModel;

    public AuthDetailPresenter(){}

    public AuthDetailPresenter(AuthDetailModel authDetailModel){
        this.authDetailModel = authDetailModel;
    }

    public void getAuthDetail(BaseActivity activity){
        if(authDetailModel == null){
            authDetailModel = new AuthDetailModel();
        }
        authDetailModel.getAuthDetail(activity, new AuthDetailModel.AuthDetailCallBack() {
            @Override
            public void success(AuthDetailResponseBean authDetailResponseBean) {
                if(mView!=null){
                    mView.authDetailSuccess(authDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.authDetailFailed(msg);
                }

            }
        });
    }
}
