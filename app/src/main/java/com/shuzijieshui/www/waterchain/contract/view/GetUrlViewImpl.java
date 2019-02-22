package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.GetUrlResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

public interface GetUrlViewImpl extends IBaseViewImpl {
    void onGetUrlSucc(GetUrlResponseBean getUrlResponseBean);
    void onGetUrlFail(String msg);
}
