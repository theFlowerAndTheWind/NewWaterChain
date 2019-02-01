package com.quanminjieshui.waterchain.contract.model;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.UserAddressResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

/**
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class UserAddressModel {
    public void getUserAddress(BaseActivity activity,final UserAddressCallBack callBack){

        RetrofitFactory.getInstance().createService()
                .getUserAddress(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<UserAddressResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<UserAddressResponseBean>>io())
                .subscribe(new BaseObserver<UserAddressResponseBean>(activity) {
                    @Override
                    protected void onSuccess(UserAddressResponseBean beans) throws Exception {
                        callBack.success(beans);
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

    public interface UserAddressCallBack{
        void success(UserAddressResponseBean beans);
        void failed(String msg);
    }
}
