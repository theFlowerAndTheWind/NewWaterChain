package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sxt on 2019/4/14.
 * Class Note:
 */

public class GoodsListModel {


    /**
     *
     * @param activity
     * @param cate_id 商品分类 1（实物商品）|2（活动）
     * @param count 当前页面已显示指定分类下数据个数
     */
    public void getGoodsLists(BaseActivity activity, final int cate_id, int count, final GoodsListsCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("cate_id",cate_id);
        params.put("count",count);
        RetrofitFactory.getInstance().createService()
                .goodsLists(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<List<GoodsListsResponseBean>>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<List<GoodsListsResponseBean>>>io())
                .subscribe(new BaseObserver<List<GoodsListsResponseBean>>(activity) {
                    @Override
                    protected void onSuccess(List<GoodsListsResponseBean> goodsListsResponseBean) throws Exception {
                        callBack.success(goodsListsResponseBean,cate_id);
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

    public interface GoodsListsCallBack{
        void success(List<GoodsListsResponseBean> goodsListsResponseBean,int cate_id);
        void failed(String msg);
    }
}
