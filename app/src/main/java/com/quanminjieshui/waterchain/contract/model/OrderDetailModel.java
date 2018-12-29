package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by WanghongHe on 2018/12/12 15:26.
 * 订单详情
 */

public class OrderDetailModel {

    public void getOrderDetail(BaseActivity activity,int id, final OrderDetailCallBack callBack){
        HashMap<String,Object>params=new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .orderDetail(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<OrderDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<OrderDetailResponseBean>>io())
                .subscribe(new BaseObserver<OrderDetailResponseBean>(activity) {

                    /**
                     * 返回成功
                     *
                     * @param orderDetailResponseBean
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(OrderDetailResponseBean orderDetailResponseBean) throws Exception {
                        callBack.onOrderDetailSuccess(orderDetailResponseBean);
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
                                callBack.onOrderDetailFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.onOrderDetailFailed(e.getMessage());
                            }
                        } else {
                            callBack.onOrderDetailFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.onOrderDetailFailed(msg);
                    }
                });
    }


    public interface OrderDetailCallBack{
        void onOrderDetailSuccess(OrderDetailResponseBean orderDetailResponseBeans);
        void onOrderDetailFailed(String msg);
    }
}
