package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.CreateOrderResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public interface CreateOrderViewImpl extends IBaseViewImpl {
    void onCreateOrderSuccess(Object o);
    void onCreateOrderFailed(String msg);
}
