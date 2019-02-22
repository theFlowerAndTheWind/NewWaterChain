package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.UserAddressResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.UserAddressModel;
import com.shuzijieshui.www.waterchain.contract.view.UserAddressViewImpl;

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
