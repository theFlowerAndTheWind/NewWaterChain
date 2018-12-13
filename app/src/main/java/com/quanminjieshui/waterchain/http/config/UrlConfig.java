package com.quanminjieshui.waterchain.http.config;

/**
 * Created by WanghongHe on 2018/12/3 12:23.
 */

public class UrlConfig {

    //登录接口
    public final static String LOGIN = "/api/home/public/login";
    //获取短信验证码接口
    public final static String GET_SMS = "";
    //注册接口
    public final static String REGISTER = "api/home/public/register";
    //找回/重置密码借口
    public static final String RESET = "api/home/public/findPass";
    //身份认证-企业
    public static final String COMPANY_AUTH = "api/home/public/companyAuth";
    //身份认证-个人
    public static final String PERSONAL_AUTH = "api/home/public/PersonalAuth";

    //轮播图列表地址
    public static final String BANNER_LIST = "api/home/banner/bannerLists";
    //洗涤企业列表
    public static final String FACTORY_LIST = "api/home/factory/factoryLists";
    //洗涤项目列表
    public static final String SERVICE_LIST = "api/home/common/serviceLists";
    //洗涤企业详情
    public static final String FACTORY_DETAIL = "api/home/factory/factoryDetail";
    //洗涤企业项目详情
    public static final String FACTORY_SERVICE = "api/home/factory/factoryService";
    //支付总金额
    public static final String TOTAL_PRICE = "api/home/factory/totalPrice";
    //创建订单
    public static final String CREATE_ORDER = "api/home/factory/addOrder";


    //交易中心
    public static final String TRADE_CENTER = "api/home/trade/tradeCenter";
    //贡献节水指标
    public static final String CHCEK_WATER = "api/home/trade/buy";
    //获取节水指标
    public static final String SELL_WATER = "api/home/trade/sell";
    //用户撤单
    public static final String TRADE_CANCEL = "api/home/trade/cancel";


    //平台资讯列表
    public static final String INFO_LIST = "api/home/info/infoLists";
    //平台咨询详情
    public static final String INFO_DETAIL = "api/home/info/infoDetail";


    //用户信息
    public static final String USER_DETAIL = "api/home/user/userDetail";
    //我的资产
    public static final String USER_ACCOUNT = "api/home/user/accountDetail";
    //用户身份证认证信息
    public static final String AUTH_DETAIL = "api/home/user/authDetail";
    //个人中心-全部委托
    public static final String PERSONAL_CENTER = "api/home/user/tradeLists";
    //交易明细
    public static final String TRADE_DETAIL = "api/home/user/tradeDetail";
    //洗涤订单
    public static  final String ORDER_LIST = "api/home/user/orderLists";
    //订单详情
    public static final String ORDER_DETAIL = "api/home/user/orderDetail";
    //修改密码
    public static final     String CHANGE_PASS = "api/home/user/changePass";

}
