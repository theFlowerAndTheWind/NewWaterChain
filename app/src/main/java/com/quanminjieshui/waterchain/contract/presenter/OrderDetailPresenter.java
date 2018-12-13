package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.OrderDetailModel;
import com.quanminjieshui.waterchain.contract.view.OrderDetailViewImpl;

import java.util.List;

/**
 * Created by WanghongHe on 2018/12/12 16:15.
 */

public class OrderDetailPresenter extends BasePresenter<OrderDetailViewImpl> {

    private OrderDetailModel orderDetailModel;

    public OrderDetailPresenter(){}

    public OrderDetailPresenter(OrderDetailModel orderDetailModel){
        this.orderDetailModel = orderDetailModel;
    }

    public void orderDetail(BaseActivity activity){
        if(orderDetailModel == null){
            orderDetailModel = new OrderDetailModel();
        }
        orderDetailModel.getOrderDetail(activity, new OrderDetailModel.OrderDetailCallBack() {
            @Override
            public void success(OrderDetailResponseBean orderDetailBeans) {
                if(mView!=null){
                    mView.onOrderDetailSuccess(orderDetailBeans);
                }
            }

            @Override
            public void failed(String msg) {
                if(mView!=null){
                    mView.onOrderDetailFailed(msg);
                }

            }
        });
    }
}
