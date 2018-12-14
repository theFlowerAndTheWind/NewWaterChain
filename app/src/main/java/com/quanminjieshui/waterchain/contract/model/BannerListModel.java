package com.quanminjieshui.waterchain.contract.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.quanminjieshui.waterchain.base.BaseActivity;
import com.quanminjieshui.waterchain.beans.BannerListResponseBean;
import com.quanminjieshui.waterchain.http.BaseObserver;
import com.quanminjieshui.waterchain.http.RetrofitFactory;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.ObservableTransformerUtils;
import com.quanminjieshui.waterchain.http.utils.RequestUtil;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.util.ArrayList;
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
                        List<Object> list = new ArrayList<>();
                        try {
                            Gson gson = new Gson();
                            JsonArray arry = new JsonParser().parse(bannerListResponseBean.getLists().toString()).getAsJsonArray();
                            for (JsonElement jsonElement : arry) {
                                list.add(gson.fromJson(jsonElement,new TypeToken<BannerListResponseBean.BannerListEntity>() {}.getType()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        callBack.success(list);
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
        void success(List<Object> list);
        void failed(String msg);
    }
}
