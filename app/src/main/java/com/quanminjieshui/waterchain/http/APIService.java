package com.quanminjieshui.waterchain.http;

import com.quanminjieshui.waterchain.beans.BannerListResponseBean;
import com.quanminjieshui.waterchain.beans.FactoryListResponseBean;
import com.quanminjieshui.waterchain.beans.LoginResponseBean;
import com.quanminjieshui.waterchain.beans.RegisterResponseBean;
import com.quanminjieshui.waterchain.beans.ServiceListResponseBean;
import com.quanminjieshui.waterchain.beans.SmsResponseBean;
import com.quanminjieshui.waterchain.http.bean.BaseEntity;
import com.quanminjieshui.waterchain.http.config.UrlConfig;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by WanghongHe on 2018/12/3 11:41.
 * post 对应@Body 和get 对应@Query
 */

public interface APIService {

    //登录接口
    @POST(UrlConfig.LOGIN)
    Observable<BaseEntity<LoginResponseBean>> login(@Body RequestBody requestBody);

//    @GET(UrlConfig.getMessageList)
//    Observable<BaseEntity<MessageBean>> getMessageList(@Query("currentPage") String currentPage, @Query("pageSize") String pageSize);  //消息列表

    //获取验证码
    @POST(UrlConfig.SEND_VER_CODE)
    Observable<BaseEntity<SmsResponseBean>> getSms(@Body RequestBody requestBody);

    //注册
    @POST(UrlConfig.REGISTER)
    Observable<BaseEntity<RegisterResponseBean>> register(@Body RequestBody requestBody);

    //忘记密码
    @POST(UrlConfig.FIND_PASS)
    Observable<BaseEntity> findPass(@Body RequestBody requestBody);

    //企业认证
    @POST(UrlConfig.COMPANY_AUTH)
    Observable<BaseEntity> companyAuth(@Body RequestBody body);

    //个人认证
    @POST(UrlConfig.PERSONAL_AUTH)
    Observable<BaseEntity> personalAuth(@Body RequestBody body);

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

    //个人中心
    @POST(UrlConfig.TRADE_LIST)
    Observable<BaseEntity> tradeList(@Body RequestBody requestBody);

    //用户身份认证信息
    @POST(UrlConfig.AUTH_DETAIL)
    Observable<BaseEntity> authDetail(@Body RequestBody requestBody);

    //我的资产
    @POST(UrlConfig.USER_ACCOUNT)
    Observable<BaseEntity> accountDetail(@Body RequestBody requestBody);

    //用户信息
    @POST(UrlConfig.USER_DETAIL)
    Observable<BaseEntity> userDetail(@Body RequestBody requestBody);

    //平台咨询详情
    @POST(UrlConfig.INFO_DETAIL)
    Observable<BaseEntity> infoDetail(@Body RequestBody requestBody);

    //平台咨询列表
    @POST(UrlConfig.INFO_LIST)
    Observable<BaseEntity> infoList(@Body RequestBody requestBody);

    //轮播列表
    @POST(UrlConfig.BANNER_LIST)
    Observable<BaseEntity<BannerListResponseBean>> bannerList(@Body RequestBody requestBody);

    //洗涤项目列表
    @POST(UrlConfig.SERVICE_LIST)
    Observable<BaseEntity<ServiceListResponseBean>> serviceList(@Body RequestBody requestBody);

    //洗涤商城企业列表
    @POST(UrlConfig.FACTORY_LIST)
    Observable<BaseEntity<List<FactoryListResponseBean>>> factoryList(@Body RequestBody requestBody);
}
