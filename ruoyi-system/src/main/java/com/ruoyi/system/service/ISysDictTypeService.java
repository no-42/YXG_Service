package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.common.core.entity.SysDictDataEntity;
import com.ruoyi.common.core.entity.SysDictTypeEntity;
import com.ruoyi.system.domain.query.SysDictTypeQuery;

/**
 * 字典 业务层
 *
 * @author ruoyi
 */
public interface ISysDictTypeService {
    /**
     * 根据条件分页查询字典类型
     *
     * @param query 查询条件
     * @return 字典类型集合信息
     */
    List<SysDictTypeEntity> selectDictTypeList(SysDictTypeQuery query);

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    List<SysDictTypeEntity> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    List<SysDictDataEntity> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    SysDictTypeEntity selectDictTypeById(String dictId);

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    SysDictTypeEntity selectDictTypeByType(String dictType);

    /**
     * 批量删除字典信息
     *
     * @param dictIds 需要删除的字典ID
     */
    void deleteDictTypeByIds(String[] dictIds);

    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(SysDictTypeEntity dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(SysDictTypeEntity dictType);

    /**
     * 校验字典类型称是否唯一
     *
     * @param dictType 字典类型
     * @return 结果
     */
    String checkDictTypeUnique(SysDictTypeEntity dictType);
}
