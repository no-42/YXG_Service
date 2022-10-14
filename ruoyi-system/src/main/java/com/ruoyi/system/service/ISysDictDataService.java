package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.entity.SysDictDataEntity;
import com.ruoyi.system.domain.query.SysDictDataQuery;

/**
 * 字典 业务层
 *
 * @author ruoyi
 */
public interface ISysDictDataService {
    /**
     * 根据条件分页查询字典数据
     *
     * @param query 查询信息
     * @return 字典数据集合信息
     */
    List<SysDictDataEntity> selectDictDataList(SysDictDataQuery query);

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    String selectDictLabel(String dictType, String dictValue);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictId 字典数据ID
     * @return 字典数据
     */
    SysDictDataEntity selectDictDataById(String dictId);

    /**
     * 批量删除字典数据信息
     *
     * @param dictIds 需要删除的字典数据ID
     */
    void deleteDictDataByIds(String[] dictIds);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(SysDictDataEntity dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int updateDictData(SysDictDataEntity dictData);
}
