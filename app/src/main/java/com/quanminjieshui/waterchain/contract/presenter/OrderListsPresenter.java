package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderListsResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.OrderListsModel;
import com.quanminjieshui.waterchain.contract.view.OrderListsViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 17:39.
 */

public class OrderListsPresenter extends BasePresenter<OrderListsViewImpl> {

    private OrderListsModel orderListsModel;

    public OrderListsPresenter(){}

    public OrderListsPresenter(OrderListsModel orderListsModel){
        this.orderListsModel = orderListsModel;
    }

    public void getOrderList(BaseActivity activity){
        if(orderListsModel == null){
            orderListsModel = new OrderListsModel();
        }
        orderListsModel.orderList(activity, new OrderListsModel.OrderListCallBack() {
            @Override
            public void success(OrderListsResponseBean orderListBean) {
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
