package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

public interface MvJslViewImpl extends IBaseViewImpl {
    void success();
    void failed(String msg);
}
