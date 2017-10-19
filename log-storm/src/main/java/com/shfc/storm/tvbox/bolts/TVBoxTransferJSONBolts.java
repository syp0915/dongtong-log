package com.shfc.storm.tvbox.bolts;

import com.alibaba.fastjson.JSON;
import com.shfc.storm.bean.TVLogBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/24 上午11:22.
 */
public class TVBoxTransferJSONBolts extends BaseRichBolt {
    private OutputCollector outputCollector;
    private Logger logger = null;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        logger = Logger.getLogger(TVBoxTransferJSONBolts.class);
    }

    @Override
    public void execute(Tuple tuple) {
        String line = tuple.getString(0);
        if (StringUtils.isNotBlank(line)){
            String[] dataArray = line.split(";");
            if (dataArray.length >= 4){
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
                    TVLogBean tvLogBean = new TVLogBean();
                    tvLogBean.setChannelNo(dataArray[1]);
                    tvLogBean.setMac(dataArray[2]);
                    tvLogBean.setSessionId(dataArray[3]);
                    tvLogBean.setVisitTimeMills(System.currentTimeMillis());
                    tvLogBean.setVisitDateTime(new Date(tvLogBean.getVisitTimeMills()));
                    logger.info("解析出来的json是----------->" + JSON.toJSONString(tvLogBean));
                    outputCollector.emit(new Values(tvLogBean));
                    outputCollector.ack(tuple);
                } catch (Exception e) {
                    e.printStackTrace();
                    outputCollector.fail(tuple);
                }
            }
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("bean"));
    }
}
