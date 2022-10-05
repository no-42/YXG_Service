package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.domain.entity.SysDictDataEntity;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.service.ISysDictDataService;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictDataEntity> selectDictDataList(SysDictDataEntity dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param id 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictDataEntity selectDictDataById(String id) {
        return dictDataMapper.selectDictDataById(id);
    }

    /**
     * 批量删除字典数据信息
     *
     * @param dictIds 需要删除的字典数据ID
     */
    @Override
    public void deleteDictDataByIds(String[] dictIds) {
        for (String dictId : dictIds) {
            SysDictDataEntity data = selectDictDataById(dictId);
            dictDataMapper.deleteDictDataById(dictId);
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(data.getType());
            DictUtils.setDictCache(data.getType(), dictDatas);
        }
    }

    /**
     * 新增保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictDataEntity data) {
        int row = dictDataMapper.insertDictData(data);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(data.getType());
            DictUtils.setDictCache(data.getType(), dictDatas);
        }
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param data 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictDataEntity data) {
        int row = dictDataMapper.updateDictData(data);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(data.getType());
            DictUtils.setDictCache(data.getType(), dictDatas);
        }
        return row;
    }
}
