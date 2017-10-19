package com.shfc.log.domain;

import com.shfc.common.httpbean.BaseBean;
import java.util.Date;

/**
 * @Package: com.shfc.log.domain.LogModuleDay.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author lv bin
 * @date 2017/04/01 11:09
 * version v1.0.0
 */
public class LogModuleDay extends BaseBean {
    /**
     * 天数
     */
    private Integer day;

    /**
     * 访问次数
     */
    private Integer count;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 频道号
     */
    private String channelNo;

    /**
     * 模块名称
     */
    private String module;

    /**
     * 类名
     */
    private String clazz;

    /**
     * 方法名
     */
    private String method;

    /**
     * 平均执行时长，单位ms
     */
    private Long avgPeriod;

    /**
     * 统计记录时间
     */
    private Date recordTime;

    private Long failCount;

    /**
     * 执行成功标志0-成功 1-失败
     */
    private Long successCount;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 获取天数
     *
     * @return day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * 设置天数
     *
     * @param day
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * 获取访问次数
     *
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置访问次数
     *
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取商户id
     *
     * @return merchant_id
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商户id
     *
     * @param merchantId
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取频道号
     *
     * @return channel_no
     */
    public String getChannelNo() {
        return channelNo;
    }

    /**
     * 设置频道号
     *
     * @param channelNo
     */
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    /**
     * 获取模块名称
     *
     * @return module
     */
    public String getModule() {
        return module;
    }

    /**
     * 设置模块名称
     *
     * @param module
     */
    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    /**
     * 获取类名
     *
     * @return clazz
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * 设置类名
     *
     * @param clazz
     */
    public void setClazz(String clazz) {
        this.clazz = clazz == null ? null : clazz.trim();
    }

    /**
     * 获取方法名
     *
     * @return method
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置方法名
     *
     * @param method
     */
    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    /**
     * 获取平均执行时长，单位ms
     *
     * @return avg_period
     */
    public Long getAvgPeriod() {
        return avgPeriod;
    }

    /**
     * 设置平均执行时长，单位ms
     *
     * @param avgPeriod
     */
    public void setAvgPeriod(Long avgPeriod) {
        this.avgPeriod = avgPeriod;
    }

    /**
     * 获取统计记录时间
     *
     * @return record_time
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * 设置统计记录时间
     *
     * @param recordTime
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    /**
     * @return fail_count
     */
    public Long getFailCount() {
        return failCount;
    }

    /**
     * @param failCount
     */
    public void setFailCount(Long failCount) {
        this.failCount = failCount;
    }

    /**
     * 获取执行成功标志0-成功 1-失败
     *
     * @return success_count
     */
    public Long getSuccessCount() {
        return successCount;
    }

    /**
     * 设置执行成功标志0-成功 1-失败
     *
     * @param successCount
     */
    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    /**
     * 获取创建者
     *
     * @return creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * 设置创建者
     *
     * @param creator
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }
}