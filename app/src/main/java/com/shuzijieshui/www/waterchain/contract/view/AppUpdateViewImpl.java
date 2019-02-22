package com.shuzijieshui.www.waterchain.contract.view;


import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/22.
 * Class Note:
 */

public interface AppUpdateViewImpl extends IBaseViewImpl {
    void onAppUpdateSuccess(Object bean);
    void onAppUpdateFailed(String msg);
}
