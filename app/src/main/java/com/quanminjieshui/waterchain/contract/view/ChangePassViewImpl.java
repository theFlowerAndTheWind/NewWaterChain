package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/10 18:57.
 */

public interface ChangePassViewImpl extends IBaseViewImpl {
    void changePassSuccess();
    void changePassFiled(String err);
}
