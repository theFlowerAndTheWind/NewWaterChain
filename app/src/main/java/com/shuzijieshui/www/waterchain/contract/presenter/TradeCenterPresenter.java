package com.shuzijieshui.www.waterchain.contract.presenter;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.TradeCenterResponseBean;
import com.shuzijieshui.www.waterchain.contract.BasePresenter;
import com.shuzijieshui.www.waterchain.contract.model.TradeCenterModel;
import com.shuzijieshui.www.waterchain.contract.view.TradeCenterViewImpl;

/**
 * Created by WanghongHe on 2018/12/12 18:43.
 */

public class TradeCenterPresenter extends BasePresenter<TradeCenterViewImpl> {

    private TradeCenterModel tradeCenterModel;

    public TradeCenterPresenter() {
    }

    public TradeCenterPresenter(TradeCenterModel tradeCenterModel) {
        this.tradeCenterModel = tradeCenterModel;
    }

    public void getTradeCenter(BaseActivity activity) {
        if (tradeCenterModel == null) {
            tradeCenterModel = new TradeCenterModel();
        }
        tradeCenterModel.getTradeDetail(activity, new TradeCenterModel.TradeCenterCallBack() {

            @Override
            public void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean) {
                if (mView != null) {
                    mView.onTradeCenterSuccess(tradeCenterResponseBean);
                }
            }

            @Override
            public void onTradeCenterFailed(String msg) {
                if (mView != null) {
                    mView.onTradeCenterFailed(msg);
                }
            }

            @Override
            public void onBuySuccess(Object o) {
            }

            @Override
            public void onBuyFailed(String msg) {
            }

            @Override
            public void onSellSuccess(Object o) {
            }

            @Override
            public void onSellFailed(String msg) {
            }
        });
    }

    public void buy(BaseActivity activity, int type, float total, float price) {
        if (tradeCenterModel == null) {
            tradeCenterModel = new TradeCenterModel();
        }
        tradeCenterModel.buy(activity, type, total, price, new TradeCenterModel.TradeCenterCallBack() {

            @Override
            public void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean) {
            }

            @Override
            public void onTradeCenterFailed(String msg) {
            }

            @Override
            public void onBuySuccess(Object o) {
                if (mView != null) {
                    mView.onBuySuccess(o);
                }
            }

            @Override
            public void onBuyFailed(String msg) {
                if (mView != null) {
                    mView.onBuyFailed(msg);
                }
            }

            @Override
            public void onSellSuccess(Object o) {
            }

            @Override
            public void onSellFailed(String msg) {
            }
        });


    }

    public void sell(BaseActivity activity, int type, float total, float price) {
        if (tradeCenterModel == null) {
            tradeCenterModel = new TradeCenterModel();
        }
        tradeCenterModel.buy(activity, type, total, price, new TradeCenterModel.TradeCenterCallBack() {

            @Override
            public void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean) {
            }

            @Override
            public void onTradeCenterFailed(String msg) {
            }

            @Override
            public void onBuySuccess(Object o) {
            }

            @Override
            public void onBuyFailed(String msg) {
            }

            @Override
            public void onSellSuccess(Object o) {
                if (mView != null) {
                    mView.onSellSuccess(o);
                }
            }

            @Override
            public void onSellFailed(String msg) {
                if (mView != null) {
                    mView.onSellFailed(msg);
                }
            }
        });


    }

}
