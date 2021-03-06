/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RegisterModel
 * Author: sxt
 * Date: 2018/12/8 11:51 AM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.shuzijieshui.www.waterchain.contract.model;

import android.text.TextUtils;
import android.util.Log;

import com.shuzijieshui.www.waterchain.R;
import com.shuzijieshui.www.waterchain.WaterChainApplication;
import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.RegisterResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.AccountValidatorUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;
import com.shuzijieshui.www.waterchain.utils.SPUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: RegisterModel
 * @Description:
 * @Author: sxt
 * @Date: 2018/12/8 11:51 AM
 */
public class RegisterModel {
    private static final String TAG = "RegisterModel";
    private Map<String, Boolean> verifyResult = new HashMap<String, Boolean>();
    private WaterChainApplication context = WaterChainApplication.getInstance();
    private String verCode;

    public Map<String, Boolean> verify(final String mobile) {
        verifyResult.clear();
        if (!TextUtils.isEmpty(mobile) && AccountValidatorUtil.isMobile(mobile)) {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), false);
        }

        return verifyResult;
    }

    public void getSms(final BaseActivity activity, final String mobile, final RegisterCallback callback) {
        int Illegal = 0;
        for (Map.Entry<String, Boolean> entry : verifyResult.entrySet()) {
            final Boolean value = entry.getValue();
            if (!value) {
                Illegal += 1;
            }
        }
        if (Illegal == 0) {
            callback.onEdtContentsLegal();
        } else {
            callback.onEdtContentsIllegal(verifyResult);
            return;
        }
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_login", mobile);
        RetrofitFactory.getInstance().createService()
                .getSms(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity>bindToLifecycle())//绑定activity生命周期，防止内存溢出
                .compose(ObservableTransformerUtils.<BaseEntity>io())//选择线程
                .subscribe(new BaseObserver(activity) {

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.onGetSmsSuccess();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onGetSmsFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onGetSmsFailed(e.getMessage());
                            }
                        } else {
                            callback.onGetSmsFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onGetSmsFailed(msg);
                    }
                });
    }

    public Map<String, Boolean> verify(final String mobile, final String pwd, final String confirm, final String sms, final String invitation, final boolean agreement) {
        verifyResult.clear();
        if (!TextUtils.isEmpty(mobile) && AccountValidatorUtil.isMobile(mobile)) {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), false);
        }
        if(!TextUtils.isEmpty(sms)&& AccountValidatorUtil.isVertifyCode(sms)){
            verifyResult.put(context.getString(R.string.key_edt_name_sms), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_sms), false);
        }
        if(!TextUtils.isEmpty(pwd)&&pwd.equals(confirm)&&AccountValidatorUtil.isPassword(pwd)&&AccountValidatorUtil.isPassword(confirm)){
            verifyResult.put(context.getString(R.string.key_edt_name_pwd), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_pwd), false);
        }
        if(agreement){
            verifyResult.put(context.getString(R.string.key_checkbox_agreement), true);
        } else {
            verifyResult.put(context.getString(R.string.key_checkbox_agreement), false);
        }

        return verifyResult;
    }

    public void register(final BaseActivity activity, final String mobile, final String pwd, final String confirm, final String sms, final String invitation, final boolean agreement, final RegisterCallback callback) {
        int Illegal = 0;
        for (Map.Entry<String, Boolean> entry : verifyResult.entrySet()) {
            final Boolean value = entry.getValue();
            if (!value) {
                Illegal += 1;
            }
        }
        if (Illegal == 0) {
            callback.onEdtContentsLegal();
        } else {
            callback.onEdtContentsIllegal(verifyResult);
            return;
        }
        Log.e("TAG", "开始注册请求");
        HashMap<String, Object> params = new HashMap<>();
        params.put("user_login", mobile);
        params.put("ver_code", sms);
        params.put("user_pass", pwd);
        params.put("inv_code", invitation);
        RetrofitFactory.getInstance().createService()
                .register(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity<RegisterResponseBean>>bindToLifecycle())//绑定activity生命周期，防止内存溢出
                .compose(ObservableTransformerUtils.<BaseEntity<RegisterResponseBean>>io())//选择线程
                .subscribe(new BaseObserver<RegisterResponseBean>(activity) {
                    @Override
                    protected void onSuccess(RegisterResponseBean bean) throws Exception {
                        SPUtil.insert(activity,SPUtil.UID,bean.getUid());
                        SPUtil.insert(activity,SPUtil.USER_LOGIN,bean.getUser_login());
                        SPUtil.insert(activity,SPUtil.TOKEN,bean.getToken());
                        callback.onRegisterSuccess(bean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRegisterFaild(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRegisterFaild(e.getMessage());
                            }
                        } else {
                            callback.onRegisterFaild("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onRegisterFaild(msg);
                    }
                });

        verCode="";//清除

    }

    public Map<String, Boolean> getVerifyResult() {
        return Collections.unmodifiableMap(verifyResult);
    }

    public interface RegisterCallback {

        void onEdtContentsLegal();

        void onEdtContentsIllegal(Map<String, Boolean> verify);

        void onGetSmsSuccess();

        void onGetSmsFailed(String msg);

        void onRegisterSuccess(RegisterResponseBean registerResponseBean);

        void onRegisterFaild(String msg);
    }

}
