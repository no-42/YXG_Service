package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.query.SysLoginInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.entity.SysLoginInfoEntity;
import com.ruoyi.system.mapper.SysLoginInfoMapper;
import com.ruoyi.system.service.ISysLoginInfoService;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysLoginInfoServiceImpl implements ISysLoginInfoService {

    @Autowired
    private SysLoginInfoMapper logininforMapper;

    @Override
    public void insertLoginInfo(SysLoginInfoEntity info) {
        logininforMapper.insertLoginInfo(info);
    }

    @Override
    public List<SysLoginInfoEntity> selectLoginInfoList(SysLoginInfoQuery query) {
        return logininforMapper.selectLoginInfoList(query);
    }


    @Override
    public int deleteLoginInfoByIds(String[] infoIds) {
        return logininforMapper.deleteLoginInfoByIds(infoIds);
    }

    @Override
    public void cleanLoginInfo() {
        logininforMapper.cleanLoginInfo();
    }
}
