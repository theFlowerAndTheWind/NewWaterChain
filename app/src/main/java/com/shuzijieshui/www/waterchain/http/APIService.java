package com.shuzijieshui.www.waterchain.http;

import com.shuzijieshui.www.waterchain.beans.AccountDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.AdImgResponseBean;
import com.shuzijieshui.www.waterchain.beans.AuthDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.BannerListResponseBean;
import com.shuzijieshui.www.waterchain.beans.CheckUserPayResponseBean;
import com.shuzijieshui.www.waterchain.beans.CreateOrderResponseBean;
import com.shuzijieshui.www.waterchain.beans.FactoryDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.FactoryListResponse;
import com.shuzijieshui.www.waterchain.beans.FactoryServiceResponseBean;
import com.shuzijieshui.www.waterchain.beans.GetUrlResponseBean;
import com.shuzijieshui.www.waterchain.beans.GoodsDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.GoodsListsResponseBean;
import com.shuzijieshui.www.waterchain.beans.GoodsResposeBean;
import com.shuzijieshui.www.waterchain.beans.InfoDetailRespoonseBean;
import com.shuzijieshui.www.waterchain.beans.InfoListsResponseBean;
import com.shuzijieshui.www.waterchain.beans.LoginResponseBean;
import com.shuzijieshui.www.waterchain.beans.OrderDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.OrderListsResponseBean;
import com.shuzijieshui.www.waterchain.beans.RegisterResponseBean;
import com.shuzijieshui.www.waterchain.beans.SellResponseBean;
import com.shuzijieshui.www.waterchain.beans.ServiceListResponseBean;
import com.shuzijieshui.www.waterchain.beans.TotalPriceResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeCenterResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeDetailResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeLineResponseBean;
import com.shuzijieshui.www.waterchain.beans.TradeListsResponseBean;
import com.shuzijieshui.www.waterchain.beans.UploadFileResponseBean;
import com.shuzijieshui.www.waterchain.beans.UserAddressResponseBean;
import com.shuzijieshui.www.waterchain.beans.UserDetailResponseBean;
import com.shuzijieshui.www.waterchain.http.bean.BaseEntity;
import com.shuzijieshui.www.waterchain.http.config.UrlConfig;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    Observable<BaseEntity> getSms(@Body RequestBody requestBody);

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
    Observable<BaseEntity<OrderDetailResponseBean>> orderDetail(@Body RequestBody requestBody);

    //洗地订单列表
    @POST(UrlConfig.ORDER_LIST)
    Observable<BaseEntity<OrderListsResponseBean>> orderList(@Body RequestBody requestBody);

    //成交明细
    @POST(UrlConfig.TRADE_DETAIL)
    Observable<BaseEntity<TradeDetailResponseBean>> tradeDetail(@Body RequestBody requestBody);

    //交易中心
    @POST(UrlConfig.TRADE_CENTER)
    Observable<BaseEntity<TradeCenterResponseBean>> tradeCenter(@Body RequestBody requestBody);

    //用户撤单
    @POST(UrlConfig.TRADE_CANCEL)
    Observable<BaseEntity>cancle(@Body RequestBody body);

    //贡献节水量
    @POST(UrlConfig.TRADE_BUY)
    Observable<BaseEntity> buy(@Body RequestBody requestBody);

    //获取节水量
    @POST(UrlConfig.TRADE_SELL)
    Observable<BaseEntity<SellResponseBean>> sell(@Body RequestBody requestBody);

    //折线图
    @POST(UrlConfig.TRADE_LINE)
    Observable<BaseEntity<TradeLineResponseBean>> tradeLine(@Body RequestBody body);

    //个人中心
    @POST(UrlConfig.TRADE_LIST)
    Observable<BaseEntity<TradeListsResponseBean>> tradeList(@Body RequestBody requestBody);

    //用户身份认证信息
    @POST(UrlConfig.AUTH_DETAIL)
    Observable<BaseEntity<AuthDetailResponseBean>> authDetail(@Body RequestBody requestBody);

    //我的资产
    @POST(UrlConfig.USER_ACCOUNT)
    Observable<BaseEntity<AccountDetailResponseBean>> accountDetail(@Body RequestBody requestBody);

    //用户信息
    @POST(UrlConfig.USER_DETAIL)
    Observable<BaseEntity<UserDetailResponseBean>> userDetail(@Body RequestBody requestBody);

    //平台资讯详情
    @POST(UrlConfig.INFO_DETAIL)
    Observable<BaseEntity<InfoDetailRespoonseBean>> infoDetail(@Body RequestBody requestBody);

    //平台资讯列表
    @POST(UrlConfig.INFO_LIST)
    Observable<BaseEntity<InfoListsResponseBean>> infoList(@Body RequestBody requestBody);

    //轮播列表
    @POST(UrlConfig.BANNER_LIST)
    Observable<BaseEntity<BannerListResponseBean>> bannerList(@Body RequestBody requestBody);

    //洗涤项目列表
    @POST(UrlConfig.SERVICE_LIST)
    Observable<BaseEntity<ServiceListResponseBean>> serviceList(@Body RequestBody requestBody);

    //洗涤商城企业列表
    @POST(UrlConfig.FACTORY_LIST)
    Observable<BaseEntity<FactoryListResponse>> factoryList(@Body RequestBody requestBody);

    //洗涤企业详情
    @POST(UrlConfig.FACTORY_DETAIL)
    Observable<BaseEntity<FactoryDetailResponseBean>> factoryDetail(@Body RequestBody requestBody);

    //洗涤企业项目详情
    @POST(UrlConfig.FACTORY_SERVICE)
    Observable<BaseEntity<FactoryServiceResponseBean>> factoryService(@Body RequestBody requestBody);

    //下单支付总金额
    @POST(UrlConfig.TOTAL_PRICE)
    Observable<BaseEntity<TotalPriceResponseBean>> totalPrice(@Body RequestBody requestBody);

    //创建订单
    @POST(UrlConfig.CREATE_ORDER)
    Observable<BaseEntity<CreateOrderResponseBean>> createOrder(@Body RequestBody requestBody);

    //我的兑换
    @POST(UrlConfig.GOODS)
    Observable<BaseEntity<List<GoodsResposeBean>>> goods(@Body RequestBody requestBody);

    @POST(UrlConfig.AD_IMG)
    Observable<BaseEntity<AdImgResponseBean>>getAdImg(@Body RequestBody body);



    @POST(UrlConfig.GET_URL)
    Observable<BaseEntity<GetUrlResponseBean>>getUrl(@Body RequestBody body);

    /**
     * "token", token;
     * "device_type", "android";
     * "file",File
     */
    @Multipart
    @POST(UrlConfig.UPLOAD)
    Observable<BaseEntity<UploadFileResponseBean>>uploadFile(@Part("token") RequestBody token,
                                                             @Part("device_type") RequestBody device_type,
                                                             @Part MultipartBody.Part file);

    /**
     * 商品列表--兑换商城
     */
    @POST(UrlConfig.GOODS_lISTS)
    Observable<BaseEntity<List<GoodsListsResponseBean>>> goodsLists(@Body RequestBody body);

    /**
     * 商品详情
     */
    @POST(UrlConfig.GOODS_DETAIL)
    Observable<BaseEntity<GoodsDetailResponseBean>> goodsDetail(@Body RequestBody body);

    /**
     * 申请转账
     */
    @POST(UrlConfig.MVJSL)
    Observable<BaseEntity> mvJsl(@Body RequestBody body);

    /**
     * 版本更新检查
     */
    @POST(UrlConfig.APP_VERSION)
    Observable<BaseEntity> appUpdate(@Body RequestBody requestBody);

    /**
     * 更改头像
     */
    @POST(UrlConfig.CHANGE_AVATAR)
    Observable<BaseEntity> changeAvatar(@Body RequestBody requestBody);

    /**
     * 检查用户是否可以支付
     */
    @POST(UrlConfig.CHECK_USER_PAY)
    Observable<BaseEntity<CheckUserPayResponseBean>> checkUserPay(@Body RequestBody requestBody);

    /**
     * 创建订单--兑换商城
     */
    @POST(UrlConfig.CREATE_ORDER_EXCHANGE)
    Observable<BaseEntity> createOrderExchange(@Body RequestBody requestBody);

    /**
     * 获取用户地址--兑换商城
     */
    @POST(UrlConfig.USER_ADDRESS)
    Observable<BaseEntity<UserAddressResponseBean>> getUserAddress(@Body RequestBody requestBody);
}

