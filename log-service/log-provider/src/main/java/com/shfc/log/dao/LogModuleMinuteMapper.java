package com.shfc.log.dao;

import com.shfc.log.domain.LogModuleMinute;
import com.shfc.mybatis.pagination.Page;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @Package: com.shfc.log.dao.LogModuleMinuteMapper.java
 * @Description: 
 * @Company: 上海房产
 * @Copyright: Copyright (c) 2016 
 * All right reserved.
 * Author lv bin
 * @date 2017/04/01 11:09
 * version v1.0.0
 */
@Repository
public interface LogModuleMinuteMapper {
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
    int insert(LogModuleMinute record);

    /**
     * @Description: 选择性插入数据库记录
     * @Title insertSelective
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int insertSelective(LogModuleMinute record);

    /**
     * @Description: 根据主键获取一条数据库记录
     * @Title selectByPrimaryKey
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param id
     * @return com.shfc.log.domain.LogModuleMinute
     * @throws []
     */
    LogModuleMinute selectByPrimaryKey(Long id);

    /**
     * @Description: 根据主键来更新对应数据库字段
     * @Title updateByPrimaryKeySelective
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKeySelective(LogModuleMinute record);

    /**
     * @Description: 根据主键来更新数据库记录
     * @Title updateByPrimaryKey
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @param record
     * @return int
     * @throws []
     */
    int updateByPrimaryKey(LogModuleMinute record);

    /**
     * @Description: 分页获取全部数据库记录
     * @Title selectByPage
     * @Author lv bin
     * @Date 2017/04/01 11:09
     * @return null
     * @throws []
     */
    List<LogModuleMinute> selectByPage(Page<LogModuleMinute> page);

    /**
     * 检查指定条件是否存在记录
     * @param params
     * @return
     */
    LogModuleMinute checkIsExistMinuteData(HashMap<String, Object> params);

    /**
     * 计算指定条件内小时统计数据
     * @param params
     * @return
     */
    Integer countHourDataByCondition(HashMap<String, Object> params);
}