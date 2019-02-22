package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

public class AdImgModel {

    public void getAdImg(BaseActivity activity, String option_name, final AdImgCallback callback) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("option_name", option_name);
        RetrofitFactory.getInstance().createService()
                .getAdImg(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity<AdImgResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<AdImgResponseBean>>io())
                .subscribe(new BaseObserver<AdImgResponseBean>(activity) {
                    @Override
                    protected void onSuccess(AdImgResponseBean bean) throws Exception {
                        callback.onGetAdImgSuccess(bean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onGetAdImgFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onGetAdImgFailed(e.getMessage());
                            }
                        } else {
                            callback.onGetAdImgFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onGetAdImgFailed(msg);
                    }
                });
    }

    public interface AdImgCallback {
        void onGetAdImgSuccess(AdImgResponseBean bean);

        void onGetAdImgFailed(String msg);
    }

}
