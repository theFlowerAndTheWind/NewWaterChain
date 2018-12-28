package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.OrderListsResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 17:38.
 */

public interface OrderListsViewImpl extends IBaseViewImpl {
    void onOrderListSuccess(OrderListsResponseBean orderListBean);
    void onOrderListFailed(String msg);
}
