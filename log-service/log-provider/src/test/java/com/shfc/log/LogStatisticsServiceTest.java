package com.shfc.log;

import com.alibaba.fastjson.JSON;
import com.shfc.common.result.ResultDO;
import com.shfc.log.dto.CacheStatisticsDTO;
import com.shfc.log.service.LogStatisticsService;
import com.shfc.log.task.LogScheduleTask;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/13 下午1:51.
 */
public class LogStatisticsServiceTest extends JunitBaseTest {

    @Autowired(required = false)
    private LogStatisticsService logStatisticsService;

    @Autowired(required = false)
    private LogScheduleTask logScheduleTask;

    @Test
    public void testRedisCount(){
        ResultDO<CacheStatisticsDTO> resultDO = logStatisticsService.getChannelRedisUseCount("998", 100013L);
        System.out.println("----------->" + JSON.toJSONString(resultDO));
    }

    @Test
    public void testSchedule(){
        try {
            logScheduleTask.persistLogCacheData2DB();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
