package com.quanminjieshui.waterchain.http.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by WanghongHe on 2018/12/3 11:19.
 * 请求
 */

public class RequestUtil {

    /**
     * 拼接公共参数
     */
    private static HashMap<String, String> getPublicParams(HashMap<String, String> map) {
        map.put("token", "token");
        map.put("device_type", "android");
        return map;
    }

    /**
     * 将参数转成json
     *
     * @param requestDataMap requestDataMap这里面放置上传数据的键值对。
     * @return
     */
    public static RequestBody getRequestBody(HashMap<String,String> requestDataMap) {
        String jsonPrarms = new Gson().toJson(getPublicParams(requestDataMap));
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonPrarms);
    }
}
