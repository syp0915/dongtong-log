package com.shfc.storm.env;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/2 下午1:51.
 */
public class SpringContextUtil {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext(){
        if (applicationContext == null){
            synchronized (ApplicationContext.class){
                if (applicationContext == null){
                    applicationContext = new ClassPathXmlApplicationContext("classpath*:config/application-*.xml");
                }
            }
        }
        return applicationContext;
    }
}
