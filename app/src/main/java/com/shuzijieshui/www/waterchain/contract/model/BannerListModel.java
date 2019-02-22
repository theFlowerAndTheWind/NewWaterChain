package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.beans.BannerListResponseBean;
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
 * Created by WanghongHe on 2018/12/13 14:29.
 */

public class BannerListModel {

    /**
     *
     * @param activity
     * @param cate 终端类型 （2IOS/3ANDROID）
     * @param position 1首页 2资讯页
     * @param callBack
     */
    public void getBannerList(BaseActivity activity, int cate, int position, final BannerListCallBack callBack){
        HashMap<String,Object> params = new HashMap<>();
        params.put("cate",cate);
        params.put("position",position);
        RetrofitFactory.getInstance().createService()
                .bannerList(RequestUtil.getRequestHashBody(params,false))
                .compose(activity.<BaseEntity<BannerListResponseBean>>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity<BannerListResponseBean>>io())
                .subscribe(new BaseObserver<BannerListResponseBean>() {

                    /**
                     * 返回成功
                     *
                     * @throws Exception
                     */
                    @Override
                    protected void onSuccess(BannerListResponseBean bannerListResponseBean) throws Exception {
                        callBack.success(bannerListResponseBean.getLists());
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

    public interface BannerListCallBack{
        void success(List<BannerListResponseBean.BannerListEntity> list);
        void failed(String msg);
    }
}
