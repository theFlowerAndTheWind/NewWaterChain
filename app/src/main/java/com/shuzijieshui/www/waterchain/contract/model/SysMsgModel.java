package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.beans.SysMsg;
import com.shuzijieshui.www.waterchain.contract.model.callback.CommonCallback;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class SysMsgModel {
    public void getAdImg(BaseActivity activity, int count, final CommonCallback callback) {
        HashMap<String,Object> params = new HashMap<>();
        params.put("count",count);
        RetrofitFactory.getInstance().createService()
                .getSysMsg(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<List<SysMsg>>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<List<SysMsg>>>io())
                .subscribe(new BaseObserver<List<SysMsg>>(activity) {
                    @Override
                    protected void onSuccess(List<SysMsg> list) throws Exception {
                        callback.onRequestSucc(list);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onRequestFail(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onRequestFail(e.getMessage());
                            }
                        } else {
                            callback.onRequestFail("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onRequestFail(msg);
                    }
                });

    }
}
