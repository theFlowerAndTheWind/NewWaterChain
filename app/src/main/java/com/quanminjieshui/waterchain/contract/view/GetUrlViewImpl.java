package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.GetUrlResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

public interface GetUrlViewImpl extends IBaseViewImpl {
    void onGetUrlSucc(GetUrlResponseBean getUrlResponseBean);
    void onGetUrlFail(String msg);
}
