package com.shfc.storm.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/3/31 下午9:22.
 */
public class TVLogBean implements Serializable {
    private String sessionId;
    private String channelNo;
    private String mac;
    private Date visitDateTime;
    private Long visitTimeMills;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(Date visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    public Long getVisitTimeMills() {
        return visitTimeMills;
    }

    public void setVisitTimeMills(Long visitTimeMills) {
        this.visitTimeMills = visitTimeMills;
    }
}
