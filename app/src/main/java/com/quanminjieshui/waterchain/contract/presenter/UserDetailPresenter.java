package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.UserDetailResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.UserDetailModel;
import com.quanminjieshui.waterchain.contract.view.UserDetailViewImpl;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:
 */

public class UserDetailPresenter extends BasePresenter<UserDetailViewImpl> {

    private UserDetailModel userDetailModel;

    public UserDetailPresenter(){}

    public UserDetailPresenter(UserDetailModel userDetailModel){
        this.userDetailModel = userDetailModel;
    }

    public void getUserDetail(BaseActivity activity){
        if(userDetailModel == null){
            userDetailModel = new UserDetailModel();
        }

        userDetailModel.getUserDetail(activity, new UserDetailModel.UserDetailCallBack() {
            @Override
            public void success(UserDetailResponseBean userDetailResponseBean) {
                if(mView!=null){
                    mView.onUserDetailSuccess(userDetailResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onUserDetailFailed(msg);
                }

            }
        });
    }
}
