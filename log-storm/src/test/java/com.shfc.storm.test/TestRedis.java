package com.shfc.storm.test;

import com.alibaba.fastjson.JSON;
import com.fc.common.redis.RedisUtil;
import com.shfc.storm.util.OKHttpClientUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/6 下午2:15.
 */
public class TestRedis extends JunitBaseTest{

    @Test
    public void testRedis(){
        RedisUtil.set("hhh", "dsadasdasdasdsa", 12*60*60L);
        Object object = RedisUtil.get("hhnjkbkjh");
        if (object != null){
            System.out.println("----------------->" + object.toString());
        }
    }

    @Test
    public void testRemote(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String url = "http://192.168.200.32:8888/shfc.tv_api_logs";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("channel", "998");
        param.put("mac", "DASFASFF89FSDCDS");
        param.put("timestamp", sdf.format(new Date()));
        param.put("session", "dsabjkdbasjk7898");
        System.out.println("request 13 url---------->" + url);
        System.out.println("request params is--------->" + JSON.toJSONString(param));
        try {
            OKHttpClientUtils.requestRemoteServer(url, param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
