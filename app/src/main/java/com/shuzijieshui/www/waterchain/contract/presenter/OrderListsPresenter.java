package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.OrderListsResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.OrderListsModel;
import com.shuzijieshui.www.waterchain.contract.view.OrderListsViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 17:39.
 */

public class OrderListsPresenter extends BasePresenter<OrderListsViewImpl> {

    private OrderListsModel orderListsModel;

    public OrderListsPresenter(){}

    public OrderListsPresenter(OrderListsModel orderListsModel){
        this.orderListsModel = orderListsModel;
    }

    public void getOrderList(BaseActivity activity,int status,int count){
        if(orderListsModel == null){
            orderListsModel = new OrderListsModel();
        }
        orderListsModel.orderList(activity,status,count, new OrderListsModel.OrderListCallBack() {
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
