package com.shfc.log.service;

import com.shfc.common.result.ResultDO;
import com.shfc.log.dto.CacheStatisticsDTO;
import com.shfc.log.manager.LogStatisticsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/1 上午11:06.
 */
@Service
public class LogStatisticsServiceImpl implements LogStatisticsService {

    @Autowired(required = false)
    private LogStatisticsManager logStatisticsManager;

    /**
     * 获取用户今天、昨天、总共调用次数的redis使用统计
     *
     * @param channelNo
     * @param merchantId
     * @return
     */
    @Override
    public ResultDO<CacheStatisticsDTO> getChannelRedisUseCount(String channelNo, Long merchantId) {
        ResultDO<CacheStatisticsDTO> resultDO = new ResultDO<CacheStatisticsDTO>();
        List<Integer> dataList = null;
        Integer totalCount = 0;
        try {
            dataList = logStatisticsManager.getPre2DayRedisStatisticsData(channelNo, merchantId);
            totalCount = logStatisticsManager.getRedisTotalUseCount(channelNo, merchantId);
        } catch (Exception e) {
            e.printStackTrace();
            resultDO.setSuccess(false);
            resultDO.setErrCode(1);
            resultDO.setErrMsg("数据库异常");
            return resultDO;
        }
        CacheStatisticsDTO cacheStatisticsDTO = new CacheStatisticsDTO();
        cacheStatisticsDTO.setTodayCunt(dataList.get(0) == null?0:dataList.get(0));
        cacheStatisticsDTO.setYesterdayCount(dataList.get(1) == null?0:dataList.get(1));
        cacheStatisticsDTO.setTotalCount(totalCount);
        resultDO.setData(cacheStatisticsDTO);
        resultDO.setSuccess(true);
        resultDO.setErrCode(0);
        resultDO.setErrMsg("成功");
        return resultDO;
    }
}
