package com.shuzijieshui.www.waterchain.contract.view;

import com.shuzijieshui.www.waterchain.beans.OrderDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.IBaseViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 16:13.
 */

public interface OrderDetailViewImpl extends IBaseViewImpl {
    void onOrderDetailSuccess(OrderDetailResponseBean orderDetailResponseBeans);
    void onOrderDetailFailed(String msg);
}
