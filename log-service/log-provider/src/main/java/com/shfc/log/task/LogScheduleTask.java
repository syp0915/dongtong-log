package com.shfc.log.task;

import com.fc.common.redis.RedisUtil;
import com.shfc.cloud.cloudbase.dto.ChannelMerchantDTO;
import com.shfc.cloud.cloudbase.service.ChannelService;
import com.shfc.common.result.ResultDO;
import com.shfc.log.domain.LogModuleDay;
import com.shfc.log.domain.LogModuleHour;
import com.shfc.log.domain.LogModuleMinute;
import com.shfc.log.manager.LogStatisticsManager;
import com.shfc.log.util.PersistUtil;
import com.shfc.log.util.RedisKeyUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/1 上午11:24.
 */
@Service
public class LogScheduleTask {

    private Logger logger = Logger.getLogger(LogScheduleTask.class);

    @Autowired(required = false)
    private ChannelService channelService;

    @Autowired(required = false)
    private LogStatisticsManager logStatisticsManager;

    private final String DATE_FORMAT_12 = "yyyyMMddHHmm";
    private final String DATE_FORMAT_10 = "yyyyMMddHH";
    private final String DATE_FORMAT_8 = "yyyyMMdd";

    private SimpleDateFormat sdf12 = new SimpleDateFormat(DATE_FORMAT_12);
    private SimpleDateFormat sdf10 = new SimpleDateFormat(DATE_FORMAT_10);
    private SimpleDateFormat sdf8 = new SimpleDateFormat(DATE_FORMAT_8);

    private String[] moduleNameArray = {"短信模块","邮件模块","容器模块","媒资模块","账户模块", "认证模块", "飘屏模块", "网关模块", "监控模块", "机顶盒mac模块", "redis模块", "apistore模块", "cloudweb模块"};

    public void persistLogCacheData2DB() throws ParseException {
        logger.info("定时任务：持久化redis分钟统计数据到mysql------>开始");
        ResultDO<List<ChannelMerchantDTO>> resultDO = channelService.queryAllChannelList();
        if (!resultDO.isSuccess()){
            logger.error("定时任务：持久化redis分钟统计数据到mysql---->失败，获取频道列表失败，失败原因：" + resultDO.getErrMsg());
            return;
        }
        final List<ChannelMerchantDTO> list = resultDO.getData();
        if (list == null || list.size() <= 0){
            logger.info("定时任务：持久化redis分钟统计数据到mysql---->没有频道列表数据");
            return;
        }
        Date currentDate = new Date();
        final String[] minuteDateTimeArray = PersistUtil.getPre4MinDateTimeStringArray(currentDate);
        final String[] hourDateTimeArray = PersistUtil.getPre2HourDateTimeStringArray(currentDate);
        final String[] dayDateTimeArray = PersistUtil.getPre2DayDateStringArray(currentDate);

        for (int i = 0; i < list.size(); i++) {
            ChannelMerchantDTO channelMerchantDTO = list.get(i);
            final Long merchantId = channelMerchantDTO.getMerchantId();
            final String channelNo = channelMerchantDTO.getChannelNo();
            new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < moduleNameArray.length; j++) {
                        String moduleName = moduleNameArray[j];
                        try {
                            for (int k = 0; k < minuteDateTimeArray.length; k++) {
                                doMinuteTask(minuteDateTimeArray[k], merchantId, channelNo, moduleName);
                            }
                            for (int k = 0; k < hourDateTimeArray.length; k++) {
                                doHourTask(hourDateTimeArray[k], merchantId, channelNo, moduleName);
                            }
                            for (int k = 0; k < dayDateTimeArray.length; k++) {
                                doDayTask(dayDateTimeArray[k], merchantId, channelNo, moduleName);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            logger.error("定时任务：数据批处理异常：" + e.getMessage());
                        }
                    }

                }
            }.run();
        }
    }

    private void doMinuteTask(String dateTimeString, Long merchantId, String channelNo, String moduleName) throws ParseException {
        Integer count = 0;
        String redisKey = RedisKeyUtil.jointRedisKeyByInfo(moduleName, merchantId + "", channelNo, dateTimeString);
        Object value = RedisUtil.get(redisKey);
        if (value != null){
            count = Integer.parseInt(value.toString());
        }
        logger.info(redisKey + "---------------------->" + count);
        LogModuleMinute logModuleMinute = logStatisticsManager.checkIsExistMinuteData(merchantId, channelNo, moduleName, dateTimeString);
        if (logModuleMinute == null){
            logModuleMinute = new LogModuleMinute();
            logModuleMinute.setModule(moduleName);
            logModuleMinute.setChannelNo(channelNo);
            logModuleMinute.setCount(count);
            logModuleMinute.setMerchantId(merchantId);
            logModuleMinute.setRecordTime(sdf12.parse(dateTimeString));
            try {
                logStatisticsManager.insertLogModuleMinute(logModuleMinute);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("定时任务：持久化redis分钟统计数据到mysql---->数据库操作异常：" + e.getMessage());
            }
        }else {
            if (count != logModuleMinute.getCount()){
                LogModuleMinute t_logModuleMinute = new LogModuleMinute();
                t_logModuleMinute.setId(logModuleMinute.getId());
                t_logModuleMinute.setCount(count);
                t_logModuleMinute.setVersion(logModuleMinute.getVersion());
                try {
                    logStatisticsManager.updateLogModuleMinute(t_logModuleMinute);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("定时任务：持久化redis分钟统计数据到mysql---->数据库操作异常：" + e.getMessage());
                }
            }
        }
    }

    private void doHourTask(String dateTimeString, Long merchantId, String channelNo, String moduleName) throws ParseException {
        Integer count = logStatisticsManager.countHourDataByCondition(merchantId, channelNo, moduleName, dateTimeString);
        LogModuleHour logModuleHour = logStatisticsManager.checkIsExistHourData(merchantId, channelNo, moduleName, dateTimeString);
        if (logModuleHour == null){
            logModuleHour = new LogModuleHour();
            logModuleHour.setModule(moduleName);
            logModuleHour.setChannelNo(channelNo);
            logModuleHour.setCount(count);
            logModuleHour.setMerchantId(merchantId);
            logModuleHour.setRecordTime(sdf10.parse(dateTimeString));
            try {
                logStatisticsManager.insertLogModuleHour(logModuleHour);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("定时任务：持久化redis小时统计数据到mysql---->数据库操作异常：" + e.getMessage());
            }
        }else {
            if (count != logModuleHour.getCount()){
                LogModuleHour t_logModuleHour = new LogModuleHour();
                t_logModuleHour.setId(logModuleHour.getId());
                t_logModuleHour.setCount(count);
                t_logModuleHour.setVersion(logModuleHour.getVersion());
                try {
                    logStatisticsManager.updateLogModuleHour(t_logModuleHour);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("定时任务：持久化redis小时统计数据到mysql---->数据库操作异常：" + e.getMessage());
                }
            }
        }
    }

    private void doDayTask(String dateTimeString, Long merchantId, String channelNo, String moduleName) throws ParseException {
        Integer count = logStatisticsManager.countDayDataByCondition(merchantId, channelNo, moduleName, dateTimeString);
        LogModuleDay logModuleDay = logStatisticsManager.checkIsExistDayData(merchantId, channelNo, moduleName, dateTimeString);
        if (logModuleDay == null){
            logModuleDay = new LogModuleDay();
            logModuleDay.setModule(moduleName);
            logModuleDay.setChannelNo(channelNo);
            logModuleDay.setCount(count);
            logModuleDay.setMerchantId(merchantId);
            logModuleDay.setRecordTime(sdf8.parse(dateTimeString));
            try {
                logStatisticsManager.insertLogModuleDay(logModuleDay);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("定时任务：持久化redis小时统计数据到mysql---->数据库操作异常：" + e.getMessage());
            }
        }else {
            if (count != logModuleDay.getCount()){
                LogModuleDay t_logModuleDay = new LogModuleDay();
                t_logModuleDay.setId(logModuleDay.getId());
                t_logModuleDay.setCount(count);
                t_logModuleDay.setVersion(logModuleDay.getVersion());
                try {
                    logStatisticsManager.updateLogModuleDay(t_logModuleDay);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("定时任务：持久化redis小时统计数据到mysql---->数据库操作异常：" + e.getMessage());
                }
            }
        }
    }
}
