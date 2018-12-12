package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeCenterResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;


/**
 * Created by WanghongHe on 2018/12/12 18:35.
 */

public class TradeCenterModel {

    public void getTradeDetail(BaseActivity activity,final TradeCenterCallBack callBack){

        RetrofitFactory.getInstance().createService()
                .tradeCenter(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {

                    /**
                     * 返回成功
                     *
                     * @param o
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        Gson gson = new Gson();
                        TradeCenterResponseBean tradeCenterBean = gson.fromJson((JsonElement) o,new TypeToken<TradeCenterResponseBean>() {}.getType());
                        callBack.success(tradeCenterBean);
                    }

                    /**
                     * 返回失败
                     *
                     * @param e
                     * @param isNetWorkError 是否是网络错误
                     * @throws Exception
                     */
                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.failed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.failed(e.getMessage());
                            }
                        } else {
                            callBack.failed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.failed(msg);
                    }
                });
    }

    public interface TradeCenterCallBack{
        void success(TradeCenterResponseBean tradeCenterResponseBean);
        void failed(String msg);
    }

}
