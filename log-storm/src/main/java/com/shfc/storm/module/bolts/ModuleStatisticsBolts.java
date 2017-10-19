package com.shfc.storm.module.bolts;

import com.alibaba.fastjson.JSON;
import com.fc.common.redis.RedisUtil;
import com.shfc.log.util.RedisKeyUtil;
import com.shfc.storm.bean.ServiceLogBean;
import com.shfc.storm.env.SpringContextUtil;
import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

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
public class ModuleStatisticsBolts extends BaseRichBolt {
    private Logger logger = null;
    private OutputCollector outputCollector;
    private static final Long REDIS_COUNT_DATA_STAY_TIME = 12 * 60 * 60L;//存入redis有效时间，暂定半天
    private static final String DATE_FORMAT_17 = "yyyy-MM-dd HH:mm:ss.SSS";

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        logger = Logger.getLogger(ModuleStatisticsBolts.class);
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
    }

    @Override
    public void execute(Tuple tuple) {
        ServiceLogBean serviceLogBean = (ServiceLogBean)tuple.getValue(0);
        logger.info("解析模块接收到的数据是-------->" + JSON.toJSONString(serviceLogBean));
        if (serviceLogBean != null){
            String moduleName = serviceLogBean.getModule();
            String merchantId = serviceLogBean.getMerchantId() + "";
            String channelNo = serviceLogBean.getChannelNo();
            String key = RedisKeyUtil.jointRedisKeyByInfo(moduleName, merchantId, channelNo);
            Long count = 1L;
            logger.info("等待被取出的redis key------>" + key);
            Object value = RedisUtil.get(key);
            if (value != null){
                count = Long.parseLong(value.toString());
                count++;
            }
            RedisUtil.set(key, count, REDIS_COUNT_DATA_STAY_TIME);
            logger.info("存入redis的数据:" + key + "----->" + count);
        }
        //outputCollector.ack(tuple);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //outputFieldsDeclarer.declare(new Fields("bean"));
    }
}
