package com.shfc.log;

import java.util.Random;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/30 上午9:56.
 */
public class GeneratorTVLog {
    public static void main(String[] args) throws InterruptedException {
        String[] sessionIds = {"1491033109302","1491033109807","1491033110309","1491033110815","1491033111320",
                "1491033111826","1491033112331","1491033112839","1491033113347","1491033113857",
                "1491033114368","1491033114880","1491033115394","1491033115907","1491033116423",
                "1491033116941","1491033117461","1491033117979","1491033118500","1491033119019"};
        String[] macs = {"30:44:46:44","54:78:68:27","64:18:94:67","5:22:9:26","37:43:56:63",
                "55:28:52:39","21:8:92:30","72:31:9:38","56:51:26:55","47:16:47:47",
                "83:91:22:50","4:97:67:74","74:92:76:3","28:67:49:64","3:55:43:18",
                "82:59:80:15","21:19:98:51","48:8:33:20","62:7:58:25","38:92:33:43"};
        //LogFileUtils.LOG_PATH = "/srv/logs/tv";
        LogFileUtils.LOG_PATH = "/tmp/logs/tv/";
        LogFileUtils logFileUtils = LogFileUtils.getInstance("tv-statistics");
        while (true){
            for (int i = 0; i < 20; i++) {
                //logFileUtils.info("测试签名", Long.parseLong(i+""), i+"", GeneratorTVLog.class, "main", Long.parseLong(i*i + ""));
                logFileUtils.info(99 + (i + ""), macs[i] + "", sessionIds[i]);
                Thread.sleep(new Random().nextInt(3000));
            }
        }
    }
}
