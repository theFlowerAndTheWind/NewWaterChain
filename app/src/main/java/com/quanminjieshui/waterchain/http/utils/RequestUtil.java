package com.quanminjieshui.waterchain.http.utils;

import com.google.gson.Gson;

import okhttp3.RequestBody;

/**
 * Created by WanghongHe on 2018/12/3 11:19.
 * 请求
 */

public class RequestUtil {
    /**
     * 将参数转成json
     *
     * @param requestDataMap requestDataMap这里面放置上传数据的键值对。
     * @return
     */
    public static RequestBody getRequestBody(Object requestDataMap) {
        Gson gson = new Gson();
        String obj = gson.toJson(requestDataMap);
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);

        return requestBody;
    }
}
