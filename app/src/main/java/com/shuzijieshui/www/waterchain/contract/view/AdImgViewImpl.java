package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

public interface AdImgViewImpl extends IBaseViewImpl {
    void onGetAdImgSuccess(AdImgResponseBean bean);

    void onGetAdImgFailed(String msg);
}
