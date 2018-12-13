package com.quanminjieshui.waterchain.http.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Created by WanghongHe on 2018/12/3 11:19.
 * 请求 java.lang.IllegalStateException: Expected BEGIN_ARRAY but was BEGIN_OBJECT at line 1 column 34 path $.data
 * 是解析出错，肯定是返回的json数据与自己的bean不一致（仔细检查自己的bean）
 */

public class RequestUtil {

    /**
     * 拼接公共参数
     * @param obj 如果请求body有参数则进行拼接 否则 直接返回公参
     * @return
     */
    private static HashMap<String, Object> getPublicBeanParams(Object obj) {
        if(obj != null){
            HashMap<String,Object> map = HashMapBeanUtils.hashJavaBeanToMap(obj);
            map.put("token", "token");
            map.put("device_type", "android");
            return map;
        }else{
            HashMap<String, Object> onlyCommonMap = new HashMap<>();
            onlyCommonMap.put("token", "token");
            onlyCommonMap.put("device_type", "android");
            return onlyCommonMap;
        }

    }

    /**
     * 拼接公共参数
     * @param map 如果请求body有参数则进行拼接 否则 直接返回公参
     * @return 组装好的map
     */
    private static HashMap<String, Object> getPublicHashParams(HashMap<String, Object> map) {
        if(map != null){
            map.put("token", "token");
            map.put("device_type", "android");
            return map;
        }else{
            HashMap<String, Object> onlyCommonMap = new HashMap<>();
            onlyCommonMap.put("token", "token");
            onlyCommonMap.put("device_type", "android");
            return onlyCommonMap;
        }

    }

    /**
     * 将参数转成json(使用此方式有局限 只可手动拼接请求参数map)
     * 1、Gson().toJson 方式会将请求体中为value=null的key过滤掉
     * 2、new GsonBuilder().serializeNulls().create().toJson 方式不会过滤value=null的key
     * @param requestDataMap requestDataMap这里面放置上传数据的键值对,如果只有公参即传null
     * @param isFilter 是否需要过滤 (key value=null)
     * @return
     */
    public static RequestBody getRequestHashBody(HashMap<String,Object> requestDataMap,boolean isFilter) {
        String jsonPrarms = "";
        if(isFilter){
            jsonPrarms = new Gson().toJson(getPublicHashParams(requestDataMap));
        }else{
            jsonPrarms = new GsonBuilder().serializeNulls().create().toJson(getPublicHashParams(requestDataMap));
        }
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonPrarms);
    }


    /**
     * 将实体bean转换成json
     * @param requestObj 实体bean
     * @param isFilter 是否需要过滤 (key value=null)
     * @return
     */
    public static RequestBody getRequestBeanBody(Object requestObj,boolean isFilter){
        String jsonPrarms = "";
        if(isFilter){
            jsonPrarms = new Gson().toJson(getPublicBeanParams(requestObj));
        }else{
            jsonPrarms = new GsonBuilder().serializeNulls().create().toJson(getPublicBeanParams(requestObj));
        }
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonPrarms);
    }
}
