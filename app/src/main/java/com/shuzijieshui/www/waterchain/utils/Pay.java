package com.shuzijieshui.www.waterchain.utils;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alipay.sdk.app.PayTask;
import com.shuzijieshui.www.waterchain.beans.WechatBean;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

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
    public boolean payByWX(Context context, WechatBean param) {
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

    public void payByAli(final Activity activity, final String orderInfo, final Handler mHandler) {

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = ALIPAY;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
