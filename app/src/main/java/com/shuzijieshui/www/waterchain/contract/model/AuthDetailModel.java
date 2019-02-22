package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AuthDetailResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

/**
 * Created by songxiaotao on 2018/12/12.
 * Class Note:用户身份认证信息
 */

public class AuthDetailModel {
    public void getAuthDetail(BaseActivity activity, final AuthDetailCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .authDetail(RequestUtil.getRequestBeanBody(null,false))
                .compose(activity.<BaseEntity<AuthDetailResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<AuthDetailResponseBean>>io())
                .subscribe(new BaseObserver<AuthDetailResponseBean>(activity) {

                    @Override
                    protected void onSuccess(AuthDetailResponseBean authDetailBean) throws Exception {
                        callBack.success(authDetailBean);
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

    public interface AuthDetailCallBack{
        void success(AuthDetailResponseBean authDetailResponseBean);
        void failed(String msg);
    }
}
