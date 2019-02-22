package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.SellResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeCenterResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

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
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver() {
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callBack.onBuySuccess(o);
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

        void onBuySuccess(Object o);

        void onBuyFailed(String msg);

        void onSellSuccess(Object o);

        void onSellFailed(String msg);
    }

}
