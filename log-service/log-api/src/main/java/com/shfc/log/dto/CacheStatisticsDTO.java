package com.shfc.log.dto;

import java.io.Serializable;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/6 下午4:57.
 */
public class CacheStatisticsDTO implements Serializable {
    private Integer todayCunt;
    private Integer yesterdayCount;
    private Integer totalCount;

    public Integer getTodayCunt() {
        return todayCunt;
    }

    public void setTodayCunt(Integer todayCunt) {
        this.todayCunt = todayCunt;
    }

    public Integer getYesterdayCount() {
        return yesterdayCount;
    }

    public void setYesterdayCount(Integer yesterdayCount) {
        this.yesterdayCount = yesterdayCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
