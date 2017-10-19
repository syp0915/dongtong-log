package com.shfc.storm.module.bolts;

import com.alibaba.fastjson.JSON;
import com.shfc.storm.bean.ServiceLogBean;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.text.ParseException;
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
public class ModuleTransferJSONBolts extends BaseRichBolt {
    private OutputCollector outputCollector;
    private Logger logger = null;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        logger = Logger.getLogger(ModuleTransferJSONBolts.class);
    }

    @Override
    public void execute(Tuple tuple) {
        String line = tuple.getString(0);
        if (StringUtils.isNotBlank(line)){
            String[] dataArray = line.split(";");
            if (dataArray.length >= 8){
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
                    ServiceLogBean serviceLogBean = new ServiceLogBean();
                    serviceLogBean.setAppName(dataArray[0]);
                    serviceLogBean.setModule(dataArray[1]);
                    serviceLogBean.setMerchantId(Long.parseLong(dataArray[2]));
                    serviceLogBean.setChannelNo(dataArray[3]);
                    serviceLogBean.setClassName(dataArray[4]);
                    serviceLogBean.setMethod(dataArray[5]);
                    serviceLogBean.setPeriod(Long.parseLong(dataArray[6]));
                    serviceLogBean.setStartTimeMillis(Long.parseLong(dataArray[7]));
                    serviceLogBean.setStartDateTime(new Date(serviceLogBean.getStartTimeMillis()));
                    serviceLogBean.setRecordDateTime(sdf.parse(dataArray[8]));
                    serviceLogBean.setSuccess(Boolean.parseBoolean(dataArray[9]));
                    logger.info("解析出来的json是----------->" + JSON.toJSONString(serviceLogBean));
                    outputCollector.emit(new Values(serviceLogBean));
                    outputCollector.ack(tuple);
                } catch (ParseException e) {
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
