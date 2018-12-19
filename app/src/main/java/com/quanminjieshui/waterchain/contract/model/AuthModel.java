package com.quanminjieshui.waterchain.contract.model;

import android.util.Log;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BaseBean;
import com.quanminjieshui.waterchain.beans.SmsResponseBean;
import com.quanminjieshui.waterchain.beans.request.CompanyAuthReqParams;
import com.quanminjieshui.waterchain.beans.request.PersonalAuthReqParams;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

public class AuthModel {
    private static final String TAG = "AuthModel";

    public void auth(final BaseActivity activity,final boolean user_type, final BaseBean params, AuthCallback callback) {
        if (user_type) {//企业认证
            companyAuthor(activity, (CompanyAuthReqParams) params, callback);
        } else {//个人认证
            personalAuthor(activity, (PersonalAuthReqParams) params, callback);
        }
    }

    private void companyAuthor(final BaseActivity activity, CompanyAuthReqParams companyAuthReqParams, final AuthCallback callback) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("province", companyAuthReqParams.getProvince());
        params.put("city", companyAuthReqParams.getCity());
        params.put("company_name", companyAuthReqParams.getCompany_name());
        params.put("company_license_no", companyAuthReqParams.getCompany_license_no());
        params.put("company_license_img", companyAuthReqParams.getCompany_license_img());
        params.put("company_boss_name", companyAuthReqParams.getCompany_boss_name());
        params.put("company_boss_tel", companyAuthReqParams.getCompany_boss_tel());
        params.put("id_img_a", companyAuthReqParams.getId_img_a());
        params.put("id_img_b", companyAuthReqParams.getId_img_b());
        params.put("company_other_name", companyAuthReqParams.getCompany_other_name());
        params.put("company_other_tel", companyAuthReqParams.getCompany_other_tel());
        Log.e(TAG,"发起企业认证请求");
        RetrofitFactory.getInstance().createService()
                .companyAuth(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity>bindToLifecycle())//绑定activity生命周期，防止内存溢出
                .compose(ObservableTransformerUtils.<BaseEntity>io())//选择线程
                .subscribe(new BaseObserver(activity) {

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.onCompanyAuthSuccess();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onCompanyAuthFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onCompanyAuthFailed(e.getMessage());
                            }
                        } else {
                            callback.onCompanyAuthFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onCompanyAuthFailed(msg);
                    }
                });


    }

    private void personalAuthor(BaseActivity activity, PersonalAuthReqParams personalAuthReqParams, final AuthCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("nationality", personalAuthReqParams.getNationality());
        params.put("province", personalAuthReqParams.getProvince());
        params.put("city", personalAuthReqParams.getCity());
        params.put("user_name", personalAuthReqParams.getUser_name());
        params.put("id_no", personalAuthReqParams.getId_no());
        params.put("id_img_a", personalAuthReqParams.getId_img_a());
        params.put("id_img_b", personalAuthReqParams.getId_img_b());
        Log.e(TAG,"发起个人认证请求");
        RetrofitFactory.getInstance().createService()
                .personalAuth(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity>bindToLifecycle())//绑定activity生命周期，防止内存溢出
                .compose(ObservableTransformerUtils.<BaseEntity>io())//选择线程
                .subscribe(new BaseObserver(activity) {

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.onPersonalAuthSuccess();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onPersonalAuthFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onPersonalAuthFailed(e.getMessage());
                            }
                        } else {
                            callback.onPersonalAuthFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onPersonalAuthFailed(msg);
                    }
                });

    }


    public interface AuthCallback {

        void onEdtContentsLegal();

        void onEdtContentsIllegal(Map<String, Boolean> verify);

        void onCompanyAuthSuccess();

        void onCompanyAuthFailed(String msg);

        void onPersonalAuthSuccess();

        void onPersonalAuthFailed(String msg);

    }
}
