package com.ruoyi.quartz.mapper;

import java.util.List;

import com.ruoyi.quartz.domain.entity.SysJobLogEntity;
import com.ruoyi.quartz.domain.query.SysJobLogQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调度任务日志信息 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysJobLogMapper {
    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param query 查询信息
     * @return 调度任务日志集合
     */
    List<SysJobLogEntity> selectJobLogList(SysJobLogQuery query);

    /**
     * 查询所有调度任务日志
     *
     * @return 调度任务日志列表
     */
    List<SysJobLogEntity> selectJobLogAll();

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    SysJobLogEntity selectJobLogById(String jobLogId);

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     * @return 结果
     */
    int insertJobLog(SysJobLogEntity jobLog);

    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    int deleteJobLogByIds(String[] logIds);

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     * @return 结果
     */
    int deleteJobLogById(String jobId);

    /**
     * 清空任务日志
     */
    void cleanJobLog();
}
