package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.TotalPriceRes;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by songxiaotao on 2018/12/26.
 * Class Note:
 */

public class TotalPriceModel {
//    public void getTotalPrice(BaseActivity activity, int pay_cate,  String trade_detail,final TotalPriceCallBack callBack){
//
//        HashMap<String,Object> params = new HashMap<>();
//        params.put("pay_cate",pay_cate);
//        params.put("trade_detail",trade_detail);
//        RetrofitFactory.getInstance().createService()
//                .totalPrice(RequestUtil.getRequestHashBody(params,false))
//                .compose(activity.<BaseEntity<TotalPriceResponseBean>>bindToLifecycle())
//                .compose(ObservableTransformerUtils.<BaseEntity<TotalPriceResponseBean>>io())
//                .subscribe(new BaseObserver<TotalPriceResponseBean>(activity) {
//                    @Override
//                    protected void onSuccess(TotalPriceResponseBean totalPriceResponseBean) throws Exception {
//                        callBack.success(totalPriceResponseBean);
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        if (e != null && e.getMessage() != null) {
//                            if (isNetWorkError) {
//                                LogUtils.e(e.getMessage());
//                                callBack.failed(HttpConfig.ERROR_MSG);
//                            } else {
//                                callBack.failed(e.getMessage());
//                            }
//                        } else {
//                            callBack.failed("");
//                        }
//                    }
//
//                    @Override
//                    protected void onCodeError(String code, String msg) throws Exception {
//                        super.onCodeError(code, msg);
//                        callBack.failed(msg);
//                    }
//                });
//    }

    public void getTotalPrice(BaseActivity activity, float count,  long id,final CommonCallback callBack){

        HashMap<String,Object> params = new HashMap<>();
        params.put("count",count);
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .getTotalPrice(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<TotalPriceRes>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<TotalPriceRes>>io())
                .subscribe(new BaseObserver<TotalPriceRes>(activity) {
                    @Override
                    protected void onSuccess(TotalPriceRes totalPriceRes) throws Exception {
                        callBack.onRequestSucc(totalPriceRes);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.onRequestFail(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.onRequestFail(e.getMessage());
                            }
                        } else {
                            callBack.onRequestFail("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.onRequestFail(msg);
                    }
                });
    }

//    public interface TotalPriceCallBack{
//        void success(TotalPriceResponseBean totalPriceResponseBean);
//        void failed(String msg);
//    }
}
