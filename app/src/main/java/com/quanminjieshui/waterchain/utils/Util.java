package com.quanminjieshui.waterchain.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.util.SimpleArrayMap;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import com.quanminjieshui.waterchain.WaterChainApplication;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sunhewei on 2018/6/29.
 */

public class Util {

    private static String Tag = "Util";
    private static long lastClickTime;

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context         上下文对象
     * @param pxValue         需要的尺寸
     * @param targetheight_px 适配尺寸——高度
     * @return
     */
    public static int px2sp(Context context, float pxValue, float targetheight_px) {
        int height_px = context.getResources().getDisplayMetrics().heightPixels;
        pxValue = (height_px / targetheight_px * pxValue);

        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int px2px(Context context, float pxValue, float targetheight_px) {
        int height_px = context.getResources().getDisplayMetrics().heightPixels;
        pxValue = (height_px / targetheight_px * pxValue);
        return (int) (pxValue + 0.5);

//        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
//        return (int) (pxValue / fontScale + 0.5f);
    }


    /**
     * 毫秒转日期
     *
     * @param millisecond
     * @return
     */
    public static String millisecond2Date(long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        date.setTime(millisecond);
        return simpleDateFormat.format(date);
    }

    public static int getAccurateTime(int type,long millisecond){
        int year = 2018,month= 10,day= 26,hour= 19;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // 用parse方法，可能会异常，所以要try-catch
            Date date = simpleDateFormat.parse(millisecond2Date(millisecond));
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            year = calendar.get(Calendar.YEAR);
            // 这里要注意，月份是从0开始。
            month = calendar.get(Calendar.MONTH);
            // 获取天
            day = calendar.get(Calendar.DAY_OF_MONTH);
            //获取时
            hour = calendar.get(Calendar.HOUR_OF_DAY);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        switch (type) {
            case 0://年
                return year;
            case 1://月
                return month;
            case 2://日
                return day;
            case 3://时
                return hour;
            default:return -1;
        }
    }
    public static String utcDateToDate(String utcDate){
        try {
            //yyyy-MM-dd'T'HH:mm:ss.SSSZ
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            Date date = df.parse(utcDate);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return utcDate;
        }
    }

    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
//        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        String regEx1 = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()){
            return true;
        }
        else {
            return false;
        }
    }


    public static boolean isPhone(String string) {
        if (string == null)
            return false;
        String regEx1 = "^[\\d]{11}";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean isEmpty(final Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof CharSequence && obj.toString().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SimpleArrayMap && ((SimpleArrayMap) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
                return true;
            }
        }
        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (obj instanceof LongSparseArray
                    && ((LongSparseArray) obj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 1000) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }

    public static String hide4Phone(String phone) {
        String phoneNumber = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        return phoneNumber;
    }

    /**
     * get App versionName
     *
     * @return
     */
    public static String getVersionName() {
        PackageManager packageManager = WaterChainApplication.getInstance().getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(WaterChainApplication.getInstance().getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
