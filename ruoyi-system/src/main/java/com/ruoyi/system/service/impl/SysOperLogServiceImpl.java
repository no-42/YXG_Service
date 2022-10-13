package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.query.SysOperLogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.entity.SysOperLogEntity;
import com.ruoyi.system.mapper.SysOperLogMapper;
import com.ruoyi.system.service.ISysOperLogService;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {
    @Autowired
    private SysOperLogMapper operLogMapper;
    
    @Override
    public void insertOperlog(SysOperLogEntity operLog) {
        operLogMapper.insertOperlog(operLog);
    }
    
    @Override
    public List<SysOperLogEntity> selectOperLogList(SysOperLogQuery query) {
        return operLogMapper.selectOperLogList(query);
    }
    
    @Override
    public int deleteOperLogByIds(String[] operIds) {
        return operLogMapper.deleteOperLogByIds(operIds);
    }
    
    @Override
    public SysOperLogEntity selectOperLogById(String operId) {
        return operLogMapper.selectOperLogById(operId);
    }
    
    @Override
    public void cleanOperLog() {
        operLogMapper.cleanOperLog();
    }
}
