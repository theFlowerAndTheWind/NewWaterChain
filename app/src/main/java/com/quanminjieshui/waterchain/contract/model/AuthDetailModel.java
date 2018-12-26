package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.AuthDetailResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:用户身份认证信息
 */

public class AuthDetailModel {
    public void getAuthDetail(BaseActivity activity, final AuthDetailCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .authDetail(RequestUtil.getRequestBeanBody(null,false))
                .compose(activity.<BaseEntity<AuthDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<AuthDetailResponseBean>>io())
                .subscribe(new BaseObserver<AuthDetailResponseBean>(activity) {

                    @Override
                    protected void onSuccess(AuthDetailResponseBean authDetailBean) throws Exception {
                        callBack.success(authDetailBean);
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

    public interface AuthDetailCallBack{
        void success(AuthDetailResponseBean authDetailResponseBean);
        void failed(String msg);
    }
}
