package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.OrderListsResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

/**
 * Created by WanghongHe on 2018/12/12 17:27.
 * 洗地订单
 */

public class OrderListsModel {

    public void orderList(BaseActivity activity, final OrderListCallBack callBack){
        RetrofitFactory.getInstance().createService()
                .orderList(RequestUtil.getRequestHashBody(null,false))
                .compose(activity.<BaseEntity<OrderListsResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<OrderListsResponseBean>>io())
                .subscribe(new BaseObserver<OrderListsResponseBean>() {

                    /**
                     * 返回成功
                     *
                     * @param orderListsResponseBean
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(OrderListsResponseBean orderListsResponseBean) throws Exception {
                        callBack.success(orderListsResponseBean);
                    }

                    /**
                     * 返回失败
                     *
                     * @param e
                     * @param isNetWorkError 是否是网络错误
                     * @throws Exception
                     */
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

    public interface OrderListCallBack{
        void success(OrderListsResponseBean orderListBean);
        void failed(String msg);
    }
}
