package com.quanminjieshui.waterchain.contract.model;

import android.text.TextUtils;
import android.util.Log;

import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.WaterChainApplication;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.utils.AccountValidatorUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WanghongHe on 2018/12/3 11:04.
 */

public class LoginModel {
    /**
     * 提交信息是否合法，View中根据value值标红并清除控件内容
     * value    true->内容合法，控件内容不变
     *          false->内容不合法，edittext边框变红，内容清空
     */
    private Map<String, Boolean> verifyResult = new HashMap<String, Boolean>();

    /**
     * 验证edt内容是否合法
     * 必须在login前执行
     *
     * @param mobile
     * @param pwd
     * @return
     */
    public Map<String, Boolean> verify(final String mobile, final String pwd) {
        verifyResult.clear();
        WaterChainApplication context=WaterChainApplication.getInstance();
        if (!TextUtils.isEmpty(mobile) && AccountValidatorUtil.isMobile(mobile)) {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_mobile), false);
        }

        if (!TextUtils.isEmpty(pwd) && AccountValidatorUtil.isPassword(pwd)) {
            verifyResult.put(context.getString(R.string.key_edt_name_pwd), true);
        } else {
            verifyResult.put(context.getString(R.string.key_edt_name_pwd), false);
        }
        return verifyResult;
    }

    public void login(final BaseActivity activity, final String userName, String password, final LoginCallback callback) {

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
        Log.e("TAG","开始登陆请求");
        callback.loginSuccess();//开发用，后期需删除。使用下面代码
//        RetrofitFactory.getInstance().createService()
//                .login(userName, password)
//                .compose(activity.<BaseEntity<UserBean>>bindToLifecycle())//绑定activity生命周期，防止内存溢出
//                .compose(ObservableTransformerUtils.<BaseEntity<UserBean>>io())//选择线程
//                .subscribe(new BaseObserver<UserBean>(activity) {
//                    @Override
//                    protected void onSuccess(UserBean userBean) throws Exception {
////                        //保存token
////                        SPUtils.put(activity, HttpConfig.HEAD_TOKEN_KEY, userBean.getToken());
////                        SPUtils.put(activity, SPConfig.USERID_KEY, userBean.getUserId());
////                        SPUtils.put(activity, SPConfig.USER_NAME_KEY, userBean.getUserName());
////                        //保存用户信息
////                        Gson gson = new Gson();
////                        String userJson = gson.toJson(userBean);
////                        SPUtils.put(activity, SPConfig.USER_INFO, userJson);
//                        callback.loginSuccess();
//                    }
//
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        if (e != null && e.getMessage() != null) {
//                            if (isNetWorkError) {
//                                LogUtils.e(e.getMessage());
//                                callback.loginFailed(HttpConfig.ERROR_MSG);
//                            } else {
//                                callback.loginFailed(e.getMessage());
//                            }
//                        } else {
//                            callback.loginFailed("");
//                        }
//                    }
//
//                    @Override
//                    protected void onCodeError(String code, String msg) throws Exception {
//                        super.onCodeError(code, msg);
//                        callback.loginFailed(msg);
//                    }
//                });

    }

    public interface LoginCallback {
        void onEdtContentsLegal();

        void onEdtContentsIllegal(Map<String,Boolean>verify);

        void loginSuccess();

        void loginFailed(String msg);
    }

}
