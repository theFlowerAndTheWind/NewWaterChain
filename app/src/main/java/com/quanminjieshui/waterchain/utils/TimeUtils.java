package com.quanminjieshui.waterchain.utils;

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
}
