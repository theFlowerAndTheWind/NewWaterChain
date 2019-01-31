package com.quanminjieshui.waterchain.contract.view;


import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/22.
 * Class Note:
 */

public interface AppUpdateViewImpl extends IBaseViewImpl {
    void onAppUpdateSuccess(Object bean);
    void onAppUpdateFailed(String msg);
}
