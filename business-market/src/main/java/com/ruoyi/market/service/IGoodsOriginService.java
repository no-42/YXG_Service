package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.entity.GoodsOriginEntity;
import com.ruoyi.market.domain.query.GoodsOriginQuery;

/**
 * 商品产地Service接口
 * 
 * @author thetbw
 * @date 2022-11-04
 */
public interface IGoodsOriginService {
    /**
     * 查询商品产地
     * 
     * @param id 商品产地主键
     * @return 商品产地
     */
    GoodsOriginEntity selectGoodsOriginById(String id);

    /**
     * 查询商品产地列表
     * 
     * @param query 查询条件
     * @return 商品产地集合
     */
    List<GoodsOriginEntity> selectGoodsOriginList(GoodsOriginQuery query);

    /**
     * 新增商品产地
     * 
     * @param goodsOrigin 商品产地
     * @return 结果
     */
    int insertGoodsOrigin(GoodsOriginEntity goodsOrigin);

    /**
     * 修改商品产地
     * 
     * @param goodsOrigin 商品产地
     * @return 结果
     */
    int updateGoodsOrigin(GoodsOriginEntity goodsOrigin);

    /**
     * 批量删除商品产地
     * 
     * @param ids 需要删除的商品产地主键集合
     * @return 结果
     */
    int deleteGoodsOriginByIds(String[] ids);

    /**
     * 删除商品产地信息
     * 
     * @param id 商品产地主键
     * @return 结果
     */
    int deleteGoodsOriginById(String id);
}
