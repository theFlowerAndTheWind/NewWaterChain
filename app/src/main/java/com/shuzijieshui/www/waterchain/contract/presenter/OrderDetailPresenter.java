package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.OrderDetailResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.OrderDetailModel;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;
import com.shuzijieshui.www.waterchain.contract.view.OrderDetailViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 16:15.
 *
 * update by sxt on 2019/4/8
 */

public class OrderDetailPresenter extends BasePresenter<CommonViewImpl> {

    private OrderDetailModel orderDetailModel;

    public OrderDetailPresenter(){}

    public OrderDetailPresenter(OrderDetailModel orderDetailModel){
        this.orderDetailModel = orderDetailModel;
    }

    public void getOrderDetail(BaseActivity activity,int id){
        if(orderDetailModel == null){
            orderDetailModel = new OrderDetailModel();
        }
        orderDetailModel.getOrderDetail(activity,id,new CommonCallback() {
            @Override
            public void onRequestSucc(Object o) {
                if(mView!=null){
                    mView.onRequestSucc(o);
                }
            }

            @Override
            public void onRequestFail(String msg) {
                if(mView!=null){
                    mView.onRequestFail(msg);
                }
            }
        });
    }
}
