package com.quanminjieshui.waterchain.http.config;

/**
 * Created by WanghongHe on 2018/12/3 12:23.
 */

public class UrlConfig {

    /**
     * 登录
     * {
     *      "code":1,
     *      "msg":"登录成功",
     *      "data":{
     *      "id":14,
     *      "is_blocked":0,
     *      "user_login":"18329257177",
     *      "avatar":"http:\/\/jsl.sshsky.com\/upload\/",
     *      "user_nickname":"",
     *      "token":"880486ea9371415e52b8ef0878b743fa7b4ea77fa1a8dd89ec9067c6e8a63129"
     * }
     */
    public final static String LOGIN = "/api/home/public/login";
    /**
     * 获取短信验证码
     * {
     *      "code":1,
     *     "msg":"操作成功",
     *     "data":"4444"
     *  }
     */
    public final static String SEND_VER_CODE = "api/home/common/sendVercode";
    /***
     * 注册
     *
     * {
     *     "code":1,
     *    "msg":"注册成功",
     *     "data":{
     *        "uid":"14",
     *        "user_login":"18329257177",
     *        "token":"d6bdbc551eb58413b80cd619cc9c4e65d19cedfb02ed04699cba16906f1c21a6"
     *    }
     * }
     */
    public final static String REGISTER = "api/home/public/register";
    //找回密码
    public static final String FIND_PASS = "api/home/public/findPass";
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

    /**
     * 用户信息
     *      {
     *     │     "code":1,
     *     │     "msg":"操作成功",
     *     │     "data":{
     *     │         "user_login":"18329257177",
     *     │         "create_time":"2018-12-13",
     *     │         "avatar":"http:\/\/www.quanminjieshui.com\/upload\/",
     *     │         "user_type":"企业",
     *     │         "is_blocked":0,
     *     │         "user_status":1
     *     │     }
     *     │ }
     */
    public static final String USER_DETAIL = "api/home/user/userDetail";
    //我的资产
    public static final String USER_ACCOUNT = "api/home/user/accountDetail";
    //用户身份证认证信息
    public static final String AUTH_DETAIL = "api/home/user/authDetail";
    //个人中心-全部委托
    public static final String TRADE_LIST = "api/home/user/tradeLists";
    //交易明细
    public static final String TRADE_DETAIL = "api/home/user/tradeDetail";
    //洗涤订单
    public static final String ORDER_LIST = "api/home/user/orderLists";
    //订单详情
    public static final String ORDER_DETAIL = "api/home/user/orderDetail";
    //修改密码
    public static final String CHANGE_PASS = "api/home/user/changePass";

}
