package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

public interface CommonViewImpl extends IBaseViewImpl {
    void onRequestSucc(Object o);
    void onRequestFail(String msg);
}
