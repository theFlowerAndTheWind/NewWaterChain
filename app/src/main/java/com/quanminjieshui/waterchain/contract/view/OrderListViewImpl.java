package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.OrderListResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 17:38.
 */

public interface OrderListViewImpl extends IBaseViewImpl {
    void onOrderListSuccess(OrderListResponseBean orderListBean);
    void onOrderListFailed(String msg);
}
