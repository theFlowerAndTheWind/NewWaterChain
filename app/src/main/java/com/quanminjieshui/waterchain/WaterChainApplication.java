package com.quanminjieshui.waterchain;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.multidex.MultiDex;

import com.quanminjieshui.waterchain.utils.NetworkStateReceiver;
import com.quanminjieshui.waterchain.utils.datacache.XCCacheManager;

/**
 * Created by WanghongHe on 2018/12/3 11:26.
 */

public class WaterChainApplication extends Application {

    public final static String TAG = "WaterChainApplication";
    private static WaterChainApplication application;
    private NetworkStateReceiver networkStateReceiver;

    public static WaterChainApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        //初始化网络receiver
        registerReceiver();

        //初始化并配置缓存策略
        cacheStrategy();
    }


    /**
     * MEMORY_FIRST = 1000 MEMORY_ONLY = 2000 DISK_ONLY = 3000
     */
    private void cacheStrategy() {
        XCCacheManager.getInstance(application,1000).init(application);
    }

    private void registerReceiver() {
        networkStateReceiver = new NetworkStateReceiver();
        //创建意图过滤器
        IntentFilter filter = new IntentFilter();
        //添加动作，监听网络
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkStateReceiver, filter);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
