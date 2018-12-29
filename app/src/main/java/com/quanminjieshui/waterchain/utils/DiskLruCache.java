package com.quanminjieshui.waterchain.utils;

import android.content.Context;
import android.graphics.Bitmap;

import cc.fussen.cache.Cache;

import static cc.fussen.cache.disklrucache.Util.getCacheDir;

/**
 * Created by songxiaotao on 2018/12/29.
 * Class Note:
 */

public class DiskLruCache {

    private static DiskLruCache instance;

    private DiskLruCache(){

    }

    public static DiskLruCache getInstance(){
        if(instance == null){
            synchronized (DiskLruCache.class){
                if(instance == null) instance = new DiskLruCache();
            }
        }
        return instance;
    }

    public void saveObject(Context context,String catchName,Object object){
        String cachePath = getCacheDir(context);
        Cache.with(context)
                .path(cachePath)
                .saveCache(catchName, object);

    }

    public void saveImage(Context context, String imageUrl){
        String cachePath = getCacheDir(context);
        Cache.with(context)
                .path(cachePath)
                .saveImage(imageUrl);
    }

    /**
     * @param context
     * @param object 实体类class
     * @return
     */
    public Object getObject(Context context,String cacheName,Class object){
        String cachePath = getCacheDir(context);
        return Cache.with(context)
                .path(cachePath)
                .getCache(cacheName, object);
    }

    public Bitmap getImage(Context context,String imageUrl){
        String cachePath = getCacheDir(context);
        return Cache.with(context)
                .path(cachePath)
                .getImageCache(imageUrl);

    }
}
