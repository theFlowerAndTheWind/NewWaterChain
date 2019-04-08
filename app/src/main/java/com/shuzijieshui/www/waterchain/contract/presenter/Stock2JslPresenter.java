package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.Stock2JslModel;
import com.shuzijieshui.www.waterchain.contract.view.Stock2JslViewImpl;

public class Stock2JslPresenter extends BasePresenter<Stock2JslViewImpl> {
    private Stock2JslModel stock2JslModel;

    public Stock2JslPresenter(Stock2JslModel stock2JslModel) {
        this.stock2JslModel = stock2JslModel;
    }

    public void stock2Jsl(BaseActivity activity, float count) {
        if (stock2JslModel == null) {
            stock2JslModel = new Stock2JslModel();
        }
        stock2JslModel.stock2JslModel(activity, count, new Stock2JslModel.Stock2JslModelCallback() {
            @Override
            public void onSucc(Object o) {
                if (mView != null) {
                    mView.onStock2JslSucc(o);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView != null) {
                    mView.onStock2JslFail(msg);
                }
            }
        });
    }

}
