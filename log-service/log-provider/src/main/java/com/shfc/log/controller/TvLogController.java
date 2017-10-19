package com.shfc.log.controller;

import com.shfc.log.LogFileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright:Copyright (c) 2017
 * Company:东方金融-上海房产
 *
 * @author ljgllxyz
 * @version V1.0
 * @date 2017/4/10 下午6:19.
 */
@RequestMapping("/tvbox")
@RestController
public class TvLogController {

    LogFileUtils logFileUtils = LogFileUtils.getInstance("tvbox");

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public void recvTVLog(HttpServletRequest request){
        logFileUtils.LOG_PATH = "/tmp/log/tvbox/";
        String channelNo = request.getParameter("chn");
        String mac = request.getParameter("mac");
        String sessionId = request.getParameter("si");
        if (StringUtils.isNotBlank(channelNo) && StringUtils.isNotBlank(mac) && StringUtils.isNotBlank(sessionId)){
            logFileUtils.info(channelNo, mac, sessionId);
        }
    }
}
