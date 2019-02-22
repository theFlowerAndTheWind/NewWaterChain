package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

public class MvJslModel {

    public void mvJsl(BaseActivity activity,String user_login,String user_name,String total,String reason,final MvJslCallback callback){
        HashMap<String,Object>params=new HashMap<>();
        params.put("user_login",user_login);
        params.put("user_name",user_name);
        params.put("total",total);
        params.put("reason",reason);
        RetrofitFactory.getInstance().createService()
                .mvJsl(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.success();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.failed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.failed(e.getMessage());
                            }
                        } else {
                            callback.failed("");
                        }

                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.failed(msg);
                    }
                });

    }


    public interface MvJslCallback{
        void success();
        void failed(String msg);
    }

}
