package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderListResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.OrderListModel;
import com.quanminjieshui.waterchain.contract.view.OrderListViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 17:39.
 */

public class OrderListPresenter extends BasePresenter<OrderListViewImpl> {

    private OrderListModel orderListModel;

    public OrderListPresenter(){}

    public OrderListPresenter(OrderListModel orderListModel){
        this.orderListModel = orderListModel;
    }

    public void getOrderList(BaseActivity activity){
        if(orderListModel == null){
            orderListModel = new OrderListModel();
        }
        orderListModel.orderList(activity, new OrderListModel.OrderListCallBack() {
            @Override
            public void success(OrderListResponseBean orderListBean) {
                if(mView!=null){
                    mView.onOrderListSuccess(orderListBean);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onOrderListFailed(msg);
                }
            }
        });
    }
}
