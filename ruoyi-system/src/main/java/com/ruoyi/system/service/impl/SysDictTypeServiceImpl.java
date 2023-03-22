package com.ruoyi.system.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.domain.query.SysDictTypeQuery;
import com.ruoyi.common.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.entity.SysDictDataEntity;
import com.ruoyi.common.core.entity.SysDictTypeEntity;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysDictTypeMapper;
import com.ruoyi.system.service.ISysDictTypeService;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
    @Autowired
    private SysDictTypeMapper dictTypeMapper;

    @Autowired
    private SysDictDataMapper dictDataMapper;

    /**
     * 项目启动时，初始化字典到缓存
     */
    @PostConstruct
    public void init() {
        loadingDictCache();
    }

    @Override
    public List<SysDictTypeEntity> selectDictTypeList(SysDictTypeQuery query) {
        return dictTypeMapper.selectDictTypeList(query);
    }


    @Override
    public List<SysDictTypeEntity> selectDictTypeAll() {
        return dictTypeMapper.selectDictTypeAll();
    }

    @Override
    public List<SysDictDataEntity> selectDictDataByType(String dictType) {
        List<SysDictDataEntity> dictDatas = DictUtils.getDict(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            return dictDatas;
        }
        dictDatas = dictDataMapper.selectDictDataByType(dictType);
        if (StringUtils.isNotEmpty(dictDatas)) {
            DictUtils.setDictCache(dictType, dictDatas);
            return dictDatas;
        }
        return null;
    }

    @Override
    public SysDictTypeEntity selectDictTypeById(String dictId) {
        return dictTypeMapper.selectDictTypeById(dictId);
    }

    @Override
    public SysDictTypeEntity selectDictTypeByType(String dictType) {
        return dictTypeMapper.selectDictTypeByType(dictType);
    }

    @Override
    public void deleteDictTypeByIds(String[] dictIds) {
        for (String dictId : dictIds) {
            SysDictTypeEntity dictType = selectDictTypeById(dictId);
            if (dictDataMapper.countDictDataByType(dictType.getType()) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", dictType.getName()));
            }
            dictTypeMapper.deleteDictTypeById(dictId);
            DictUtils.removeDictCache(dictType.getType());
        }
    }

    @Override
    public void loadingDictCache() {
        SysDictDataQuery dictData = new SysDictDataQuery();
        dictData.setEnable(true);
        Map<String, List<SysDictDataEntity>> dictDataMap = dictDataMapper.selectDictDataList(dictData).stream().collect(Collectors.groupingBy(SysDictDataEntity::getType));
        for (Map.Entry<String, List<SysDictDataEntity>> entry : dictDataMap.entrySet()) {
            DictUtils.setDictCache(entry.getKey(), entry.getValue().stream().sorted(Comparator.comparing(SysDictDataEntity::getSort)).collect(Collectors.toList()));
        }
    }

    @Override
    public void clearDictCache() {
        DictUtils.clearDictCache();
    }

    @Override
    public void resetDictCache() {
        clearDictCache();
        loadingDictCache();
    }


    @Override
    public int insertDictType(SysDictTypeEntity dict) {
        int row = dictTypeMapper.insertDictType(dict);
        if (row > 0) {
            DictUtils.setDictCache(dict.getType(), null);
        }
        return row;
    }

    @Override
    @Transactional
    public int updateDictType(SysDictTypeEntity dict) {
        SysDictTypeEntity oldDict = dictTypeMapper.selectDictTypeById(dict.getId());
        dictDataMapper.updateDictDataType(oldDict.getType(), dict.getType());
        int row = dictTypeMapper.updateDictType(dict);
        if (row > 0) {
            List<SysDictDataEntity> dictDatas = dictDataMapper.selectDictDataByType(dict.getType());
            DictUtils.setDictCache(dict.getType(), dictDatas);
        }
        return row;
    }
    
    @Override
    public String checkDictTypeUnique(SysDictTypeEntity dict) {
        String dictId = StringUtils.isNotEmpty(dict.getId()) ? dict.getId() : null;
        SysDictTypeEntity dictType = dictTypeMapper.checkDictTypeUnique(dict.getType());
        if (StringUtils.isNotNull(dictType) && !dictType.getId().equals(dictId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
