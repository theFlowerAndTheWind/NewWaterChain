/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FindPassModel
 * Author: sxt
 * Date: 2018/12/9 2:15 AM
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
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.AccountValidatorUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: FindPassModel
 * @Description: java类作用描述
 * @Author: sxt
 * @Date: 2018/12/9 2:15 AM
 */
public class FindPassModel {
    private Map<String, Boolean> verifyResult = new HashMap<String, Boolean>();
    private WaterChainApplication context = WaterChainApplication.getInstance();

    public Map<String, Boolean> verify(final String mobile) {
        verifyResult.clear();
        if (!TextUtils.isEmpty(mobile) && AccountValidatorUtil.isMobile(mobile)) {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), false);
        }

        return verifyResult;
    }

    public void getSms(final BaseActivity activity, final String mobile, final FindPassCallback callback) {
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
        Log.e("TAG", "开始获取短信请求");

        HashMap<String,Object>params=new HashMap<>();
        params.put("user_login",mobile);
        RetrofitFactory.getInstance().createService()
                .getSms(RequestUtil.getRequestHashBody(params,false))
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

    public Map<String, Boolean> verify(final String mobile, final String pwd,final String confirm, final String sms) {
        verifyResult.clear();
        if (!TextUtils.isEmpty(mobile) && AccountValidatorUtil.isMobile(mobile)) {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), false);
        }
        if(!TextUtils.isEmpty(sms)/*&&sms.length()==4*/){
            verifyResult.put(context.getString(R.string.key_edt_name_sms),true);
        }else{
            verifyResult.put(context.getString(R.string.key_edt_name_sms),false);
        }
        if(!TextUtils.isEmpty(pwd)&&pwd.equals(confirm)&&AccountValidatorUtil.isPassword(pwd)&&AccountValidatorUtil.isPassword(confirm)){
            verifyResult.put(context.getString(R.string.key_edt_name_pwd),true);
        }else{
            verifyResult.put(context.getString(R.string.key_edt_name_pwd),false);
        }

        return verifyResult;
    }

    public void findPass(final BaseActivity activity, final String mobile, final String pwd, final String confirm, final String sms, final FindPassCallback callback) {
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
        Log.e("TAG", "开始重置请求");

        HashMap<String, Object> params = new HashMap<>();
        params.put(context.getString(R.string.field_mobile), mobile);
        params.put(context.getString(R.string.field_pwd), pwd);
        params.put(context.getString(R.string.field_sms), sms);
        RetrofitFactory.getInstance().createService()
                .findPass(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity>bindToLifecycle())//绑定activity生命周期，防止内存溢出
                .compose(ObservableTransformerUtils.<BaseEntity>io())//选择线程
                .subscribe(new BaseObserver(activity) {
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.onFindPassSuccess();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onFindPassFaild(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onFindPassFaild(e.getMessage());
                            }
                        } else {
                            callback.onFindPassFaild("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onFindPassFaild(msg);
                    }
                });

    }

    public Map<String,Boolean> getVerifyResult(){
        return Collections.unmodifiableMap(verifyResult);
    }

    public interface FindPassCallback {

        void onEdtContentsLegal();

        void onEdtContentsIllegal(Map<String, Boolean> verify);

        void onGetSmsSuccess();

        void onGetSmsFailed(String msg);

        void onFindPassSuccess();

        void onFindPassFaild(String msg);
    }

}
