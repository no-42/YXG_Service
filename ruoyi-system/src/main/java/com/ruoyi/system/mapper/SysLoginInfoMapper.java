package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.BaseMapper;
import com.ruoyi.system.domain.entity.SysLoginInfoEntity;
import com.ruoyi.system.domain.query.SysLoginInfoQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统访问日志情况信息 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysLoginInfoMapper extends BaseMapper<SysLoginInfoEntity> {
    /**
     * 新增系统登录日志
     *
     * @param info 访问日志对象
     */
    void insertLoginInfo(SysLoginInfoEntity info);

    /**
     * 查询系统登录日志集合
     *
     * @param query 查询信息
     * @return 登录记录集合
     */
    List<SysLoginInfoEntity> selectLoginInfoList(SysLoginInfoQuery query);

    /**
     * 批量删除系统登录日志
     *
     * @param infoIds 需要删除的登录日志ID
     * @return 结果
     */
    int deleteLoginInfoByIds(String[] infoIds);

    /**
     * 清空系统登录日志
     *
     * @return 结果
     */
    int cleanLoginInfo();
}
