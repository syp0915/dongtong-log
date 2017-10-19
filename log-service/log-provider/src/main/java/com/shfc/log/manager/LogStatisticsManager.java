package com.shfc.log.manager;

import com.shfc.log.dao.LogModuleDayMapper;
import com.shfc.log.dao.LogModuleHourMapper;
import com.shfc.log.dao.LogModuleMinuteMapper;
import com.shfc.log.domain.LogModuleDay;
import com.shfc.log.domain.LogModuleHour;
import com.shfc.log.domain.LogModuleMinute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/1 上午11:19.
 */
@Service
public class LogStatisticsManager {

    @Autowired(required = false)
    private LogModuleMinuteMapper logModuleMinuteMapper;

    @Autowired(required = false)
    private LogModuleHourMapper logModuleHourMapper;

    @Autowired(required = false)
    private LogModuleDayMapper logModuleDayMapper;

    public LogModuleMinute checkIsExistMinuteData(Long merchantId, String channelNo, String moduleName, String dateTimeString){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        params.put("moduleName", moduleName);
        params.put("dateTimeString", dateTimeString);
        return logModuleMinuteMapper.checkIsExistMinuteData(params);
    }

    public int insertLogModuleMinute(LogModuleMinute logModuleMinute){
        return logModuleMinuteMapper.insert(logModuleMinute);
    }

    public int updateLogModuleMinute(LogModuleMinute logModuleMinute){
        return logModuleMinuteMapper.updateByPrimaryKeySelective(logModuleMinute);
    }

    public LogModuleHour checkIsExistHourData(Long merchantId, String channelNo, String moduleName, String dateTimeString){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        params.put("moduleName", moduleName);
        params.put("dateTimeString", dateTimeString);
        return logModuleHourMapper.checkIsExistHourData(params);
    }

    public int insertLogModuleHour(LogModuleHour logModuleHour){
        return logModuleHourMapper.insert(logModuleHour);
    }

    public int updateLogModuleHour(LogModuleHour logModuleHour){
        return logModuleHourMapper.updateByPrimaryKeySelective(logModuleHour);
    }

    public LogModuleDay checkIsExistDayData(Long merchantId, String channelNo, String moduleName, String dateTimeString){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        params.put("moduleName", moduleName);
        params.put("dateTimeString", dateTimeString);
        return logModuleDayMapper.checkIsExistDayData(params);
    }

    public int insertLogModuleDay(LogModuleDay logModuleDay){
        return logModuleDayMapper.insert(logModuleDay);
    }

    public int updateLogModuleDay(LogModuleDay logModuleDay){
        return logModuleDayMapper.updateByPrimaryKeySelective(logModuleDay);
    }

    /**
     *
     * @param merchantId
     * @param channelNo
     * @param moduleName
     * @param dateTimeString yyyyMMddHH格式
     * @return
     */
    public int countHourDataByCondition(Long merchantId, String channelNo, String moduleName, String dateTimeString){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        params.put("moduleName", moduleName);
        params.put("dateTimeString", dateTimeString);
        Integer result = logModuleMinuteMapper.countHourDataByCondition(params);
        return result == null? 0:result;
    }

    /**
     *
     * @param merchantId
     * @param channelNo
     * @param moduleName
     * @param dateTimeString yyyyMMdd格式
     * @return
     */
    public int countDayDataByCondition(Long merchantId, String channelNo, String moduleName, String dateTimeString){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        params.put("moduleName", moduleName);
        params.put("dateTimeString", dateTimeString);
        Integer result = logModuleHourMapper.countDayDataByCondition(params);
        return result == null?0:result;
    }

    public List<Integer> getPre2DayRedisStatisticsData(String channelNo, Long merchantId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        String todayString = sdf.format(new Date());
        String yeatDayString = sdf.format(calendar.getTime());
        params.put("yestdayDate", yeatDayString);
        params.put("todayDate", todayString);
        List<HashMap<String, Object>> list = logModuleDayMapper.getPre2DayRedisStatisticsData(params);
        List<Integer> dataList = new ArrayList<Integer>();
        dataList.add(0);
        dataList.add(0);
        if (list != null && list.size() > 0){
            for (HashMap<String, Object> map : list) {
                if (todayString.equals(map.get("recordDay").toString())){
                    dataList.set(0, Integer.parseInt(map.get("totalCount").toString()));
                }
                if (yeatDayString.equals(map.get("recordDay").toString())){
                    dataList.set(1, Integer.parseInt(map.get("totalCount").toString()));
                }
            }
        }
        return dataList;
    }

    public Integer getRedisTotalUseCount(String channelNo, Long merchantId){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("merchantId", merchantId);
        params.put("channelNo", channelNo);
        Integer count = logModuleDayMapper.getRedisTotalUseCount(params);
        if (count == null){
            count = 0;
        }
        return count;
    }
}
