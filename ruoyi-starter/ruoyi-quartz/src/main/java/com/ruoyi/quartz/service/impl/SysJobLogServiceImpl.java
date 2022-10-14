package com.ruoyi.quartz.service.impl;

import java.util.List;

import com.ruoyi.quartz.domain.query.SysJobLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.quartz.domain.entity.SysJobLogEntity;
import com.ruoyi.quartz.mapper.SysJobLogMapper;
import com.ruoyi.quartz.service.ISysJobLogService;

/**
 * 定时任务调度日志信息 服务层
 *
 * @author ruoyi
 */
@Service
public class SysJobLogServiceImpl implements ISysJobLogService {
    @Autowired
    private SysJobLogMapper jobLogMapper;
    
    @Override
    public List<SysJobLogEntity> selectJobLogList(SysJobLogQuery query) {
        return jobLogMapper.selectJobLogList(query);
    }
    
    @Override
    public SysJobLogEntity selectJobLogById(String jobLogId) {
        return jobLogMapper.selectJobLogById(jobLogId);
    }
    
    @Override
    public void addJobLog(SysJobLogEntity jobLog) {
        jobLogMapper.insertJobLog(jobLog);
    }
    
    @Override
    public int deleteJobLogByIds(String[] logIds) {
        return jobLogMapper.deleteJobLogByIds(logIds);
    }
    
    @Override
    public int deleteJobLogById(String jobId) {
        return jobLogMapper.deleteJobLogById(jobId);
    }
    
    @Override
    public void cleanJobLog() {
        jobLogMapper.cleanJobLog();
    }
}
