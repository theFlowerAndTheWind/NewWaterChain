package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.TradeDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:26.
 */

public interface TradeDetailViewImpl extends IBaseViewImpl {
    void onTradeDetailSuccess(TradeDetailResponseBean tradeDetailResponseBean);
    void onTradeDetailFailed(String msg);
}
