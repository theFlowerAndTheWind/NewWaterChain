package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.UserDetailResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:用户信息
 */

public class UserDetailModel {

    public void getUserDetail(BaseActivity activity, final UserDetailCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .userDetail(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<UserDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<UserDetailResponseBean>>io())
                .subscribe(new BaseObserver<UserDetailResponseBean>() {

                    @Override
                    protected void onSuccess(UserDetailResponseBean userDetailBean) throws Exception {
                        callBack.success(userDetailBean);
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

    public interface UserDetailCallBack{
        void success(UserDetailResponseBean userDetailResponseBean);
        void failed(String msg);
    }
}
