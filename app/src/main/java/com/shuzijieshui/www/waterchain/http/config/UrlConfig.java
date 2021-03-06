package com.shuzijieshui.www.waterchain.http.config;

/**
 * Created by WanghongHe on 2018/12/3 12:23.
 */

public class UrlConfig {
    //登录
    /**
     * {
     * "code":1,
     * "msg":"登录成功",
     * "data":{
     * "id":14,
     * "is_blocked":0,
     * "user_login":"18329257177",
     * "avatar":"http:\/\/jsl.sshsky.com\/upload\/",
     * "user_nickname":"",
     * "token":"880486ea9371415e52b8ef0878b743fa7b4ea77fa1a8dd89ec9067c6e8a63129"
     * }
     */
    public final static String LOGIN = "/api/home/public/login";
    //登录
    /**
     * 获取短信验证码
     * {
     * "code":1,
     * "msg":"发送成功",
     * "data":""
     * }
     */
    public final static String SEND_VER_CODE = "api/home/common/sendVercode";
    //登录
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
    /**
     * 该接口暂时不用
     *
     * 洗涤企业列表
     * <p>
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功！",
     * │     "data":[
     * │         {
     * │             "id":1,
     * │             "f_name":"洗涤工厂",
     * │             "logo":"http:\/\/www.quanminjieshui.com\/upload\/fac\/20181202\/e3e8abc9439e5d42286449533770f8ed.jpg",
     * │             "service_area":"山东临沂市区内",
     * │             "service_lists":"酒店餐厅布草、餐具消毒类等"
     * │         }
     * │     ]
     * │ }
     */
    public static final String FACTORY_LIST = "api/home/factory/factoryLists";
    /**
     * 洗涤项目列表
     *     │ {
     *     │     "code":1,
     *     │     "msg":"操作成功",
     *     │     "data":{
     *     │         "lists":[
     *     │             {
     *     │                 "id":10,
     *     │                 "s_name":"污水处理合同订单",
     *     │                 "intro":"为化工厂、PX、农业、环保、城市景观、医疗、建筑等领域的污水，定制污水解决方案",
     *     │                 "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190328\/5ec43794194932ae1df39fc3578639c0.jpg",
     *     │                 "show":1
     *     │             },
     *     │             {
     *     │                 "id":11,
     *     │                 "s_name":"脱盐水合同订单",
     *     │                 "intro":"为热电厂、工厂等领域，定制脱盐水，在生产环节中，最低程度的减少水中溶解的盐结垢",
     *     │                 "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190328\/e2227f5883f31e1149c8059eaf715d3b.jpg",
     *     │                 "show":1
     *     │             },
     *     │             {
     *     │                 "id":13,
     *     │                 "s_name":"工业洗涤园",
     *     │                 "intro":"工业循环用水洗涤技术”是针对布草和餐具的电化学洗涤技术，可节水95%、减少98%污水排放",
     *     │                 "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190401\/13034ed0c1c8b075d20c63649945ec50.jpg",
     *     │                 "show":0
     *     │             },
     *     │             {
     *     │                 "id":14,
     *     │                 "s_name":"节水环保产业园",
     *     │                 "intro":"政府或企业实现产业发展目标而创立的特殊区位平台，围绕节水及环保方向的产品和服务",
     *     │                 "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190401\/673b92e0dc79df19289cbb0d4a4b828c.jpg",
     *     │                 "show":0
     *     │             }
     *     │         ]
     *     │     }
     *     │ }
     */
    public static final String SERVICE_LIST = "api/home/common/serviceLists";
    /**
     * 洗涤企业详情
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "detail":{
     * │             "id":1,
     * │             "description":"<p>临沂东海胜洁清洁服务有限公司现有员六十余人，管理人员均从业多年并持有国家认可的中级以上洗涤技师资格证书，所有员工均经过正规的岗前培训方可上岗。 东海胜洁清洁服务有限公司是经国家相关部门批准注册的企业。专业从事床单、被罩、浴巾、毛巾、桌布、客车座套及商务办公、家庭住宅的窗帘、床罩、毛毯、工作服等布草的洗涤(干洗、水洗）、烘干、消毒、熨烫的清洁 洗涤一条龙服务公司 。公司成立几年来，为多家中小型宾馆以及各大酒店、公司等提服务。<\/p>",
     * │             "img":"https:\/\/www.quanminjieshui.com\/upload\/fac\/20181202\/e3e8abc9439e5d42286449533770f8ed.jpg"
     * │         },
     * │         "service_lists":[
     * │             {
     * │                 "s_name":"酒店餐厅布草",
     * │                 "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
     * │                 "service_id":1,
     * │                 "description":"节水 95%，专业洗涤",
     * │                 "id":7
     * │             }
     * │         ]
     * │     }
     * │ }
     */
    public static final String FACTORY_DETAIL = "api/home/factory/factoryDetail";
    /**
     * 洗涤企业项目详情
     *     │ {
     *     │     "code":1,
     *     │     "msg":"操作成功",
     *     │     "data":{
     *     │         "id":10,
     *     │         "service_id":3,
     *     │         "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190328\/5ec43794194932ae1df39fc3578639c0.jpg",
     *     │         "s_name":"污水处理合同订单",
     *     │         "description":"&lt;p&gt;&lt;span style=&quot;font-size: 16px; line-height:1.5&quot;&gt;利用物理、化学和生物的方法对废水进行处理，使污水及废水净化，减少污染，以至达到回收、复用，充分利用水资源，使污水达到排入某一水体或再次使用的水质要求对其进行净化的过程。&lt;\/span&gt;&lt;\/p&gt;&lt;p&gt;&lt;span style=&quot;font-size: 16px; line-height:1.5&quot;&gt;污水处理被广泛应用于建筑、农业、交通、能源、石化、环保、城市景观、医疗、餐饮等各个领域，我方根据客户的需求，会提供多元化组合式处理方法。&lt;\/span&gt;&lt;\/p&gt;&lt;p&gt;&lt;br\/&gt;&lt;\/p&gt;",
     *     │         "factory_id":1,
     *     │         "intro":"为化工厂、PX、农业、环保、城市景观、医疗、建筑等领域的污水，定制污水解决方案",
     *     │         "content":"<p><span style=\"font-size: 16px; line-height:1.5\">利用物理、化学和生物的方法对废水进行处理，使污水及废水净化，减少污染，以至达到回收、复用，充分利用水资源，使污水达到排入某一水体或再次使用的水质要求对其进行净化的过程。<\/span><\/p><p><span style=\"font-size: 16px; line-height:1.5\">污水处理被广泛应用于建筑、农业、交通、能源、石化、环保、城市景观、医疗、餐饮等各个领域，我方根据客户的需求，会提供多元化组合式处理方法。<\/span><\/p><p><br\/><\/p>"
     *     │     }
     *     │ }
     */

    public static final String FACTORY_SERVICE = "api/home/factory/factoryService";
    /**
     * 支付总金额
     *     │ {
     *     │     "code":1,
     *     │     "msg":"操作成功",
     *     │     "data":{
     *     │         "total_price":"11.00",
     *     │         "user_jsl":"1243",
     *     │         "max_use_jsl":"6",
     *     │         "max_money":"0.55",
     *     │         "scale":"0.09000",
     *     │         "true_max_use_jsl":"6"
     *     │     }
     *     │ }
     */

    public static final String TOTAL_PRICE = "api/home/factory/totalPrice";

    /**
     * 创建订单
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "oid":"162",
     * │         "orderStr":"app_id=2016092200573088&method=alipay.trade.app.pay&format=JSON&charset=UTF-8&sign_type=RSA2&version=1.0&return_url=https%3A%2F%2Fwww.quanminjieshui.com%2Fportal%2Fpay%2Ftbapi&notify_url=https%3A%2F%2Fwww.quanminjieshui.com%2Fportal%2Fpay%2Fnotify&timestamp=2019-01-30+21%3A09%3A10&sign=A%2BeVAtZwia4CUAPOArtjOvv8zbJVo%2BmPBMmlAz01Qj4GGwbo8%2FRDdGBreivVnEnV59YZkzLTtnnVTkRbhaXYeus1KIer4kuMN63orIH27cfc0kjXZT4ltADMQpXGZ5IM%2BD1f1NsJbtJdq7kjzT0RYEAPKE5kW9gFRnVjUVXb36nuDcvcr2XcpgJkIKGM3xVMXbbtM%2B%2BpG3AVGjNgqJVsrhN20JL%2B8PRiEXkxg7ZM6AzE9r6URwugg9%2FkQ8L4FV%2BFMrItyFlzx%2BGqt2sC0v0oNnwcgNnh0HUT3oNZOoMQH316veZmdqrJW1ieUKFwg7uPCLVzCbU%2FLOecJCcrMnqZgw%3D%3D&biz_content=%7B%22out_trade_no%22%3A%222019013054101495%22%2C%22total_amount%22%3A%222.00%22%2C%22subject%22%3A%22%5Cu6d17%5Cu6da4%5Cu8ba2%5Cu53552019013054101495%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D"
     * │     }
     * │ }
     *
     *
     * │ {
     *     │     "code":1,
     *     │     "msg":"操作成功",
     *     │     "data":{
     *     │         "oid":"280",
     *     │         "orderStr":{
     *     │             "appid":"**************",
     *     │             "partnerid":"***********",
     *     │             "prepayid":"wx08144157820573093b122b3f3067955017",
     *     │             "timestamp":"1552027317",
     *     │             "noncestr":"GH6zc87GKDIqT0tF",
     *     │             "package":"Sign=WXPay",
     *     │             "sign":"D4AAAE56CAF0F3D5E3046BC241A3BC21"
     *     │         }
     *     │     }
     *     │ }
     *
     */

    public static final String CREATE_ORDER = "api/home/factory/addOrder";
    /**
     * 获取支付结果
     * │ {
     *     │     "code":1,
     *     │     "msg":"支付成功",
     *     │     "data":""
     *     │ }
     */
    public static final String GET_PAY_RES="api/home/factory/getPayRes";
    //洗涤企业列表
    /**
     * 交易中心
     * 无token
     * │ {
     * │     "code":1,
     * │     "msg":"",
     * │     "data":{
     * │         "cur_price":"0.00342",
     * │         "price_limit":"--",
     * │         "price_limit_color":"",
     * │         "day_vol":"6829.73375",
     * │         "trade_status":"休息",
     * │         "trade_list":{
     * │             "buy":[
     * │                 {
     * │                     "total":"641.44527",
     * │                     "price":"0.00342",
     * │                     "name":"贡献1"
     * │                 },
     * │                 {
     * │                     "total":"26.70361",
     * │                     "price":"0.00340",
     * │                     "name":"贡献2"
     * │                 },
     * │                 {
     * │                     "total":"136.37764",
     * │                     "price":"0.00339",
     * │                     "name":"贡献3"
     * │                 },
     * │                 {
     * │                     "total":"1169.39638",
     * │                     "price":"0.00338",
     * │                     "name":"贡献4"
     * │                 },
     * │                 {
     * │                     "total":"2.72868",
     * │                     "price":"0.00337",
     * │                     "name":"贡献5"
     * │                 }
     * │             ],
     * │             "sell":[
     * │                 {
     * │                     "total":"141.20675",
     * │                     "price":"0.00316",
     * │                     "name":"获取1"
     * │                 },
     * │                 {
     * │                     "total":"618.71021",
     * │                     "price":"0.00321",
     * │                     "name":"获取2"
     * │                 },
     * │                 {
     * │                     "total":"944.27701",
     * │                     "price":"0.00322",
     * │                     "name":"获取3"
     * │                 },
     * │                 {
     * │                     "total":"311.60029",
     * │                     "price":"0.00324",
     * │                     "name":"获取4"
     * │                 },
     * │                 {
     * │                     "total":"357.13491",
     * │                     "price":"0.00335",
     * │                     "name":"获取5"
     * │                 }
     * │             ]
     * │         },
     * │         "trade_detail_list":[
     * │             {
     * │                 "id":3447,
     * │                 "add_time":"21:48:01",
     * │                 "action_type":1,
     * │                 "price":"0.00292",
     * │                 "total":"16.32278",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3449,
     * │                 "add_time":"21:49:01",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"3.30007",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3453,
     * │                 "add_time":"21:52:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"0.14069",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3455,
     * │                 "add_time":"21:52:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"9.30398",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3457,
     * │                 "add_time":"21:53:01",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"0.83569",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3459,
     * │                 "add_time":"21:54:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"102.59792",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3461,
     * │                 "add_time":"21:54:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"1.30924",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3463,
     * │                 "add_time":"21:54:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"30.08287",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3472,
     * │                 "add_time":"21:58:01",
     * │                 "action_type":1,
     * │                 "price":"0.00329",
     * │                 "total":"43.31933",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3474,
     * │                 "add_time":"21:58:01",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"4.88370",
     * │                 "type_name":"获取"
     * │             }
     * │         ],
     * │         "user_cur_trade":[
     * │
     * │         ],
     * │         "user_history_trade":[
     * │
     * │         ],
     * │         "user_account":{
     * │             "jsl":"0.00000",
     * │             "ds":"0.00000"
     * │         },
     * │         "line_chart":"0.00342",
     * │         "is_login":0
     * │     }
     * │ }
     * **********************************************************************************************
     * 带token
     * │ {
     * │     "code":1,
     * │     "msg":"",
     * │     "data":{
     * │         "cur_price":"0.00342",
     * │         "price_limit":"--",
     * │         "price_limit_color":"",
     * │         "day_vol":"6599.96565",
     * │         "trade_status":"休息",
     * │         "trade_list":{
     * │             "buy":[
     * │                 {
     * │                     "total":"641.44527",
     * │                     "price":"0.00342",
     * │                     "name":"贡献1"
     * │                 },
     * │                 {
     * │                     "total":"26.70361",
     * │                     "price":"0.00340",
     * │                     "name":"贡献2"
     * │                 },
     * │                 {
     * │                     "total":"136.37764",
     * │                     "price":"0.00339",
     * │                     "name":"贡献3"
     * │                 },
     * │                 {
     * │                     "total":"1169.39638",
     * │                     "price":"0.00338",
     * │                     "name":"贡献4"
     * │                 },
     * │                 {
     * │                     "total":"2.72868",
     * │                     "price":"0.00337",
     * │                     "name":"贡献5"
     * │                 }
     * │             ],
     * │             "sell":[
     * │                 {
     * │                     "total":"141.20675",
     * │                     "price":"0.00316",
     * │                     "name":"获取1"
     * │                 },
     * │                 {
     * │                     "total":"618.71021",
     * │                     "price":"0.00321",
     * │                     "name":"获取2"
     * │                 },
     * │                 {
     * │                     "total":"944.27701",
     * │                     "price":"0.00322",
     * │                     "name":"获取3"
     * │                 },
     * │                 {
     * │                     "total":"311.60029",
     * │                     "price":"0.00324",
     * │                     "name":"获取4"
     * │                 },
     * │                 {
     * │                     "total":"357.13491",
     * │                     "price":"0.00335",
     * │                     "name":"获取5"
     * │                 }
     * │             ]
     * │         },
     * │         "trade_detail_list":[
     * │             {
     * │                 "id":3447,
     * │                 "add_time":"21:48:01",
     * │                 "action_type":1,
     * │                 "price":"0.00292",
     * │                 "total":"16.32278",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3449,
     * │                 "add_time":"21:49:01",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"3.30007",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3453,
     * │                 "add_time":"21:52:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"0.14069",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3455,
     * │                 "add_time":"21:52:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"9.30398",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3457,
     * │                 "add_time":"21:53:01",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"0.83569",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3459,
     * │                 "add_time":"21:54:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"102.59792",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3461,
     * │                 "add_time":"21:54:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"1.30924",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3463,
     * │                 "add_time":"21:54:02",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"30.08287",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3472,
     * │                 "add_time":"21:58:01",
     * │                 "action_type":1,
     * │                 "price":"0.00329",
     * │                 "total":"43.31933",
     * │                 "type_name":"获取"
     * │             },
     * │             {
     * │                 "id":3474,
     * │                 "add_time":"21:58:01",
     * │                 "action_type":1,
     * │                 "price":"0.00342",
     * │                 "total":"4.88370",
     * │                 "type_name":"获取"
     * │             }
     * │         ],
     * │         "user_cur_trade":[
     * │
     * │         ],
     * │         "user_history_trade":[
     * │
     * │         ],
     * │         "user_account":{
     * │             "jsl":"1222.00000",
     * │             "ds":"1532.00000"
     * │         },
     * │         "line_chart":"0.00342",
     * │         "is_login":1
     * │     }
     * │ }
     */
    public static final String TRADE_CENTER = "api/home/trade/tradeCenter";
    //贡献节水指标
    public static final String TRADE_BUY = "api/home/trade/buy";
    //获取节水指标
    public static final String TRADE_SELL = "api/home/trade/sell";
    //用户撤单
    public static final String TRADE_CANCEL = "api/home/trade/cancel";
    //折线图
    /**
     * │ {
     * │     "code":1,
     * │     "msg":"",
     * │     "data":{
     * │         "data":[
     * │             {
     * │                 "price":"0.00411",
     * │                 "tdate":"2019-01-04 10:01:00"
     * │             },
     * │             {
     * │                 "price":"0.00411",
     * │                 "tdate":"2019-01-04 10:02:00"
     * │             },
     * │             {
     * │                 "price":"0.00407",
     * │                 "tdate":"2019-01-04 10:03:00"
     * │             },
     * │             {
     * │                 "price":"0.00407",
     * │                 "tdate":"2019-01-04 10:04:00"
     * │             },
     * │             {
     * │                 "price":"0.00430",
     * │                 "tdate":"2019-01-04 10:05:00"
     * │             },
     * │             {
     * │                 "price":"0.00430",
     * │                 "tdate":"2019-01-04 10:05:00"
     * │             },
     * │             {
     * │                 "price":"0.00429",
     * │                 "tdate":"2019-01-04 10:06:00"
     * │             },
     * │             {
     * │                 "price":"0.00429",
     * │                 "tdate":"2019-01-04 10:06:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:07:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:08:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:09:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:09:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:10:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:11:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:12:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:13:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:14:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:15:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:15:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:16:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:16:00"
     * │             },
     * │             {
     * │                 "price":"0.
     * └────────────────────────────────────────────────────────────────────────────────────────────────────────────────
     * 01-04 10:25:30.852 21339-21546/com.quanminjieshui.www.waterchain D/WaterChain:
     * ┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────
     * │ 00441",
     * │                 "tdate":"2019-01-04 10:17:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:17:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:18:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:19:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:19:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:20:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:21:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:22:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:23:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:23:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:24:00"
     * │             },
     * │             {
     * │                 "price":"0.00441",
     * │                 "tdate":"2019-01-04 10:24:00"
     * │             },
     * │             {
     * │                 "price":"0.00396",
     * │                 "tdate":"2019-01-04 10:25:00"
     * │             }
     * │         ],
     * │         "xasix":[
     * │             "2019-01-04 10:00:00",
     * │             "2019-01-04 11:00:00",
     * │             "2019-01-04 12:00:00",
     * │             "2019-01-04 13:00:00",
     * │             "2019-01-04 14:00:00",
     * │             "2019-01-04 15:00:00",
     * │             "2019-01-04 16:00:00",
     * │             "2019-01-04 17:00:00",
     * │             "2019-01-04 18:00:00",
     * │             "2019-01-04 19:00:00",
     * │             "2019-01-04 20:00:00",
     * │             "2019-01-04 21:00:00",
     * │             "2019-01-04 22:00:00"
     * │         ]
     * │     }
     */
    public static final String TRADE_LINE = "api/home/trade/tradeLine";
    //平台资讯列表
    /**
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "lists":[
     * │             {
     * │                 "id":4,
     * │                 "title":"12月24日节水资讯",
     * │                 "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181214\/61865c5afa8cb1be3faf5d81647e2ca5.jpg",
     * │                 "publishtime":"2018-12-27",
     * │                 "content":"12月14日节水资讯"
     * │             },
     * │             {
     * │                 "id":5,
     * │                 "title":"12月25日节水资讯",
     * │                 "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181214\/4db2ce83bfac13ae1fdfc1476a23a305.jpg",
     * │                 "publishtime":"2018-12-27",
     * │                 "content":"12月24日节水资讯"
     * │             },
     * │             {
     * │                 "id":6,
     * │                 "title":"12月26日节水资讯",
     * │                 "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20181227\/13771579cbe4a5b1fe32284e2c5769c1.jpg",
     * │                 "publishtime":"2018-12-27",
     * │                 "content":"12月24日节水资讯"
     * │             }
     * │         ]
     * │     }
     * │ }
     */
    public static final String INFO_LIST = "api/home/info/infoLists";
    //平台资讯详情
    public static final String INFO_DETAIL = "api/home/info/infoDetail";
    //用户信息
    /**
     * 用户信息
     * {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "user_login":"18329257177",
     * │         "create_time":"2018-12-13",
     * │         "avatar":"http:\/\/www.quanminjieshui.com\/upload\/",
     * │         "user_type":"企业",
     * │         "is_blocked":0,
     * │         "user_status":1
     * │     }
     * │ }
     */
    public static final String USER_DETAIL = "api/home/user/userDetail";
    //用户信息
    /**
     * 我的资产
     * <p>
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "jsl":"0.16908",
     * │         "jsl_freeze":"0.00000",
     * │         "ds":"0.42439",
     * │         "ds_freeze":"0.00000",
     * │         "jsl_lock_view":"0.00000",
     * │         "jsl_gyj":"0.00000"
     * │     }
     * │ }
     */
    public static final String USER_ACCOUNT = "api/home/user/accountDetail";
    //用户身份证认证信息
    /**
     * 用户身份证认证信息
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "user_status":"待审核",
     * │         "user_name":"",
     * │         "id_no":"",
     * │         "company_name":"公司名称",
     * │         "company_license_no":"123456789",
     * │         "user_type":2
     * │     }
     * │ }
     */
    public static final String AUTH_DETAIL = "api/home/user/authDetail";
    //个人中心-全部委托
    /**
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "lists":[
     * │             {
     * │                 "id":7034,
     * │                 "add_time":"2019-01-03 19:08",
     * │                 "price":"0.00000",
     * │                 "old_total":"6.43998",
     * │                 "total":"6.43998",
     * │                 "status":"等待成交",
     * │                 "action_type":"贡献",
     * │                 "deal_total":"0.00000",
     * │                 "more":0,
     * │                 "show_cancel":1,
     * │                 "fee":"0.00000JSL",
     * │                 "price_avg":"0.00000"
     * │             },
     * │             {
     * │                 "id":7027,
     * │                 "add_time":"2019-01-03 19:04",
     * │                 "price":"0.00000",
     * │                 "old_total":"122.35950",
     * │                 "total":"122.35950",
     * │                 "status":"等待成交",
     * │                 "action_type":"贡献",
     * │                 "deal_total":"0.00000",
     * │                 "more":0,
     * │                 "show_cancel":1,
     * │                 "fee":"0.00000JSL",
     * │                 "price_avg":"0.00000"
     * │             },
     * │             {
     * │                 "id":7024,
     * │                 "add_time":"2019-01-03 19:03",
     * │                 "price":"0.00349",
     * │                 "old_total":"51.51979",
     * │                 "total":"51.51979",
     * │                 "status":"等待成交",
     * │                 "action_type":"贡献",
     * │                 "deal_total":"0.00000",
     * │                 "more":0,
     * │                 "show_cancel":1,
     * │                 "fee":"0.00000JSL",
     * │                 "price_avg":"0.00000"
     * │             },
     * │             {
     * │                 "id":7018,
     * │                 "add_time":"2019-01-03 19:00",
     * │                 "price":"0.00000",
     * │                 "old_total":"131.90307",
     * │                 "total":"0.00000",
     * │                 "status":"全部成交",
     * │                 "action_type":"获取",
     * │                 "deal_total":"131.90307",
     * │                 "more":1,
     * │                 "show_cancel":0,
     * │                 "fee":"0.00255T",
     * │                 "price_avg":"0.00394"
     * │             },
     * │             {
     * │                 "id":7017,
     * │                 "add_time":"2019-01-03 18:59",
     * │                 "price":"0.00000",
     * │                 "old_total":"15.83471",
     * │                 "total":"0.00000",
     * │                 "status":"全部成交",
     * │                 "action_type":"获取",
     * │                 "deal_total":"15.83471",
     * │                 "more":1,
     * │                 "show_cancel":0,
     * │                 "fee":"0.00029T",
     * │                 "price_avg":"0.00406"
     * │             }
     * │         ]
     * │     }
     * │ }
     */
    public static final String TRADE_LIST = "api/home/user/tradeLists";
    //交易明细
    /**
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "old_total":"147.64575",
     * │         "action_type":"贡献",
     * │         "uid":6,
     * │         "avg_price":"0.00346",
     * │         "deal_total":"0.51085",
     * │         "fee":"0.73822T",
     * │         "trade_detail":[
     * │             {
     * │                 "add_time":"2019-01-07 21:56:01",
     * │                 "price":"0.00346",
     * │                 "total":"147.64575",
     * │                 "fee":"0.73822T"
     * │             }
     * │         ]
     * │     }
     * │ }
     */
    public static final String TRADE_DETAIL = "api/home/user/tradeDetail";
    //洗涤订单
    /**
     * 洗涤订单
     * <p>
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "lists":[
     * │             {
     * │                 "fid":1,
     * │                 "f_name":"洗涤工厂",
     * │                 "s_name":"餐具消毒类",
     * │                 "img":"http:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
     * │                 "total_price":"2.00000",
     * │                 "status":"已取消",
     * │                 "id":48
     * │             }
     * │         ]
     * │     }
     * │ }
     */
    public static final String ORDER_LIST = "api/home/user/orderLists";
    //洗涤订单
    /**
     * 订单详情   需要传订单id  文档中未提示
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "f_name":"洗涤工厂",
     * │         "fid":1,
     * │         "s_name":"餐具消毒类",
     * │         "img":"http:\/\/www.quanminjieshui.com\/upload\/admin\/20181211\/4aadf0b4a721ffa8bc2dbc298bfa3c27.jpg",
     * │         "total_price":"2.00000",
     * │         "service_cate":[
     * │             {
     * │                 "c_name":"方巾",
     * │                 "total":"1",
     * │                 "price":"2.00"
     * │             }
     * │         ],
     * │         "order_sn":"2018122757979754",
     * │         "status":"已取消",
     * │         "createtime":"2018-12-27 21:47:37",
     * │         "pay_cate":"全额支付",
     * │         "pay_type":"支付宝支付",
     * │         "pay_price":"2.00000",
     * │         "pay_jsl":"0.00000",
     * │         "contact_name":"傅基本",
     * │         "contact_tel":"15110067060",
     * │         "express":"洗涤企业配送",
     * │         "province":"湖北",
     * │         "city":"武汉",
     * │         "address":"湖北武汉雨花石",
     * │         "pickup_time":"2018-12-12",
     * │         "pay_sn":"",
     * │         "id":48
     * │     }
     * │ }
     *
     *
     *     │ {
     *     │     "code":1,
     *     │     "msg":"操作成功",
     *     │     "data":{
     *     │         "order_sn":"2019040710250559",
     *     │         "f_name":"工业净水分离技术有限公司",
     *     │         "status":1,
     *     │         "s_name":"污水处理合同订单",
     *     │         "count":"0",
     *     │         "total_price":"0.00",
     *     │         "pay_price":"0.00",
     *     │         "pay_jsl":"0",
     *     │         "pay_cate":"全额支付",
     *     │         "add_time":"2019-04-07 23:09:19",
     *     │         "back_jsl":"0",
     *     │         "back_time":"-",
     *     │         "fid":1,
     *     │         "img":"https:\/\/www.shuzijieshui.com\/upload\/fac\/20190328\/5ec43794194932ae1df39fc3578639c0.jpg",
     *     │         "status_view":"待付款"
     *     │     }
     *     │ }
     */
    public static final String ORDER_DETAIL = "api/home/user/orderDetail";
    //修改密码
    public static final String CHANGE_PASS = "api/home/user/changePass";
    //洗涤订单
    /**
     * 我的-兑换记录
     * 实际请求：
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":[
     * │
     * │     ]
     * │ }
     * <p>
     * <p>
     * 文档示例:
     * {
     * "code": "",
     * "data": [
     * {
     * "order_sn": "8056040072755409",
     * "g_name": "",
     * "c_name": "",
     * "total_price": "",
     * "createtime": "",
     * "status_view": ""
     * }
     * ],
     * "msg": ""
     * }
     */
    public static final String GOODS = "api/home/user/goods";
    //获取广告位图片  ad_fac（洗涤业务页面顶部图片）|ad_goods（发现页兑换商场图片）
    /**
     * │ {
     * │     "code":1,
     * │     "msg":"操作成功",
     * │     "data":{
     * │         "img":"https:\/\/www.quanminjieshui.com\/upload\/admin\/20190108\/19116f04112a21a9d03b752902c5b504.jpg",
     * │         "name":"pic"
     * │     }
     * │ }
     */
    public static final String AD_IMG = "api/home/common/getAdImg";


    /**
     * 获取H5链接    about(关于我们)|service(服务协议)|contract(合同条款)
     */
    public static final String GET_URL = "api/home/common/getUrl";

    /**
     * 关于我们链接
     */
    public static final String ABOUT_US = "https://quanminjieshui.com/h5/user/about.html";

    /**
     * 注册平台服务协议 TODO
     */
    public static final String PLATFORM_AGREEMENT = "https://www.baidu.com";

    /**
     * 文件上传
     */
    public static final String UPLOAD = "api/user/Upload/one";

    /**
     * 商品列表--发现兑换商城
     */
    public static final String GOODS_lISTS = "api/home/goods/goodsLists";

    /**
     * 商品详情
     */
    public static final String GOODS_DETAIL = "api/home/goods/goodsDetail";

    /**
     * 申请转账
     */
    public static final String MVJSL = "api/home/user/moveJsl";


    /**
     * 版本检查
     */
    public static final String APP_VERSION = "api/home/public/ver";

    /**
     * 改变头像
     */
    public static final String CHANGE_AVATAR = "api/home/user/changeAvatar";

    /**
     * 查看用户是否可以支付--兑换商城
     */
    public static final String CHECK_USER_PAY = "api/home/goods/checkUserPay";

    /**
     * 创建订单--兑换商城
     */
    public static final String CREATE_ORDER_EXCHANGE = "api/home/goods/payOrder";

    /**
     * 获取用户地址--兑换商城
     */
    public static final String USER_ADDRESS = "api/home/goods/userAddress";

    /**
     * 我的资产-回购虚拟股票
     */
    public static final String BUY_BACK="https://www.shuzijieshui.com/api/home/user/buyBack";

    /**
     * 我的资产-回购虚拟股票
     */
    public static final String STOCK_2_JSL="https://www.shuzijieshui.com/api/home/user/stockToJsl";

    /**
     * 系统消息
     */
    public static final String SYS_MSG="https://www.shuzijieshui.com/api/home/user/msg";
}
