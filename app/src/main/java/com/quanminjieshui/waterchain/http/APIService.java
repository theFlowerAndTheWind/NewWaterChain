package com.quanminjieshui.waterchain.http;

import com.quanminjieshui.waterchain.beans.OrderDetailResponseBean;
import com.quanminjieshui.waterchain.beans.RegisterResponseBean;
import com.quanminjieshui.waterchain.beans.SmsResponseBean;
import com.quanminjieshui.waterchain.beans.UserBean;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.UrlConfig;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by WanghongHe on 2018/12/3 11:41.
 * post和gest
 */

public interface APIService {

    //登录接口
    @POST(UrlConfig.LOGIN)
    @FormUrlEncoded
    Observable<BaseEntity<UserBean>> login(@Field("name") String name, @Field("password") String password);

    //忘记密码
//    @GET(UrlConfig.getMessageList)
//    Observable<BaseEntity<MessageBean>> getMessageList(@Query("currentPage") String currentPage, @Query("pageSize") String pageSize);  //消息列表

    @POST(UrlConfig.GET_SMS)
    @FormUrlEncoded
    Observable<BaseEntity<SmsResponseBean>> getSms(@Field("mobile") String mobile);

    @POST(UrlConfig.REGISTER)
    Observable<BaseEntity> register(@Body RequestBody requestBody);

    @POST(UrlConfig.RESET)
    @FormUrlEncoded
    Observable<BaseEntity<RegisterResponseBean>> reset(@FieldMap Map<String, Object> map);

    //修改密码
    @POST(UrlConfig.CHANGE_PASS)
    Observable<BaseEntity> changePass(@Body RequestBody requestBody);

    //订单详情
    @POST(UrlConfig.ORDER_DETAIL)
    Observable<BaseEntity> orderDetail(@Body RequestBody requestBody);

    //洗地订单列表
    @POST(UrlConfig.ORDER_LIST)
    Observable<BaseEntity> orderList(@Body RequestBody requestBody);

    //成交明细
    @POST(UrlConfig.TRADE_DETAIL)
    Observable<BaseEntity> tradeDetail(@Body RequestBody requestBody);

    //交易中心
    @POST(UrlConfig.TRADE_CENTER)
    Observable<BaseEntity> tradeCenter(@Body RequestBody requestBody);
}
