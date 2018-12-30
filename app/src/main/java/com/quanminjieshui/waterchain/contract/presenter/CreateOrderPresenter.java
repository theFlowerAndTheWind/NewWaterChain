package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.CreateOrderResponseBean;
import com.quanminjieshui.waterchain.beans.request.CreateOrderReqParams;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.CreateOrderModel;
import com.quanminjieshui.waterchain.contract.view.CreateOrderViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public class CreateOrderPresenter extends BasePresenter<CreateOrderViewImpl> {

    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter(){}

    public CreateOrderPresenter(CreateOrderModel createOrderModel){
        this.createOrderModel = createOrderModel;
    }

    public void createOrder(BaseActivity activity, CreateOrderReqParams params){
        if(createOrderModel == null){
            createOrderModel = new CreateOrderModel();
        }
        createOrderModel.createOrder(activity,params, new CreateOrderModel.CreateOrderCallBack() {
            @Override
            public void success(CreateOrderResponseBean createOrderResponseBean) {
                if (mView!=null){
                    mView.onCreateOrderSuccess(createOrderResponseBean);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onCreateOrderFailed(msg);
                }

            }
        });

    }
}