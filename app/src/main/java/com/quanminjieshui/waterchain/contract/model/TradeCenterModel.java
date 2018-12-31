package com.quanminjieshui.waterchain.contract.model;

import com.bumptech.glide.load.resource.bitmap.TransformationUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BuyResponseBean;
import com.quanminjieshui.waterchain.beans.SellResponseBean;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;


/**
 * Created by WanghongHe on 2018/12/12 18:35.
 */

public class TradeCenterModel {

    public void getTradeDetail(BaseActivity activity, final TradeCenterCallBack callBack) {

        RetrofitFactory.getInstance().createService()
                .tradeCenter(RequestUtil.getRequestHashBody(null, false))
                .compose(activity.<BaseEntity<TradeCenterResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TradeCenterResponseBean>>io())
                .subscribe(new BaseObserver<TradeCenterResponseBean>(activity) {

                    @Override
                    protected void onSuccess(TradeCenterResponseBean tradeCenterBean) throws Exception {
                        callBack.onTradeCenterSuccess(tradeCenterBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.onTradeCenterFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.onTradeCenterFailed(e.getMessage());
                            }
                        } else {
                            callBack.onTradeCenterFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.onTradeCenterFailed(msg);
                    }
                });
    }

    public void buy(BaseActivity activity, int type, float total, float price, final TradeCenterCallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        if (type == 1 || type == 2) {
            params.put("type", type);
        }
        if (total > 0) {
            params.put("total", total);
        }
        if (price > 0) {
            params.put("price", price);
        }
        RetrofitFactory.getInstance().createService()
                .buy(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity<BuyResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<BuyResponseBean>>io())
                .subscribe(new BaseObserver<BuyResponseBean>() {
                    @Override
                    protected void onSuccess(BuyResponseBean buyResponseBean) throws Exception {
                        callBack.onBuySuccess(buyResponseBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.onBuyFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.onBuyFailed(e.getMessage());
                            }
                        } else {
                            callBack.onBuyFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.onBuyFailed(msg);
                    }
                });

    }

    public void sell(BaseActivity activity, int type, float total, float price, final TradeCenterCallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        if (type == 1 || type == 2) {
            params.put("type", type);
        }
        if (total > 0) {
            params.put("total", total);
        }
        if (price > 0) {
            params.put("price", price);
        }
        RetrofitFactory.getInstance().createService()
                .sell(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity<SellResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<SellResponseBean>>io())
                .subscribe(new BaseObserver<SellResponseBean>() {
                    @Override
                    protected void onSuccess(SellResponseBean sellResponseBean) throws Exception {
                        callBack.onSellSuccess(sellResponseBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.onSellFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.onSellFailed(e.getMessage());
                            }
                        } else {
                            callBack.onSellFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.onSellFailed(msg);
                    }
                });

    }


    public interface TradeCenterCallBack {
        void onTradeCenterSuccess(TradeCenterResponseBean tradeCenterResponseBean);

        void onTradeCenterFailed(String msg);

        void onBuySuccess(BuyResponseBean buyResponseBean);

        void onBuyFailed(String msg);

        void onSellSuccess(SellResponseBean sellResponseBean);

        void onSellFailed(String msg);
    }

}
