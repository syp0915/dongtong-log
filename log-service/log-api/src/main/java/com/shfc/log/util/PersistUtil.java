package com.shfc.log.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/5 下午6:53.
 */
public class PersistUtil {

    /**
     * 获取指定时间当前分钟和往前推三分钟的yyyyMMddHHmm格式数据数组
     * @param date
     * @return
     */
    public static String[] getPre4MinDateTimeStringArray(Date date){
        String[] dateTimeStringArray = new String[4];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        dateTimeStringArray[0] = sdf.format(date);//当前分钟
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -1);
        dateTimeStringArray[1] = sdf.format(calendar.getTime());
        calendar.add(Calendar.MINUTE, -1);
        dateTimeStringArray[2] = sdf.format(calendar.getTime());
        calendar.add(Calendar.MINUTE, -1);
        dateTimeStringArray[3] = sdf.format(calendar.getTime());
        return dateTimeStringArray;
    }

    /**
     * 获取指定时间当前小时和往前推三小时的yyyyMMddHH格式数据数组
     * @param date
     * @return
     */
    public static String[] getPre2HourDateTimeStringArray(Date date){
        String[] dateTimeStringArray = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        dateTimeStringArray[0] = sdf.format(date);//当前小时
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        dateTimeStringArray[1] = sdf.format(calendar.getTime());
        return dateTimeStringArray;
    }

    /**
     * 获取指定时间当天和往前推一天的yyyyMMddHH格式数据数组
     * @param date
     * @return
     */
    public static String[] getPre2DayDateStringArray(Date date){
        String[] dateTimeStringArray = new String[2];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        dateTimeStringArray[0] = sdf.format(date);//当前天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        dateTimeStringArray[1] = sdf.format(calendar.getTime());
        return dateTimeStringArray;
    }
}
