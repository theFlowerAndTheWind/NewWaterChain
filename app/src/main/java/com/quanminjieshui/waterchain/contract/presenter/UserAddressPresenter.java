package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.UserAddressResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.UserAddressModel;
import com.quanminjieshui.waterchain.contract.view.UserAddressViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class UserAddressPresenter extends BasePresenter<UserAddressViewImpl> {
    private UserAddressModel userAddressModel;

    public UserAddressPresenter(){}

    public UserAddressPresenter(UserAddressModel userAddressModel){
        this.userAddressModel = userAddressModel;
    }

    public void getUserAddress(BaseActivity activity){
        if (userAddressModel==null){
            userAddressModel = new UserAddressModel();
        }
        userAddressModel.getUserAddress(activity, new UserAddressModel.UserAddressCallBack() {
            @Override
            public void success(UserAddressResponseBean beans) {
                if (mView!=null){
                    mView.onUserAddressSuccess(beans);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onUserAddressFailed(msg);
                }
            }
        });
    }
}
