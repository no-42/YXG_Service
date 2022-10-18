package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.common.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.core.entity.SysDictDataEntity;
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


    @Override
    public List<SysDictDataEntity> selectDictDataList(SysDictDataQuery query) {
        return dictDataMapper.selectDictDataList(query);
    }

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        return dictDataMapper.selectDictLabel(dictType, dictValue);
    }

    @Override
    public SysDictDataEntity selectDictDataById(String id) {
        return dictDataMapper.selectDictDataById(id);
    }

    @Override
    public void deleteDictDataByIds(String[] dictIds) {
        for (String dictId : dictIds) {
            SysDictDataEntity data = selectDictDataById(dictId);
            dictDataMapper.deleteDictDataById(dictId);
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(data.getType());
            DictUtils.setDictCache(data.getType(), dictDatas);
        }
    }

    @Override
    public int insertDictData(SysDictDataEntity data) {
        int row = dictDataMapper.insertDictData(data);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(data.getType());
            DictUtils.setDictCache(data.getType(), dictDatas);
        }
        return row;
    }
    
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
