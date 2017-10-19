package com.shfc.storm.env;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/12 下午6:56.
 */
public class PropertiesValueUtils {
    private static Properties properties;

    public static Properties getProperties(){
        try {
            if (properties == null){
                properties = PropertiesLoaderUtils.loadAllProperties("log-storm-env.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getStringPropertyValue(String key){
        return getProperties().getProperty(key);
    }

    public static Integer getIntegerPropertyValue(String key){
        String value = getProperties().getProperty(key);
        if (value == null || "".equals(value)){
            value = "0";
        }
        return Integer.parseInt(value);
    }
}
