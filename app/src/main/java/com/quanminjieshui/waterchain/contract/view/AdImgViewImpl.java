package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.AdImgResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

public interface AdImgViewImpl extends IBaseViewImpl {
    void onGetAdImgSuccess(AdImgResponseBean bean);

    void onGetAdImgFailed(String msg);
}
