package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:42.
 */

public interface TradeCenterViewImpl extends IBaseViewImpl {
    void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean);
    void onTradeCenterFailed(String msg);
}
