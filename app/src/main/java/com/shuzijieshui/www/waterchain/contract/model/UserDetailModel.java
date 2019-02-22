package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.UserDetailResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

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
