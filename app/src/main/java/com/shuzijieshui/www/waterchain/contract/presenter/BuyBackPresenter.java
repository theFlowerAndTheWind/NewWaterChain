package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.BuyBackModel;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.contract.view.CommonViewImpl;

public class BuyBackPresenter extends BasePresenter<CommonViewImpl> {
    private BuyBackModel buyBackModel;

    public BuyBackPresenter(BuyBackModel buyBackModel) {
        this.buyBackModel = buyBackModel;
    }

    public void buyBack(BaseActivity activity, float count) {
        if (buyBackModel == null) {
            buyBackModel = new BuyBackModel();
        }
        buyBackModel.buyBack(activity, count, new CommonCallback() {
            @Override
            public void onRequestSucc(Object o) {
                if (mView != null) {
                    mView.onRequestSucc(o);
                }
            }

            @Override
            public void onRequestFail(String msg) {
                if (mView != null) {
                    mView.onRequestFail(msg);
                }
            }
        });
    }

}
