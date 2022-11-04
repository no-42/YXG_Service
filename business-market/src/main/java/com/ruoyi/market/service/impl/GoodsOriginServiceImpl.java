package com.ruoyi.market.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.GoodsOriginMapper;
import com.ruoyi.market.domain.entity.GoodsOriginEntity;
import com.ruoyi.market.domain.query.GoodsOriginQuery;
import com.ruoyi.market.service.IGoodsOriginService;

/**
 * 商品产地Service业务层处理
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Service
public class GoodsOriginServiceImpl implements IGoodsOriginService {
    @Autowired
    private GoodsOriginMapper goodsOriginMapper;
    
    @Override
    public GoodsOriginEntity selectGoodsOriginById(String id) {
        return goodsOriginMapper.selectById(id);
    }
    
    @Override
    public List<GoodsOriginEntity> selectGoodsOriginList(GoodsOriginQuery query) {
        return goodsOriginMapper.selectListByQuery(query);
    }



    @Override
    public int insertGoodsOrigin(GoodsOriginEntity goodsOrigin) {
        return goodsOriginMapper.insert(goodsOrigin);
    }
    
    @Override
    public int updateGoodsOrigin(GoodsOriginEntity goodsOrigin) {
        return goodsOriginMapper.updateById(goodsOrigin);
    }
    
    @Override
    public int deleteGoodsOriginByIds(String[] ids) {
        return goodsOriginMapper.deleteBatchIds(Arrays.asList(ids));
    }
    
    @Override
    public int deleteGoodsOriginById(String id) {
        return goodsOriginMapper.deleteById(id);
    }
}
