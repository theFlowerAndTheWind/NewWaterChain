/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.contract.model
 * @ClassName: TradeListsModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
package com.quanminjieshui.waterchain.contract.model;

import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.TradeListsResponseBean;
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
 *
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.waterchain.contract.model
 * @ClassName: TradeListsModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2018/12/27 7:10 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/12/27 7:10 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class TradeListsModel {

    public void getTradeLists(BaseActivity activity, final TradeListsCallback callback){

        RetrofitFactory.getInstance().createService()
                .tradeList(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {
                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callback.onGetTradeListsSuccess(o);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callback.onGetTradeListsFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callback.onGetTradeListsFailed(e.getMessage());
                            }
                        } else {
                            callback.onGetTradeListsFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callback.onGetTradeListsFailed(msg);
                    }
                });
    }


    public interface TradeListsCallback{
        void onGetTradeListsSuccess(Object o);//参数类型待确定
        void onGetTradeListsFailed(String msg);
    }


}
