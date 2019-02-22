package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.TradeDetailResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

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
                .compose(activity.<BaseEntity<TradeDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TradeDetailResponseBean>>io())
                .subscribe(new BaseObserver<TradeDetailResponseBean>(activity) {

                    /**
                     * 返回成功
                     *
                     * @param tradeDetailBean
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(TradeDetailResponseBean tradeDetailBean) throws Exception {
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
