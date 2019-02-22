package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

public class ChangeAvatarModel {
    public void changeAvatar(BaseActivity activity,String url,final ChangeAvatarCallBack callBack){
        HashMap<String,Object>params=new HashMap<>();
        params.put("url",url);
        RetrofitFactory.getInstance().createService()
                .changeAvatar(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callBack.changeAvatarSucc();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.changeAvatarFail(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.changeAvatarFail(e.getMessage());
                            }
                        } else {
                            callBack.changeAvatarFail("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.changeAvatarFail(msg);
                    }
                });
    }

    public interface ChangeAvatarCallBack{
        void changeAvatarSucc();
        void changeAvatarFail(String msg);
    }
}
