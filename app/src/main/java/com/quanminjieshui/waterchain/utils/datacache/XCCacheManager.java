package com.quanminjieshui.waterchain.utils.datacache;

import android.content.Context;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by WanghongHe on 2018/12/29 17:48.
 * 双缓存管理框架类XCCacheManager
 */

public class XCCacheManager {

    private static XCCacheManager mInstance = null;

    //缓存策略
    private final static int MEMORY_FIRST = 1000;
    private final static int MEMORY_ONLY = 2000;
    private final static int DISK_ONLY = 3000;
    private int strategy;
    //线程池
    private ExecutorService mExecutor = null;
    //内存缓存
    private MemoryCache mMemoryCache;
    //Disk缓存
    private DiskCache mDiskCache;

    private Context applicationContext;

    private XCCacheManager(Context applicationContext, int strategy) {
        this.strategy = strategy;
        this.applicationContext = applicationContext;
    }

    /**
     * 初始化 DiskLruCache
     */
    public void init() {
        mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        mDiskCache = new DiskCache(this.applicationContext);
        mMemoryCache = new MemoryCache();
    }

    public static XCCacheManager getInstance(Context context, int strategy) {
        if (mInstance == null) {
            synchronized (XCCacheManager.class) {
                if (mInstance == null) {
                    mInstance = new XCCacheManager(context.getApplicationContext(), strategy);
                }
            }
        } else {
            mInstance.setStrategy(strategy);
        }
        return mInstance;
    }

    /**
     * 缓存策略
     * @param strategy
     */
    public void setStrategy(int strategy) {
        switch (strategy) {
            case MEMORY_FIRST:
                if (!mMemoryCache.hasEvictedListener()) {
                    mMemoryCache.setEvictedListener(new MemoryCache.EvictedListener() {
                        @Override
                        public void handleEvictEntry(String evictKey, String evictValue) {
                            mDiskCache.put(evictKey, evictValue);
                        }
                    });
                }
                break;
            case MEMORY_ONLY:
                if (mMemoryCache.hasEvictedListener()) {
                    mMemoryCache.setEvictedListener(null);
                }
                break;
            case DISK_ONLY:
                break;
            default:break;
        }
    }

    /**
     * 从缓存中读取value
     */
    public String readCache(final String key) {
        Future<String> ret = mExecutor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String result = null;
                switch (strategy) {
                    case MEMORY_ONLY:
                        result = mMemoryCache.get(key);
                        break;
                    case MEMORY_FIRST:
                        result = mMemoryCache.get(key);
                        if (result == null) {
                            result = mDiskCache.get(key);
                        }
                        break;
                    case DISK_ONLY:
                        result = mDiskCache.get(key);
                        break;
                    default:break;
                }
                return result;
            }
        });
        try {
            return ret.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将value 写入到缓存中
     */
    public void writeCache(final String key, final String value) {
        mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                switch (strategy) {
                    case MEMORY_FIRST:
                        mMemoryCache.put(key, value);
                        mDiskCache.put(key,value);
                        break;
                    case MEMORY_ONLY:
                        mMemoryCache.put(key, value);
                        break;
                    case DISK_ONLY:
                        mDiskCache.put(key, value);
                        break;
                    default:break;
                }
            }
        });
    }
}
