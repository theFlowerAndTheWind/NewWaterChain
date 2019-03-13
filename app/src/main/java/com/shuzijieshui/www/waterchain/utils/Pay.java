package com.shuzijieshui.www.waterchain.utils;


import android.app.Activity;
import android.content.Context;

import com.shuzijieshui.www.waterchain.beans.WechatBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class Pay {
    public static final int ALIPAY = 1;
    public static final int WX = 2;
    public static final int OFFLINE = 3;


    /**
     * 微信支付
     *
     * @param context
     * @param param
     */
    public static boolean payByWX(Context context, WechatBean param) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, Key.WX_APPID);
        api.registerApp(param.getAppId());
        PayReq request = new PayReq();
        request.appId = param.getAppId();
        request.partnerId = param.getPartnerId();
        request.prepayId = param.getPrepayId();
        request.packageValue = param.get_package();
        request.nonceStr = param.getNoncestr();
        request.timeStamp = param.getTimeStamp();
        request.sign = param.getSign();

        return api.sendReq(request);

    }

}
