package com.shuzijieshui.www.waterchain;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.multidex.MultiDex;

import com.shuzijieshui.www.waterchain.utils.NetworkStateReceiver;
import com.shuzijieshui.www.waterchain.utils.datacache.XCCacheManager;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by WanghongHe on 2018/12/3 11:26.
 */

public class WaterChainApplication extends Application {

    public final static String TAG = "WaterChainApplication";
    private static WaterChainApplication application;
    private NetworkStateReceiver networkStateReceiver;
    public static XCCacheManager xcCacheManager;
    public static WaterChainApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 为了保证运营数据的准确性，建议不要在异步线程初始化Bugly。
         *
         *     第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         *
         *         输出详细的Bugly SDK的Log；
         *         每一条Crash都会被立即上报；
         *         自定义日志将会在Logcat中输出。
         *
         *     建议在测试阶段建议设置成true，发布时设置为false。
         */
        CrashReport.initCrashReport(getApplicationContext(), "55cd264394", true);//todo  正式发布
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
        XCCacheManager.getInstance(application,1000).init();
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
