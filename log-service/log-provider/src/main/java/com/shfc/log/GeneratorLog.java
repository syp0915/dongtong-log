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
public class GeneratorLog {
    public static void main(String[] args) throws InterruptedException {
        LogFileUtils.LOG_PATH = "/tmp/logs/module/";
        LogFileUtils logFileUtils = LogFileUtils.getInstance("测试App");
        String[] moduleNameArray = {"短信模块","邮件模块","容器模块","媒资模块","账户模块", "认证模块", "飘屏模块", "网关模块", "监控模块", "机顶盒mac模块", "redis模块", "apistore模块", "cloudweb模块"};
        String[] channelNoArray = {"981_2", "982_3", "983_4", "984_5", "985_6", "986_7", "987_8", "988_9", "997_1", "999_1"};
        while (true){
            for (int i = 0; i < 20; i++) {
                String[] channelInfo = channelNoArray[new Random().nextInt(10)].split("_");
                logFileUtils.info(moduleNameArray[new Random().nextInt(12)], Long.parseLong(channelInfo[1]), channelInfo[0], GeneratorLog.class, "main", Long.parseLong(i*i + ""));
                Thread.sleep(300);
            }
        }
    }
}
