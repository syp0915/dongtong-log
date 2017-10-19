package com.shfc.log;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Package com.shfc.log.LogFileUtils
 * @Description: LogFileUtils
 * @Company:上海房产
 * @Copyright: Copyright (c) 2016
 * Author lv bin
 * @date 2017/3/15 15:48
 * version V1.0.0
 */
public class LogFileUtils {

    private Logger logger = Logger.getLogger(LogFileUtils.class);

    private DailyRollingFileAppender dailyRollingFileAppender = null;

    private static LogFileUtils logFile = null;

    public static String LOG_PATH = "/tmp/logs/";

    private static String APP_NAME = null;

    private static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.sss";

    private static String datePattern = "'.'yyyy-MM-dd'.log'";

    private static Layout layout = new PatternLayout("%p-%-d{yyyy-MM-dd HH:mm:ss.sss}-%t-%C{1}.%M(%L)$$$%m%n");

    private LogFileUtils(){
        try {
            String logPath = LOG_PATH.concat(APP_NAME).concat("/").concat("data.log");
            dailyRollingFileAppender = new DailyRollingFileAppender(layout, logPath, datePattern);
            logger.addAppender(dailyRollingFileAppender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例
     * @param appName
     * @return
     */
    public static synchronized LogFileUtils getInstance(String appName) {
        APP_NAME = appName;
        if (logFile == null) {
            logFile = new LogFileUtils();
        }

        return logFile;
    }

    /**
     * 正常日志
     * @param module     模块名称
     * @param merchantId 商户id
     * @param channelNo 频道编号
     * @param clazz      类名称
     * @param method     方法名
     * @param startTimeMillis 开始时间毫秒
     */
    public void info(String module, Long merchantId, String channelNo, Class clazz, String method, Long startTimeMillis) {
        StringBuilder sb = new StringBuilder();
        sb.append(APP_NAME).append(";");
        sb.append(module).append(";");
        sb.append(merchantId).append(";");
        sb.append(channelNo).append(";");
        sb.append(clazz.getName()).append(";");
        sb.append(method).append(";");
        sb.append(startTimeMillis).append(";");
        sb.append(System.currentTimeMillis() - startTimeMillis).append(";");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sb.append(sdf.format(new Date())).append(";");

        info(sb.toString());
    }

    /**
     * 错误日志
     * @param module     模块名称
     * @param merchantId - 商户id
     * @param channelNo 频道编号
     * @param clazz      - 类名称
     * @param method     - 方法名
     * @param startTimeMillis - 开始时间毫秒
     */
    public void error(String module, Long merchantId, String channelNo, Class clazz, String method, Long startTimeMillis) {

        StringBuilder sb = new StringBuilder();
        sb.append(APP_NAME).append(";");
        sb.append(module).append(";");
        sb.append(merchantId).append(";");
        sb.append(channelNo).append(";");
        sb.append(clazz.getName()).append(";");
        sb.append(method).append(";");
        sb.append(startTimeMillis).append(";");
        sb.append(System.currentTimeMillis() - startTimeMillis).append(";");
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sb.append(sdf.format(new Date())).append(";");

        error(sb.toString());
    }

    /**
     * 机顶盒日志搜集专用方法
     * @param channelNo 频道号
     * @param mac mac地址
     * @param sessionid sessionId
     */
    public void info(String channelNo, String mac, String sessionid) {
        StringBuilder sb = new StringBuilder();
        sb.append(APP_NAME).append(";");
        sb.append(channelNo).append(";");
        sb.append(mac).append(";");
        sb.append(sessionid).append(";");
        info(sb.toString());
    }

    /**
     * info log
     *
     * @param content
     */
    public void info(String content) {
        logger.info(content.concat("true"));
    }

    public void error(String content) {
        logger.error(content.concat("false"));
    }

    public static void main(String[] args) {
        LogFileUtils utils = LogFileUtils.getInstance("test-log");
        for (int i = 0; i < 500L; i++) {
            utils.info("短信签名", i * 1L, i + "", Logger.class, "sign", 1000L);
            utils.error("短信签名", i * 1L, i + "", Logger.class, "sign", 1000L);
        }
    }
}
