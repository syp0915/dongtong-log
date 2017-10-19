package com.shfc.log.manager;

import com.shfc.log.dao.LogModuleDayMapper;
import com.shfc.log.dao.LogModuleHourMapper;
import com.shfc.log.dao.LogModuleMinuteMapper;
import com.shfc.log.domain.LogModuleDay;
import com.shfc.log.domain.LogModuleHour;
import com.shfc.log.domain.LogModuleMinute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/1 上午11:06.
 */
@Service
public class LogManager {

    @Autowired(required = false)
    private LogModuleDayMapper logModuleDayMapper;

    @Autowired(required = false)
    private LogModuleHourMapper logModuleHourMapper;

    @Autowired(required = false)
    private LogModuleMinuteMapper logModuleMinuteMapper;

    public void saveModuleMinuteLog(LogModuleMinute logModuleMinute){
        logModuleMinuteMapper.insertSelective(logModuleMinute);
    }

    public void saveModuleHourLog(LogModuleHour logModuleHour){
        logModuleHourMapper.insertSelective(logModuleHour);
    }

    public void saveModuleDayLog(LogModuleDay logModuleDay){
        logModuleDayMapper.insertSelective(logModuleDay);
    }

}
