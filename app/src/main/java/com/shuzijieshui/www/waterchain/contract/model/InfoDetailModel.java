package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.InfoDetailRespoonseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * Created by songxiaotao on 2018/12/13.
 * Class Note:平台咨讯详情
 */

public class InfoDetailModel {

    public void getInfoDetail(BaseActivity activity, int id, final InfoDetailCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .infoDetail(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<InfoDetailRespoonseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<InfoDetailRespoonseBean>>io())
                .subscribe(new BaseObserver<InfoDetailRespoonseBean>(activity) {
                    @Override
                    protected void onSuccess(InfoDetailRespoonseBean infoDetailRespoonseBeans) throws Exception {
                        callBack.success(infoDetailRespoonseBeans);
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

    public interface InfoDetailCallBack{
        void success(InfoDetailRespoonseBean infoDetailRespoonseBean);
        void failed(String msg);
    }
}
