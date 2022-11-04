package com.ruoyi.market.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.GoodsSupplierMapper;
import com.ruoyi.market.domain.entity.GoodsSupplierEntity;
import com.ruoyi.market.domain.query.GoodsSupplierQuery;
import com.ruoyi.market.service.IGoodsSupplierService;

/**
 * 商品供应商Service业务层处理
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Service
public class GoodsSupplierServiceImpl implements IGoodsSupplierService {
    @Autowired
    private GoodsSupplierMapper goodsSupplierMapper;
    
    @Override
    public GoodsSupplierEntity selectGoodsSupplierById(String id) {
        return goodsSupplierMapper.selectById(id);
    }
    
    @Override
    public List<GoodsSupplierEntity> selectGoodsSupplierList(GoodsSupplierQuery query) {
        return goodsSupplierMapper.selectListByQuery(query);
    }



    @Override
    public int insertGoodsSupplier(GoodsSupplierEntity goodsSupplier) {
        return goodsSupplierMapper.insert(goodsSupplier);
    }
    
    @Override
    public int updateGoodsSupplier(GoodsSupplierEntity goodsSupplier) {
        return goodsSupplierMapper.updateById(goodsSupplier);
    }
    
    @Override
    public int deleteGoodsSupplierByIds(String[] ids) {
        return goodsSupplierMapper.deleteBatchIds(Arrays.asList(ids));
    }
    
    @Override
    public int deleteGoodsSupplierById(String id) {
        return goodsSupplierMapper.deleteById(id);
    }
}
