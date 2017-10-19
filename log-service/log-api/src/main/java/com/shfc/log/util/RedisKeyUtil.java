package com.shfc.log.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/5 上午10:46.
 */
public class RedisKeyUtil {

    private static final String DATE_TIME_FORMAT_12 = "yyyyMMddHHmm";
    private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_12);

    /**
     * 拼装redis的key值
     * @param moduleName 模块名称
     * @param merchantId 商户id
     * @param channelNo 频道号
     * @param dateTimeString 时间串，格式为yyyyMMddHHmm
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String jointRedisKeyByInfo(String moduleName, String merchantId, String channelNo, String dateTimeString) {
        return moduleName + "_" + merchantId + "_" + channelNo + "_" + dateTimeString;
    }

    /**
     * 拼装redis的key值，时间字符串默认为当前系统时间字符串
     * @param moduleName 模块名称
     * @param merchantId 商户id
     * @param channelNo 频道号
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String jointRedisKeyByInfo(String moduleName, String merchantId, String channelNo) {
        String dateTimeString = sdf.format(new Date());
        return jointRedisKeyByInfo(moduleName, merchantId, channelNo, dateTimeString);
    }
}
