package com.ruoyi.common.core.domain;

import com.ruoyi.common.utils.MybatisPlusUtils;

import java.util.List;

public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     * 根据查询条件查询列表
     *
     * @param query 查询条件
     * @return 列表
     */
    default List<T> selectListByQuery(BaseQuery query) {
        if (query == null) {
            return selectList(null);
        } else {
            return selectList(MybatisPlusUtils.buildQueryWrapper(query));
        }
    }
}
