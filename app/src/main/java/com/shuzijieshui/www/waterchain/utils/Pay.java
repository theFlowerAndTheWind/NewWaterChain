package com.shuzijieshui.www.waterchain.utils;


import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

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
        IWXAPI api = WXAPIFactory.createWXAPI(context, Constants.WX_APPID);
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
                /**
                 *String orderInfo: app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。
                 * boolean isShowPayLoading:用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，这个值设置为true，将会在调用pay接口的时候直接唤起一个loading，直到唤起H5支付页面或者唤起外部的钱包付款页面loading才消失。（建议将该值设置为true，优化点击付款到支付唤起支付页面的过渡过程。）
                 */
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
