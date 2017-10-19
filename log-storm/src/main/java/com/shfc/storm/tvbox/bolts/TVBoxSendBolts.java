package com.shfc.storm.tvbox.bolts;

import com.alibaba.fastjson.JSON;
import com.shfc.storm.bean.TVLogBean;
import com.shfc.storm.util.OKHttpClientUtils;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/31 下午2:45.
 */
@Service
public class TVBoxSendBolts extends BaseRichBolt {
    private Logger logger = null;
    private OutputCollector outputCollector;

    @Value("${log.source.send.url}")
    public String sendLogSourceUrl;
    //开发环境
    //private String defaultUrl = "http://192.168.200.32:8888/shfc.tv_api_logs";

    //测试环境
    //private String defaultUrl = "http://192.168.201.233:8083/statistics/shfc.tv_api_logs";

    //生产环境
    private String defaultUrl = "http://80.2.65.1:8088/statistics/shfc.tv_api_logs";

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        logger = Logger.getLogger(TVBoxSendBolts.class);
        logger.info("url---------->" + sendLogSourceUrl);
    }

    @Override
    public void execute(Tuple tuple) {
        TVLogBean tvLogBean = (TVLogBean)tuple.getValue(0);
        logger.info("发送模块接收到的tvLogBean----->" + JSON.toJSONString(tvLogBean));
        if (tvLogBean != null){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("channel", tvLogBean.getChannelNo());
                param.put("mac", tvLogBean.getMac());
                param.put("timestamp", sdf.format(tvLogBean.getVisitDateTime()));
                param.put("session", tvLogBean.getSessionId());
                if (sendLogSourceUrl == null){
                    sendLogSourceUrl = defaultUrl;
                }
                logger.info("request 13 url---------->" + sendLogSourceUrl);
                logger.info("request params is--------->" + JSON.toJSONString(param));
                OKHttpClientUtils.requestRemoteServer(sendLogSourceUrl, param);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("bean"));
    }
}
