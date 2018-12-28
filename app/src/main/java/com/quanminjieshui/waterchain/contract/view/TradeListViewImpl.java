package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.TradeListsResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/13 14:16.
 */

public interface TradeListViewImpl extends IBaseViewImpl {
    void onTradeListSuccess(TradeListsResponseBean responseBean);
    void onTradeListFailed(String msg);
}
