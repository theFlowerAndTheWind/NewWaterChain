package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeListResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

/**
 * Created by WanghongHe on 2018/12/13 14:06.
 * 个人中心
 */

public class TradeListModel {
    public void getTradeList(BaseActivity activity, final TradeListCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .tradeList(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {
                    /**
                     * Provides the Observer with a new item to observe.
                     * <p>
                     * The {@link Observable} may call this method 0 or more times.
                     * <p>
                     * The {@code Observable} will not call this method again after it calls either {@link #onComplete} or
                     * {@link #onError}.
                     *
                     * @param o the item emitted by the Observable
                     */
                    @Override
                    public void onNext(Object o) {

                    }

                    /**
                     * 返回成功
                     *
                     * @param o
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        Gson gson = new Gson();
                        TradeListResponseBean tradeListBean = gson.fromJson((JsonElement) o,new TypeToken<TradeListResponseBean>(){}.getType());
                        callBack.success(tradeListBean);
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
                    }
                });

    }

    public interface TradeListCallBack{
        void success(TradeListResponseBean tradeListResponseBean);
        void failed(String msg);
    }
}
