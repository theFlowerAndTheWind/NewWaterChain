package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.InfoListsResponseBean;
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
 * Class Note:
 */

public class InfoListModel {

    public void getInfoList(BaseActivity activity, int count, final InfoListCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("count",count);
        RetrofitFactory.getInstance().createService()
                .infoList(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<InfoListsResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<InfoListsResponseBean>>io())
                .subscribe(new BaseObserver<InfoListsResponseBean>() {

                    @Override
                    protected void onSuccess(InfoListsResponseBean infoListsResponseBean) throws Exception {
                        callBack.success(infoListsResponseBean);
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

    public interface InfoListCallBack{
        void success(InfoListsResponseBean infoListResponseBean);
        void failed(String msg);
    }
}
