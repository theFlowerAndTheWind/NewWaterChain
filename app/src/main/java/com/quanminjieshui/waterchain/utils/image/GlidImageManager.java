package com.quanminjieshui.waterchain.utils.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Looper;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.quanminjieshui.waterchain.R;
import com.quanminjieshui.waterchain.WaterChainApplication;
import com.quanminjieshui.waterchain.utils.LogUtils;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by songxiaotao on 2018/12/16.
 * Class Note:
 */

public class GlidImageManager {

    private static GlidImageManager instance;

    private GlidImageManager(){

    }

    public static GlidImageManager getInstance(){
        if(instance == null){
            synchronized (GlidImageManager.class){
                if(instance == null) instance = new GlidImageManager();
            }
        }
        return instance;
    }

    public void loadImageUri(Context context, String img_url, ImageView imageView, int default_img) {

        Glide.with(context)                             //配置上下文
                .load(Uri.fromFile(new File(img_url)))      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(default_img)           //设置错误图片
                .placeholder(default_img)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }


    public void loadImage(Context context, int res_id, ImageView imageView, int default_img) {

        Glide.with(context)                             //配置上下文
                .load(res_id)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(default_img)           //设置错误图片
                .placeholder(default_img)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }
    public void loadImageById(Context context, String imageId, ImageView imageView, int default_img) {
        GlideUrl glideUrl=getGlideUrl(context,imageId);

        Glide.with(context)                             //配置上下文
                .load(glideUrl)      //设置图片路径(fix #8,文件名包含%符号 无法识别和显示)
                .error(default_img)           //设置错误图片
                .placeholder(default_img)     //设置占位图片
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }

    public void loadImageView(Context context, String imageUrl,ImageView imageView, int default_img){
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存全尺寸
                .into(imageView);
    }
    //加载圆角图片
    public void loadRoundImage(Context context, String imageId, final ImageView imageView, int placeholder, int errorImg) {
        GlideUrl glideUrl=getGlideUrl(context,imageId);

        Glide.with(context)
                .load(glideUrl)
                .placeholder(placeholder)
                .error(errorImg)
                .override(750,750)
                //.centerCrop() 千万不要加，加了就没有圆角效果了
                .transform(new FitCenter(context), new GlideRoundTransform(context,10))
                .into(imageView);
    }


    //加载圆形图片
    public void loadCirclePic(final Context context, String imageId, final ImageView imageView,int placeholder,int errorImg) {
        GlideUrl glideUrl=getGlideUrl(context,imageId);
        Glide.with(context)
                .load(glideUrl)
                .asBitmap()
                .override(750,750)
                .transform(new FitCenter(context),new GlideCircleTransform(context))
                .placeholder(placeholder)
                .error(errorImg)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

    //加载头像 transform重载--解决图片大小不一的问
    public void loadCircleImg(final Context context, String imgUrl, final ImageView imageView,int placeholder,int errorImg) {
        Glide.with(context)
                .load(imgUrl)
                .asBitmap()
                .override(750,750)
                .transform(new FitCenter(context),new GlideCircleTransform(context))
                .placeholder(placeholder)
                .error(errorImg)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

    public void loadCircleImg(final Context context, File file, final ImageView imageView,int placeholder,int errorImg) {
        Glide.with(context)
                .load(file)
                .asBitmap()
                .override(750,750)
                .transform(new FitCenter(context),new GlideCircleTransform(context))
                .placeholder(placeholder)
                .error(errorImg)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

    public  static GlideUrl getGlideUrl(Context context,String imageUrl){

        GlideUrl glideUrl = new GlideUrl(imageUrl, new LazyHeaders.Builder()
//                .addHeader("token", (String) SPUtils.get(context,HttpConfig.HEAD_TOKEN_KEY,""))
                //.addHeader("key2", )
                .build());
        return glideUrl;
    }

    /**
     * 清除所有内存缓存(需要在Ui线程操作)
     */
    public static void clearMemoryCache(){
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(WaterChainApplication.getInstance()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除所有磁盘缓存(需要在子线程操作)
     */
    public static void clearDiskCache(){
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(WaterChainApplication.getInstance()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(WaterChainApplication.getInstance()).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片所有缓存
     * ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR 系统默认路径
     */
    public void clearImageAllCache() {
        clearDiskCache();
        clearMemoryCache();
        deleteFolderFile(WaterChainApplication.getInstance().getExternalCacheDir() + GlideCatchConfig.GLIDE_CARCH_DIR, true);
    }

    /**
     * 获取Glide造成的缓存大小
     * InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR 系统默认路径
     * @return CacheSize
     */
    public String getCacheSize() {
        try {
            return getFormatSize(getFolderSize(new File(WaterChainApplication.getInstance().getCacheDir() + GlideCatchConfig.GLIDE_CARCH_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.e("获取指定文件夹内文件大小失败：\n"+e.getMessage());
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.e("删除指定目录下的文件失败：\n"+e.getMessage());
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

}
