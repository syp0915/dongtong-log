package com.shfc.log.dao;

import com.shfc.log.domain.LogModuleDay;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.shfc.log.dao.LogModuleDayMapper.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author lv bin
 * @date 2017/04/01 11:09
 * version v1.0.0
 */
@Repository
public interface LogModuleDayMapper {
    /**
     * @Description: 根据主键删除数据库的记录
     * @Title deleteByPrimaryKey
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param id
     * @return int
     * @throws []
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @Description: 插入数据库记录
     * @Title insert
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int insert(LogModuleDay record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(LogModuleDay record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param id
     * @return com.shfc.log.domain.LogModuleDay
     * @throws []
     */
    LogModuleDay selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(LogModuleDay record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(LogModuleDay record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @return null
     * @throws []
     */
    List<LogModuleDay> selectByPage(Page<LogModuleDay> page);

    /**
     * 检查指定条件是否存在记录
     * @param params
     * @return
     */
    LogModuleDay checkIsExistDayData(HashMap<String, Object> params);

    /**
     * 获取用户今天和昨天的redis使用统计
     * @param params
     * @return
     */
    List<HashMap<String, Object>> getPre2DayRedisStatisticsData(HashMap<String, Object> params);

    /**
     * 获取指定商户指定频道redis总调用次数
     * @param params
     * @return
     */
    Integer getRedisTotalUseCount(HashMap<String, Object> params);
}