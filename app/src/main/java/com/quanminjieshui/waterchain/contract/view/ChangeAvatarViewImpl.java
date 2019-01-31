package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

public interface ChangeAvatarViewImpl extends IBaseViewImpl {
    void changeAvatarSucc();
    void changeAvatarfail(String msg);
}
