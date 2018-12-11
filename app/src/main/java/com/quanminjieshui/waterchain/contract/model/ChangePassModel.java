package com.quanminjieshui.waterchain.contract.model;

import android.text.TextUtils;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by WanghongHe on 2018/12/10 12:57.
 * 修改密码
 */

public class ChangePassModel {

    public void changePass(BaseActivity activity, String user_login, String old_pass, String new_pass, final ChangePassCallBack callBack){
        HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(user_login)) {
            params.put("user_login", user_login);
        }
        if (!TextUtils.isEmpty(old_pass)) {
            params.put("old_pass", old_pass);
        }
        if (!TextUtils.isEmpty(old_pass)) {
            params.put("new_pass", new_pass);
        }
        RetrofitFactory.getInstance().createService()
                .changePass(RequestUtil.getRequestBody(params))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>download())
                .subscribe(new BaseObserver(activity) {
                    /**
                     * 返回成功
                     *
                     * @param o
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callBack.success();
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
                });
    }

    public interface ChangePassCallBack{
        void success();
        void failed(String msg);
    }
}
