/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: CancleTradeModel
 * Author: sxt
 * Date: 2019/1/3 7:59 PM
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */

package com.shuzijieshui.www.waterchain.contract.model;

import com.shuzijieshui.www.waterchain.base.BaseActivity;
import com.shuzijieshui.www.waterchain.http.BaseObserver;
import com.shuzijieshui.www.waterchain.http.RetrofitFactory;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.HttpConfig;
import com.shuzijieshui.www.waterchain.http.utils.ObservableTransformerUtils;
import com.shuzijieshui.www.waterchain.http.utils.RequestUtil;
import com.shuzijieshui.www.waterchain.utils.LogUtils;

import java.util.HashMap;

/**
 * @ProjectName: NewWaterChain
 * @Package: com.quanminjieshui.www.waterchain.contract.model
 * @ClassName: CancleTradeModel
 * @Description: java类作用描述
 * @Author: sxt
 * @CreateDate: 2019/1/3 7:59 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/1/3 7:59 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CancleTradeModel {

    public void cancle(BaseActivity activity, int tid, final CancleTradeCallBack callBack) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("tid",tid);
        RetrofitFactory.getInstance().createService()
                .cancle(RequestUtil.getRequestHashBody(params, false))
                .compose(activity.<BaseEntity>bindToLifecycle())
                .compose(ObservableTransformerUtils.<BaseEntity>io())
                .subscribe(new BaseObserver(activity) {

                    @Override
                    protected void onSuccess(Object o) throws Exception {
                        callBack.onCancleSuccess();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        if (e != null && e.getMessage() != null) {
                            if (isNetWorkError) {
                                LogUtils.e(e.getMessage());
                                callBack.onCancleFailed(HttpConfig.ERROR_MSG);
                            } else {
                                callBack.onCancleFailed(e.getMessage());
                            }
                        } else {
                            callBack.onCancleFailed("");
                        }
                    }

                    @Override
                    protected void onCodeError(String code, String msg) throws Exception {
                        super.onCodeError(code, msg);
                        callBack.onCancleFailed(msg);
                    }
                });

//        Log.e("tag","********----------cancle!!!!!!!!!!!!!!!  "+tid);
//        LogUtils.d("tag","********----------cancle!!!!!!!!!!!!!!!  "+tid);
//
//        String url = "https://www.quanminjieshui.com/api/home/trade/cancel";
//        OkHttpClient okHttpClient = new OkHttpClient();
//        RequestBody requestBody = new FormBody.Builder()
//                .add("tid", String.valueOf(tid))
//                .build();
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .build();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("tag", "onFailure:********---------- " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.d("tag", response.protocol() + " ********----------" +response.code() + " " + response.message());
//
//                Log.d("tag", "onResponse: ********----------" + response.body().string());
//            }
//        });



    }

    public interface CancleTradeCallBack {
        void onCancleSuccess();

        void onCancleFailed(String msg);
    }
}