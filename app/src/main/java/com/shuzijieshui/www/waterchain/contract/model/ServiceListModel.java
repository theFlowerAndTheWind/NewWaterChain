package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.ServiceEntity;
import com.shuzijieshui.www.waterchain.beans.ServiceListResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class ServiceListModel {
    public void getSrviceList(BaseActivity activity,int count,final ServiceListCallBack callBack){
        HashMap<String, Object> params = new HashMap<>();
        //以下两个参数接口doc中要求传递，后接口文档修改，无参
        params.put("count", count);
        RetrofitFactory.getInstance().createService()
                .serviceList(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<ServiceListResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<ServiceListResponseBean>>io())
                .subscribe(new BaseObserver<ServiceListResponseBean>(activity) {

                    @Override
                    protected void onSuccess(ServiceListResponseBean serviceListResponseBean) throws Exception {
                        callBack.success(serviceListResponseBean.getLists());
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
        void success(List<ServiceEntity> serviceListEntity);
        void failed(String msg);
    }
}
