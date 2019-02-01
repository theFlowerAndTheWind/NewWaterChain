package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.UserAddressResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public interface UserAddressViewImpl extends IBaseViewImpl {
    void onUserAddressSuccess(UserAddressResponseBean beans);
    void onUserAddressFailed(String msg);
}
