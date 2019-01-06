package com.quanminjieshui.waterchain.utils;

import android.text.format.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by songxiaotao on 2019/1/4.
 * Class Note:
 */

public class TimeUtils {

    /**
     * 秒转时分秒
     * @param second
     * @return
     */
    public static String getToHMSTime(int second) {
        if (second < 10) {
            return "00分0" + second+"秒";
        }
        if (second < 60) {
            return "00分" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute +"分"+ "0" + second+"秒";
                }
                return "0" + minute +"分"+ second+"秒";
            }
            if (second < 10) {
                return minute + "分"+"0" + second+"秒";
            }
            return minute + "分" + second+"秒";
        }
        int hour = second / 3600;
        int minute = (second - hour * 3600) / 60;
        second = second - hour * 3600 - minute * 60;
        if (hour < 10) {
            if (minute < 10) {
                if (second < 10) {
                    return "0" + hour + "时0" + minute + "分0" + second+"秒";
                }
                return "0" + hour + "时0" + minute + "分" + second+"秒";
            }
            if (second < 10) {
                return "0" + hour+"时" + minute + "分0" + second+"秒";
            }
            return "0" + hour+"时" + minute + "分" + second+"秒";
        }
        if (minute < 10) {
            if (second < 10) {
                return hour + "时0" + minute + "分0" + second+"秒";
            }
            return hour + "时0" + minute + "分" + second+"秒";
        }
        if (second < 10) {
            return hour+"时" + minute + "分0" + second+"秒";
        }
        return hour +"时"+ minute + "分" + second+"秒";
    }

    public static String getToMSTime(int second) {
        if (second < 10) {
            return "00分0" + second+"秒";
        }
        if (second < 60) {
            return "00分" + second;
        }
        if (second < 3600) {
            int minute = second / 60;
            second = second - minute * 60;
            if (minute < 10) {
                if (second < 10) {
                    return "0" + minute +"分"+ "0" + second+"秒";
                }
                return "0" + minute +"分"+ second+"秒";
            }
            if (second < 10) {
                return minute + "分"+"0" + second+"秒";
            }
            return minute + "分" + second+"秒";
        }else{
            return null;
        }
    }

    /**
     * 日期字符串转换Date实体
     * @param serverTime
     * @param format
     * @return
     */
    public static Date parseServerTime(String serverTime, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINESE);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        Date date = null;
        try {
            date = sdf.parse(serverTime);
        } catch (Exception e) {
            LogUtils.d(e);
        }
        return date;
    }

    /**
     * 秒转换成时分秒
     * @param lSeconds
     * @return
     */
    public static String convertSecToTimeString(long lSeconds) {
        long nHour = lSeconds / 3600;
        long nMin = lSeconds % 3600;
        long nSec = nMin % 60;
        nMin = nMin / 60;

        return String.format("%02d小时%02d分钟%02d秒", nHour, nMin, nSec);
    }

    /**
     * Date对象获取时间字符串
     * @param date
     * @param format
     * @return
     */
    public static String getDateStr(Date date,String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 时间戳转换日期格式字符串
     * @param time
     * @param format
     * @return
     */
    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }

    /**
     * 日期格式字符串转换时间戳
     * @param date
     * @param format
     * @return
     */
    public static String date2TimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取某个日期前后N天的日期
     *
     * @param beginDate
     * @param distanceDay 前后几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @param format      日期格式，默认"yyyy-MM-dd"
     * @return
     */
    public static String getOldDateByDay(Date beginDate, int distanceDay, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat dft = new SimpleDateFormat(format);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 获取前后几个月的日期
     * @param beginDate
     * @param distanceMonth
     * @param format
     * @return
     */
    public static String getOldDateByMonth(Date beginDate, int distanceMonth, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat dft = new SimpleDateFormat(format);
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + distanceMonth);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    /**
     * 判断一个时间是否在某个时间范围内
     * @param deadlineHour
     * @param deadlineMin
     * @return
     */
    public static boolean isCurrentInTimeScope(int deadlineHour, int deadlineMin) {
        boolean result;
        // 1000 * 60 * 60 * 24
        final long aDayInMillis = 86400000;
        final long currentTimeMillis = System.currentTimeMillis();
        //截止时间
        Time deadlineTime = new Time();
        deadlineTime.set(currentTimeMillis);
        deadlineTime.hour = deadlineHour;
        deadlineTime.minute = deadlineMin;
        //当前时间
        Time startTime = new Time();
        startTime.set(currentTimeMillis);
        //当前时间推后20分钟
        Date d = new Date(currentTimeMillis);
        long myTime = (d.getTime() / 1000) + 20 * 60;
        d.setTime(myTime * 1000);
        Time endTime = new Time();
        endTime.set(myTime);
        if (!startTime.before(endTime)) {
            // 跨天的特殊情况（比如22:00-8:00）
            startTime.set(startTime.toMillis(true) - aDayInMillis);
            result = !deadlineTime.before(startTime) && !deadlineTime.after(endTime);
            // startTime <= deadlineTime <=endTime
            Time startTimeInThisDay = new Time();
            startTimeInThisDay.set(startTime.toMillis(true) + aDayInMillis);
            if (!deadlineTime.before(startTimeInThisDay)) {
                result = true;
            }
        } else {
            // 普通情况(比如 8:00 - 14:00)
            result = !deadlineTime.before(startTime) && !deadlineTime.after(endTime);
            // startTime <= deadlineTime <=endTime
        }
        return result;
    }
}
