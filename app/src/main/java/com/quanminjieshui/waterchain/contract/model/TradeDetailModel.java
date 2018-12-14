package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderListResponseBean;
import com.quanminjieshui.waterchain.beans.TradeDetailResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by WanghongHe on 2018/12/12 18:08.
 * 成交明细
 */

public class TradeDetailModel {

    public void getTradeDetail(BaseActivity activity, int id, final TradeDetailCallBack callBack){
        HashMap<String , Object> params = new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .tradeDetail(RequestUtil.getRequestHashBody(params,false))
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
                        TradeDetailResponseBean tradeDetailBean = gson.fromJson((JsonElement) o,new TypeToken<OrderListResponseBean>() {}.getType());
                        callBack.success(tradeDetailBean);
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

    public interface TradeDetailCallBack{
        void success(TradeDetailResponseBean tradeDetailResponseBean);
        void failed(String msg);
    }

}
