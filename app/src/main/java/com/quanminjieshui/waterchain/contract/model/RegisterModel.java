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
package com.quanminjieshui.waterchain.contract.model;

import android.text.TextUtils;
import android.util.Log;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.WaterChainApplication;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.AccountValidatorUtil;

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
        Log.e("TAG", "开始获取短信请求");
        callback.onGetSmsSuccess();//无接口，开发用，生产时删除！！！！
//        RetrofitFactory.getInstance().createService()
//                .getSms(mobile)
//                .compose(activity.<BaseEntity<SmsResponseBean>>bindToLifecycle())//绑定activity生命周期，防止内存溢出
//                .compose(ObservableTransformerUtils.<BaseEntity<SmsResponseBean>>io())//选择线程
//                .subscribe(new BaseObserver<SmsResponseBean>(activity) {
//                    @Override
//                    protected void onSuccess(SmsResponseBean bean) throws Exception {
//                        //todo save sth. or do sth.
//                        callback.onGetSmsSuccess();
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        if (e != null && e.getMessage() != null) {
//                            if (isNetWorkError) {
//                                LogUtils.e(e.getMessage());
//                                callback.onGetSmsFailed(HttpConfig.ERROR_MSG);
//                            } else {
//                                callback.onGetSmsFailed(e.getMessage());
//                            }
//                        } else {
//                            callback.onGetSmsFailed("");
//                        }
//                    }
//
//                    @Override
//                    protected void onCodeError(String code, String msg) throws Exception {
//                        super.onCodeError(code, msg);
//                        callback.onGetSmsFailed(msg);
//                    }
//                });
    }

    public Map<String, Boolean> verify(final String mobile, final String pwd,final String confirm, final String sms, final String invitation, final boolean agreement) {
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
        if(agreement){
            verifyResult.put(context.getString(R.string.key_checkbox_agreement),true);
        }else{
            verifyResult.put(context.getString(R.string.key_checkbox_agreement),false);
        }
        return verifyResult;
    }

    public void register(final BaseActivity activity, final String mobile, final String pwd,final String confirm, final String sms, final String invitation, final boolean agreement, final RegisterCallback callback) {
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
        callback.onRegisterSuccess();

//        Map<String, Object> form = new HashMap<>();
//        form.put(context.getString(R.string.field_mobile), mobile);
//        form.put(context.getString(R.string.field_pwd), pwd);
//        form.put(context.getString(R.string.field_sms), sms);
//        form.put(context.getString(R.string.field_invitation), invitation);
//        form.put("agreement", agreement);
//        RetrofitFactory.getInstance().createService()
//                .register(form)
//                .compose(activity.<BaseEntity<RegisterResponseBean>>bindToLifecycle())//绑定activity生命周期，防止内存溢出
//                .compose(ObservableTransformerUtils.<BaseEntity<RegisterResponseBean>>io())//选择线程
//                .subscribe(new BaseObserver<RegisterResponseBean>(activity) {
//                    @Override
//                    protected void onSuccess(RegisterResponseBean bean) throws Exception {
//                        //todo save sth. or do sth.
//                        callback.onRegisterSuccess();
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        if (e != null && e.getMessage() != null) {
//                            if (isNetWorkError) {
//                                LogUtils.e(e.getMessage());
//                                callback.onRegisterFaild(HttpConfig.ERROR_MSG);
//                            } else {
//                                callback.onRegisterFaild(e.getMessage());
//                            }
//                        } else {
//                            callback.onRegisterFaild("");
//                        }
//                    }
//
//                    @Override
//                    protected void onCodeError(String code, String msg) throws Exception {
//                        super.onCodeError(code, msg);
//                        callback.onRegisterFaild(msg);
//                    }
//                });

    }

    public Map<String,Boolean> getVerifyResult(){
        return Collections.unmodifiableMap(verifyResult);
    }

    public interface RegisterCallback {

        void onEdtContentsLegal();

        void onEdtContentsIllegal(Map<String, Boolean> verify);

        void onGetSmsSuccess();

        void onGetSmsFailed(String msg);

        void onRegisterSuccess();

        void onRegisterFaild(String msg);
    }

}
