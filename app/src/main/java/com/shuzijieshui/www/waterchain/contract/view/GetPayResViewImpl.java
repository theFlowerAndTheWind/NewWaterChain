package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

public interface GetPayResViewImpl  extends IBaseViewImpl {
    void onGetPayResSucc();
    void onGetPayResFail(String msg);
}
