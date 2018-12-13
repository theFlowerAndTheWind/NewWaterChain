package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.UserDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:
 */

public interface UserDetailViewImpl extends IBaseViewImpl {
    void onUserDetailSuccess(UserDetailResponseBean userDetailResponseBean);
    void onUserDetailFailed(String msg);
}
