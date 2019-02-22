package com.shuzijieshui.www.waterchain.http;

import com.google.gson.Gson;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.utils.Util;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by WanghongHe on 2019/1/2 18:30.
 */

public class StringResponseBodyConverter implements Converter<ResponseBody, BaseEntity> {
    @Override
    public BaseEntity convert(ResponseBody value) throws IOException {
        try {
            BaseEntity baseEntity = new Gson().fromJson(value.string(),BaseEntity.class);
            if(Util.isEmpty(baseEntity.getData()) && baseEntity.getData().equals("")){
                baseEntity.setData(new Object());
            }
            return baseEntity;
        } finally {
            value.close();
        }
    }
}
