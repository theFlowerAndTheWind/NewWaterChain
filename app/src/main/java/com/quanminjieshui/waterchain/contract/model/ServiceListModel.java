package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.ServiceListResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListModel {
    public void getSrviceList(BaseActivity activity,final ServiceListCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .serviceList(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {
                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        Gson gson = new Gson();
                        ServiceListResponseBean serviceListBean = gson.fromJson((JsonElement) o,new TypeToken<ServiceListResponseBean>() {}.getType());
                        callBack.success(serviceListBean.getLists());
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

    public interface ServiceListCallBack{
        void success(List<ServiceListResponseBean.serviceListEntity> serviceListEntity);
        void failed(String msg);
    }
}
