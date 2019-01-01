package com.quanminjieshui.waterchain.contract.presenter;

import android.content.Context;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BuyResponseBean;
import com.quanminjieshui.waterchain.beans.SellResponseBean;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.contract.BasePresenter;
import com.quanminjieshui.waterchain.contract.model.TradeCenterModel;
import com.quanminjieshui.waterchain.contract.view.TradeCenterViewImpl;

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
            public void onBuySuccess(BuyResponseBean buyResponseBean) {
            }

            @Override
            public void onBuyFailed(String msg) {
            }

            @Override
            public void onSellSuccess(SellResponseBean sellResponseBean) {
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
        tradeCenterModel.sell(activity, type, total, price, new TradeCenterModel.TradeCenterCallBack() {

            @Override
            public void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean) {
            }

            @Override
            public void onTradeCenterFailed(String msg) {
            }

            @Override
            public void onBuySuccess(BuyResponseBean buyResponseBean) {
                if (mView != null) {
                    mView.onBuySuccess(buyResponseBean);
                }
            }

            @Override
            public void onBuyFailed(String msg) {
                if (mView != null) {
                    mView.onBuyFailed(msg);
                }
            }

            @Override
            public void onSellSuccess(SellResponseBean sellResponseBean) {
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
            public void onBuySuccess(BuyResponseBean buyResponseBean) {
            }

            @Override
            public void onBuyFailed(String msg) {
            }

            @Override
            public void onSellSuccess(SellResponseBean sellResponseBean) {
                if (mView != null) {
                    mView.onSellSuccess(sellResponseBean);
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
