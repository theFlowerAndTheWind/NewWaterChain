package com.quanminjieshui.waterchain.contract.presenter;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.request.CreateOrderExchangeReqBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.CreateOrderExchangeModel;
import com.quanminjieshui.waterchain.contract.view.CreateOrderExchangeViewImpl;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class CreateOrderExchangePresenter extends BasePresenter<CreateOrderExchangeViewImpl> {
    private CreateOrderExchangeModel createOrderExchangeModel;

    public CreateOrderExchangePresenter(){}

    public CreateOrderExchangePresenter(CreateOrderExchangeModel createOrderExchangeModel){
        this.createOrderExchangeModel = createOrderExchangeModel;
    }

    public void createOrderExchange(BaseActivity activity, CreateOrderExchangeReqBean params){
        if (createOrderExchangeModel==null){
            createOrderExchangeModel = new CreateOrderExchangeModel();
        }
        createOrderExchangeModel.createOrderExchange(activity, params, new CreateOrderExchangeModel.CreateOrderExchangeCallBack() {
            @Override
            public void success(Object o) {
                if (mView!=null){
                    mView.onCreateOrderExchangeSuccess(o);
                }
            }

            @Override
            public void failed(String msg) {
                if (mView!=null){
                    mView.onCreateOrderExchangeFailed(msg);
                }
            }
        });
    }
}
