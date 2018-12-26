package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public interface CreateOrderViewImpl extends IBaseViewImpl {
    void onCreateOrderSuccess(CreateOrderResponseBean createOrderResponseBean);
    void onCreateOrderFailed(String msg);
}
