package com.shfc.storm.tvbox.bolts;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/24 上午10:19.
 */
public class TVBoxFilterBolts extends BaseRichBolt{
    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        String sourceString = tuple.getString(0);
        Logger.getLogger(TVBoxFilterBolts.class).info("接收到kafka传来的数据----------------->" + sourceString);
        if (StringUtils.isNotBlank(sourceString)){
            String[] lines = sourceString.split("\n");
            for (String line :  lines) {
                if (StringUtils.isBlank(line)){
                    continue;
                }
                outputCollector.emit(new Values(line));
            }
            outputCollector.ack(tuple);
        }else {
            outputCollector.fail(tuple);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }
}
