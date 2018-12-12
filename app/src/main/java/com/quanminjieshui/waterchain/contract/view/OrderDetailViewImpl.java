package com.quanminjieshui.waterchain.contract.view;

import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.contract.IBaseViewImpl;

import java.util.List;

/**
 * Created by WanghongHe on 2018/12/12 16:13.
 */

public interface OrderDetailViewImpl extends IBaseViewImpl{
    void onOrderDetailSuccess(OrderDetailResponseBean orderDetailResponseBeans);
    void onOrderDetailFailed(String msg);
}
