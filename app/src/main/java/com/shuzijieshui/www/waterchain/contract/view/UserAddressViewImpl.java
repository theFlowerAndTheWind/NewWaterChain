package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.UserAddressResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public interface UserAddressViewImpl extends IBaseViewImpl {
    void onUserAddressSuccess(UserAddressResponseBean beans);
    void onUserAddressFailed(String msg);
}
