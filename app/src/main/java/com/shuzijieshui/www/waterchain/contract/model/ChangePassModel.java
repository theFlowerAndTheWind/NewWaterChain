package com.shuzijieshui.www.waterchain.contract.model;

import android.text.TextUtils;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by WanghongHe on 2018/12/10 12:57.
 * 修改密码
 */

public class ChangePassModel {

    public void changePass(BaseActivity activity, String user_login, String old_pass, String new_pass, final ChangePassCallBack callBack){
        HashMap<String, Object> params = new HashMap<>();
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
                .changePass(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
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

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.failed(msg);
                    }
                });
    }

    public interface ChangePassCallBack{
        void success();
        void failed(String msg);
    }
}
