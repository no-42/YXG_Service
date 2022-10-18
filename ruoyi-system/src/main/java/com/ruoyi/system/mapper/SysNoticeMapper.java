package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.BaseMapper;
import com.ruoyi.system.domain.entity.SysNoticeEntity;
import com.ruoyi.system.domain.query.SysNoticeQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通知公告表 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNoticeEntity> {
    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysNoticeEntity selectNoticeById(String noticeId);

    /**
     * 查询公告列表
     *
     * @param query query
     * @return 公告集合
     */
    List<SysNoticeEntity> selectNoticeList(SysNoticeQuery query);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int insertNotice(SysNoticeEntity notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int updateNotice(SysNoticeEntity notice);

    /**
     * 批量删除公告
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    int deleteNoticeById(String noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    int deleteNoticeByIds(String[] noticeIds);
}
