package com.ruoyi.market.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.GoodsSpecMapper;
import com.ruoyi.market.domain.entity.GoodsSpecEntity;
import com.ruoyi.market.domain.query.GoodsSpecQuery;
import com.ruoyi.market.service.IGoodsSpecService;

/**
 * 商品规格Service业务层处理
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Service
public class GoodsSpecServiceImpl implements IGoodsSpecService {
    @Autowired
    private GoodsSpecMapper goodsSpecMapper;
    
    @Override
    public GoodsSpecEntity selectGoodsSpecById(String id) {
        return goodsSpecMapper.selectById(id);
    }
    
    @Override
    public List<GoodsSpecEntity> selectGoodsSpecList(GoodsSpecQuery query) {
        return goodsSpecMapper.selectListByQuery(query);
    }



    @Override
    public int insertGoodsSpec(GoodsSpecEntity goodsSpec) {
        return goodsSpecMapper.insert(goodsSpec);
    }
    
    @Override
    public int updateGoodsSpec(GoodsSpecEntity goodsSpec) {
        return goodsSpecMapper.updateById(goodsSpec);
    }
    
    @Override
    public int deleteGoodsSpecByIds(String[] ids) {
        return goodsSpecMapper.deleteBatchIds(Arrays.asList(ids));
    }
    
    @Override
    public int deleteGoodsSpecById(String id) {
        return goodsSpecMapper.deleteById(id);
    }
}
