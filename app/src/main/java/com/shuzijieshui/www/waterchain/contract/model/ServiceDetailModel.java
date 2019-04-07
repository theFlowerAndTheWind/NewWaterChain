package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.FactoryServiceResponseBean;
import com.shuzijieshui.www.waterchain.beans.ServiceDetail;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

public class ServiceDetailModel {
    public void getServiceDetail(BaseActivity activity, int fsid, final CommonCallback callBack){

        HashMap<String,Object> params = new HashMap<>();
        params.put("fsid",fsid);
        RetrofitFactory.getInstance().createService()
                .serviceDetail(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<ServiceDetail>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<ServiceDetail>>io())
                .subscribe(new BaseObserver<ServiceDetail>() {
                    @Override
                    protected void onSuccess(ServiceDetail serviceDetail) throws Exception {
                        callBack.onRequestSucc(serviceDetail);
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
}
