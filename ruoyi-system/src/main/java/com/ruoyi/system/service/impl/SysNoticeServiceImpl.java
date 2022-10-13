package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.query.SysNoticeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.entity.SysNoticeEntity;
import com.ruoyi.system.mapper.SysNoticeMapper;
import com.ruoyi.system.service.ISysNoticeService;

/**
 * 公告 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {
    @Autowired
    private SysNoticeMapper noticeMapper;

    @Override
    public SysNoticeEntity selectNoticeById(String noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    @Override
    public List<SysNoticeEntity> selectNoticeList(SysNoticeQuery query) {
        return noticeMapper.selectNoticeList(query);
    }

    @Override
    public int insertNotice(SysNoticeEntity notice) {
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public int updateNotice(SysNoticeEntity notice) {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int deleteNoticeById(String noticeId) {
        return noticeMapper.deleteNoticeById(noticeId);
    }
    
    @Override
    public int deleteNoticeByIds(String[] noticeIds) {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }
}
