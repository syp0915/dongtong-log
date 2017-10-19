package com.shfc.storm.util;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/5/19 上午11:01.
 */
public class OKHttpClientUtils {

    private static Logger logger = Logger.getLogger(OKHttpClientUtils.class);

    /**
     * 统一封装参数，向消息中心发起请求
     * @param url
     * @param params
     * @return
     */
    public static String requestRemoteServer(String url, Map<String, Object> params){
        OkHttpClient client = new OkHttpClient();
        logger.info("message center request params----------->" + JSON.toJSONString(params));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), JSON.toJSONString(params));
        Request request = new Request.Builder().url(url).post(requestBody).build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()){
                logger.info("remote server response------>" + JSON.toJSONString(response));
                String result = response.body().string();
                logger.info("remote server response result----------->" + result);
                return result;
            }else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
