package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.CheckUserPayResponseBean;
import com.shuzijieshui.www.waterchain.beans.request.CheckUserPayReqBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

/**
 * Created by songxiaotao on 2019/2/1.
 * Class Note:
 */

public class CheckUserPayModel {

    public void checkUserPay(BaseActivity activity, CheckUserPayReqBean bean,final CheckUserPayCallBack callBack){

        RetrofitFactory.getInstance().createService()
                .checkUserPay(RequestUtil.getRequestBeanBody(bean,false))
                .compose(activity.<BaseEntity<CheckUserPayResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<CheckUserPayResponseBean>>io())
                .subscribe(new BaseObserver<CheckUserPayResponseBean>(activity) {
                    @Override
                    protected void onSuccess(CheckUserPayResponseBean beans) throws Exception {
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

    public interface CheckUserPayCallBack{
        void success(CheckUserPayResponseBean beans);
        void failed(String msg);
    }
}
