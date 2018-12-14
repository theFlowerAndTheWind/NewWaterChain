package com.quanminjieshui.waterchain.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.quanminjieshui.waterchain.event.NetworkStateEvent;

import de.greenrobot.event.EventBus;


/**
 * Created by WanghongHe on 2018/11/10 11:41.
 */

public class NetworkStateReceiver extends BroadcastReceiver {
    private static boolean isFirst = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, ConnectivityManager.CONNECTIVITY_ACTION)) {
            if (!isFirst) {
                dealNetWorkChange(context);
            }
            isFirst = false;
        }
    }

    private void dealNetWorkChange(Context context) {
        EventBus.getDefault().post(new NetworkStateEvent());
    }
}
