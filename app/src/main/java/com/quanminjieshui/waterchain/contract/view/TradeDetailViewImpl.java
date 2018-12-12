package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.TradeDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:26.
 */

public interface TradeDetailViewImpl extends IBaseViewImpl {
    void onTradeDetailSuccess(TradeDetailResponseBean tradeDetailResponseBean);
    void onTradeDetailFailed(String msg);
}
