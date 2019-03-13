package com.shuzijieshui.www.waterchain.contract.model;

import com.google.gson.Gson;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CreateOrderResponseBean;
import com.shuzijieshui.www.waterchain.beans.CreateOrderResponseBean1;
import com.shuzijieshui.www.waterchain.beans.request.CreateOrderReqParams;
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
 * Class Note:创建订单
 */

public class CreateOrderModel {
    public void createOrder(BaseActivity activity, CreateOrderReqParams params, final CreateOrderCallBack callBack) {
        HashMap<String, Object> map = new HashMap<>();
        final String payType = params.getPay_type();
        map.put("fsid", params.getFsid());
        map.put("trade_detail", params.getTrade_detail());
        map.put("express", params.getExpress());
        map.put("contact_name", params.getContact_name());
        map.put("contact_tel", params.getContact_tel());
        map.put("province", params.getProvince());
        map.put("city", params.getCity());
        map.put("address", params.getAddress());
        map.put("pay_cate", params.getPay_cate());
        map.put("pickup_time", params.getPickup_time());
        map.put("pay_type", payType);
        RetrofitFactory.getInstance().createService()
                .createOrder(RequestUtil.getRequestHashBody(map, false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        Gson gson = new Gson();
                        String json = gson.toJson(o);
                        if (payType.equals("1")) {//ali
                            CreateOrderResponseBean bean = gson.fromJson(json, CreateOrderResponseBean.class);
                            callBack.success(bean);
                        } else if (payType.equals("2")) {//wx
                            CreateOrderResponseBean1 bean1 = gson.fromJson(json, CreateOrderResponseBean1.class);
                            callBack.success(bean1);
                        }
                    }

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

    public interface CreateOrderCallBack {
        void success(Object o);

        void failed(String msg);
    }
//    public void createOrder(BaseActivity activity, CreateOrderReqParams params,final CreateOrderCallBack callBack){
//        HashMap<String,Object>map=new HashMap<>();
//        map.put("fsid",params.getFsid());
//        map.put("trade_detail",params.getTrade_detail());
//        map.put("express",params.getExpress());
//        map.put("contact_name",params.getContact_name());
//        map.put("contact_tel",params.getContact_tel());
//        map.put("province",params.getProvince());
//        map.put("city",params.getCity());
//        map.put("address",params.getAddress());
//        map.put("pay_cate",params.getPay_cate());
//        map.put("pickup_time",params.getPickup_time());
//        map.put("pay_type",params.getPay_type());
//        RetrofitFactory.getInstance().createService()
//                .createOrder(RequestUtil.getRequestHashBody(map,false))
//                .compose(activity.<BaseEntity<CreateOrderResponseBean>>bindToLifecycle())
//                .compose(ObservableTransformerUtils.<BaseEntity<CreateOrderResponseBean>>io())
//                .subscribe(new BaseObserver<CreateOrderResponseBean>(activity) {
//                    @Override
//                    protected void onSuccess(CreateOrderResponseBean createOrderResponseBean) throws Exception {
//                        callBack.success(createOrderResponseBean);
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
//
//    public interface CreateOrderCallBack{
//        void success(CreateOrderResponseBean createOrderResponseBean);
//        void failed(String msg);
//    }
}
