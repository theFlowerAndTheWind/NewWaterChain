package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderExchangeReqBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.CreateOrderExchangeModel;
import com.shuzijieshui.www.waterchain.contract.view.CreateOrderExchangeViewImpl;

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
