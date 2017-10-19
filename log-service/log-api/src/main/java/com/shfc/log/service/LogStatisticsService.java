package com.shfc.log.service;

import com.shfc.common.result.ResultDO;
import com.shfc.log.dto.CacheStatisticsDTO;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/1 上午11:04.
 */
public interface LogStatisticsService {
    /**
     * 获取用户今天和昨天的redis使用统计
     * @param channelNo
     * @param merchantId
     * @return
     */
    public ResultDO<CacheStatisticsDTO> getChannelRedisUseCount(String channelNo, Long merchantId);
}
