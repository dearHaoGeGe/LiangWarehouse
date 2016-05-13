package com.dream.myliu.liangwarehouse.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeUtils
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-8-24
 */
public class TimeUtils {
    private static final int YEAR = 365 * 24 * 60 * 60;
    private static final int MONTH = 30 * 24 * 60 * 60;
    private static final int DAY = 24 * 60 * 60;
    private static final int HOUR = 60 * 60;
    private static final int MINUTE = 60;
    private static SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat();
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm";
    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * long time to string
     * 
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }
    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前
     *
     * @param timestamp
     *            时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {
            timeStr = timeGap / MINUTE + "分钟前";
        } else {
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 将日期字符串以指定格式转换为Date
     *
     * @param timeStr
     *            日期字符串
     * @param format
     *            指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return
     */
    public static Date getTimeFromString(String timeStr, String format) {
        if (StringUtil.isBlank(format)) {
            mSimpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        } else {
            mSimpleDateFormat.applyPattern(format);
        }
        try {
            return mSimpleDateFormat.parse(timeStr);
        } catch (ParseException e) {
            return new Date();
        }
    }
    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }
    public static String getCurrentTime(String format) {
        if (StringUtil.isBlank(format)) {
            mSimpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        } else {
            mSimpleDateFormat.applyPattern(format);
        }
        return mSimpleDateFormat.format(new Date());
    }
    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     * 
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     * 
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

//    public static String Time() {
//        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
//        String date = sDateFormat.format(new java.util.Date());
//        return date;
//    }
}
