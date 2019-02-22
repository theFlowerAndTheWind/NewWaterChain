package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public interface CreateOrderExchangeViewImpl extends IBaseViewImpl {
    void onCreateOrderExchangeSuccess(Object o);
    void onCreateOrderExchangeFailed(String msg);
}
