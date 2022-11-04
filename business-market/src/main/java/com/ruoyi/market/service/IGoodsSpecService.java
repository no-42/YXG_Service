package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.entity.GoodsSpecEntity;
import com.ruoyi.market.domain.query.GoodsSpecQuery;

/**
 * 商品规格Service接口
 * 
 * @author thetbw
 * @date 2022-11-04
 */
public interface IGoodsSpecService {
    /**
     * 查询商品规格
     * 
     * @param id 商品规格主键
     * @return 商品规格
     */
    GoodsSpecEntity selectGoodsSpecById(String id);

    /**
     * 查询商品规格列表
     * 
     * @param query 查询条件
     * @return 商品规格集合
     */
    List<GoodsSpecEntity> selectGoodsSpecList(GoodsSpecQuery query);

    /**
     * 新增商品规格
     * 
     * @param goodsSpec 商品规格
     * @return 结果
     */
    int insertGoodsSpec(GoodsSpecEntity goodsSpec);

    /**
     * 修改商品规格
     * 
     * @param goodsSpec 商品规格
     * @return 结果
     */
    int updateGoodsSpec(GoodsSpecEntity goodsSpec);

    /**
     * 批量删除商品规格
     * 
     * @param ids 需要删除的商品规格主键集合
     * @return 结果
     */
    int deleteGoodsSpecByIds(String[] ids);

    /**
     * 删除商品规格信息
     * 
     * @param id 商品规格主键
     * @return 结果
     */
    int deleteGoodsSpecById(String id);
}
