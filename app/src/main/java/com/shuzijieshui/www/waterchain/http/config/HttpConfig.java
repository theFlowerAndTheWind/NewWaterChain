package com.shuzijieshui.www.waterchain.http.config;

/**
 * Created by WanghongHe on 2018/12/3 12:13.
 */

public class HttpConfig {

    public static int HTTP_TIME = 30000;

//    public static String BASE_URL = "https://www.jsl.com";
    //开发环境
//    public static String BASE_URL = "https://jsl.sshsky.com";
    //生产环境
    public static String BASE_URL = "https://www.shuzijieshui.com";//20190220域名由www.quanminjieshui.com更改为www.shuzijieshui.com


    public static final String HEAD_TOKEN_KEY = "TOKEN";
    public static final String HEAD_SYSTEM_KEY = "SYSTEM";
    public static final String HEAD_PRODUCT_KEY = "PRODUCT";
    public static final String HEAD_VERSION_KEY = "VERSION";
    public static final String HEAD_UUID_KEY = "CLIENT-UUID";

    public static final String ERROR_MSG="网络加载失败,请重试";
}
