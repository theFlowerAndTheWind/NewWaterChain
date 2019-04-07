package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CreateOrderResponseBean;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderReqParams;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.CreateOrderModel;
import com.shuzijieshui.www.waterchain.contract.view.CreateOrderViewImpl;

/**
 * Created by songxiaotao on 2018/12/27.
 * Class Note:
 */

public class CreateOrderPresenter extends BasePresenter<CreateOrderViewImpl> {

    private CreateOrderModel createOrderModel;

    public CreateOrderPresenter() {
    }

    public CreateOrderPresenter(CreateOrderModel createOrderModel) {
        this.createOrderModel = createOrderModel;
    }

//    public void createOrder(BaseActivity activity, CreateOrderReqParams params) {
//        if (createOrderModel == null) {
//            createOrderModel = new CreateOrderModel();
//        }
//        createOrderModel.createOrder(activity, params, new CreateOrderModel.CreateOrderCallBack() {
//            @Override
//            public void success(Object o) {
//                if (mView != null) {
//                    mView.onCreateOrderSuccess(o);
//                }
//            }
//
//            @Override
//            public void failed(String msg) {
//                if (mView != null) {
//                    mView.onCreateOrderFailed(msg);
//                }
//
//            }
//        });
//
//    }
    public void createOrder(BaseActivity activity, long id, float count, float use_jsl, int pay_cate) {
        if (createOrderModel == null) {
            createOrderModel = new CreateOrderModel();
        }
        createOrderModel.createOrder(activity, id, count, use_jsl, pay_cate, new CreateOrderModel.CreateOrderCallBack() {
            @Override
            public void success(Object o) {
                if (mView != null) {
                    mView.onCreateOrderSuccess(o);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView != null) {
                    mView.onCreateOrderFailed(msg);
                }

            }
        });

    }
}
