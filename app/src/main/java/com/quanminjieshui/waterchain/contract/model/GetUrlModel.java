package com.quanminjieshui.waterchain.contract.model;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.GetUrlResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;

public class GetUrlModel {
    public void getUrl(BaseActivity activity,String type,final GetUrlCallback callback){
        HashMap<String,Object>params=new HashMap<>();
        params.put("type",type);
        RetrofitFactory.getInstance().createService()
                .getUrl(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<GetUrlResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<GetUrlResponseBean>>io())
                .subscribe(new BaseObserver<GetUrlResponseBean>(activity) {
                    @Override
                    protected void onSuccess(GetUrlResponseBean getUrlResponseBean) throws Exception {
                        callback.onGetUrlSucc(getUrlResponseBean);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onGetUrlFail(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onGetUrlFail(e.getMessage());
                            }
                        } else {
                            callback.onGetUrlFail("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onGetUrlFail(msg);
                    }
                });
    }

    public interface GetUrlCallback{
        void onGetUrlSucc(GetUrlResponseBean getUrlResponseBean);
        void onGetUrlFail(String msg);
    }


}
