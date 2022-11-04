package com.ruoyi.market.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.GoodsCategoryMapper;
import com.ruoyi.market.domain.entity.GoodsCategoryEntity;
import com.ruoyi.market.domain.query.GoodsCategoryQuery;
import com.ruoyi.market.service.IGoodsCategoryService;

/**
 * 商品分类Service业务层处理
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {
    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    
    @Override
    public GoodsCategoryEntity selectGoodsCategoryById(String id) {
        return goodsCategoryMapper.selectById(id);
    }
    
    @Override
    public List<GoodsCategoryEntity> selectGoodsCategoryList(GoodsCategoryQuery query) {
        return goodsCategoryMapper.selectListByQuery(query);
    }



    @Override
    public int insertGoodsCategory(GoodsCategoryEntity goodsCategory) {
        return goodsCategoryMapper.insert(goodsCategory);
    }
    
    @Override
    public int updateGoodsCategory(GoodsCategoryEntity goodsCategory) {
        return goodsCategoryMapper.updateById(goodsCategory);
    }
    
    @Override
    public int deleteGoodsCategoryByIds(String[] ids) {
        return goodsCategoryMapper.deleteBatchIds(Arrays.asList(ids));
    }
    
    @Override
    public int deleteGoodsCategoryById(String id) {
        return goodsCategoryMapper.deleteById(id);
    }
}
