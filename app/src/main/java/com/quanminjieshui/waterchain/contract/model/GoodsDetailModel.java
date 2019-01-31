package com.quanminjieshui.waterchain.contract.model;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.GoodsDetailResponseBean;
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
 * Created by songxiaotao on 2019/1/31.
 * Class Note:
 */

public class GoodsDetailModel {
    public void getGoodsDetail(BaseActivity activity,int id,final GoodsDetailCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("id",id);
        RetrofitFactory.getInstance().createService()
                .goodsDetail(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<List<GoodsDetailResponseBean>>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<List<GoodsDetailResponseBean>>>io())
                .subscribe(new BaseObserver<List<GoodsDetailResponseBean>>(activity) {
                    @Override
                    protected void onSuccess(List<GoodsDetailResponseBean> beans) throws Exception {
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

    public interface GoodsDetailCallBack{
        void success(List<GoodsDetailResponseBean> beans);
        void failed(String msg);
    }
}
