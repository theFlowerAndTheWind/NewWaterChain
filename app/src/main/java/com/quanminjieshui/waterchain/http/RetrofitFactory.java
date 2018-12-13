package com.quanminjieshui.waterchain.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.quanminjieshui.waterchain.http.config.HttpConfig;
import com.quanminjieshui.waterchain.http.utils.InterceptorUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WanghongHe on 2018/12/3 11:41.
 */

public class RetrofitFactory {
    private APIService apiService;

    private RetrofitFactory() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(new InterceptorUtil())//添加对Header的统一处理
                .build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build();
        //如果使用不同的service文件，需要注释下行代码
        apiService = mRetrofit.create(APIService.class);
    }

    public static RetrofitFactory getInstance() {
        return RetrofitHolder.retrofitFactory;
    }

    private static class RetrofitHolder {
        private final static RetrofitFactory retrofitFactory = new RetrofitFactory();
    }

    /**
     * 所有接口公用一个 service文件
     *
     * @return
     */
    public APIService createService() {
        return apiService;
    }
}
