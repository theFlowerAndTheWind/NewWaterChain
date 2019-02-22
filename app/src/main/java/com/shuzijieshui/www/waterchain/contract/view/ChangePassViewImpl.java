package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/10 18:57.
 */

public interface ChangePassViewImpl extends IBaseViewImpl {
    void changePassSuccess();
    void changePassFiled(String err);
}
