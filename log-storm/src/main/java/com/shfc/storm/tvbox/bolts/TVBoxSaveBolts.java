package com.shfc.storm.tvbox.bolts;

import com.alibaba.fastjson.JSON;
import com.shfc.storm.bean.TVLogBean;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/31 下午2:52.
 */
public class TVBoxSaveBolts extends BaseRichBolt {
    private OutputCollector outputCollector;
    private Logger logger;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        logger = Logger.getLogger(TVBoxSaveBolts.class);
    }

    @Override
    public void execute(Tuple tuple) {
        TVLogBean tvLogBean = (TVLogBean)tuple.getValue(0);
        logger.info("存储模块接收到的serviceLogBean----->" + JSON.toJSONString(tvLogBean));

        outputCollector.ack(tuple);

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
