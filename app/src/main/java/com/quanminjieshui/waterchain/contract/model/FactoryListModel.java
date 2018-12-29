package com.quanminjieshui.waterchain.contract.model;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.FactoryListResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by songxiaotao on 2018/12/20.
 * Class Note:洗涤商城
 */

public class FactoryListModel {

    public void getFactoryList(BaseActivity activity,int count,final FactoryListCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("count",count);
        RetrofitFactory.getInstance().createService()
                .factoryList(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<List<FactoryListResponseBean>>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<List<FactoryListResponseBean>>>io())
                .subscribe(new BaseObserver<List<FactoryListResponseBean>>(activity) {
                    @Override
                    protected void onSuccess(List<FactoryListResponseBean> factoryListResponseBean) throws Exception {
                        callBack.success(factoryListResponseBean);
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

    public interface FactoryListCallBack{
        void success(List<FactoryListResponseBean> factoryListEntities);
        void failed(String msg);
    }
}
