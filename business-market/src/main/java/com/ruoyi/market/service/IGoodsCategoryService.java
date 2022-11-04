package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.entity.GoodsCategoryEntity;
import com.ruoyi.market.domain.query.GoodsCategoryQuery;

/**
 * 商品分类Service接口
 * 
 * @author thetbw
 * @date 2022-11-04
 */
public interface IGoodsCategoryService {
    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    GoodsCategoryEntity selectGoodsCategoryById(String id);

    /**
     * 查询商品分类列表
     * 
     * @param query 查询条件
     * @return 商品分类集合
     */
    List<GoodsCategoryEntity> selectGoodsCategoryList(GoodsCategoryQuery query);

    /**
     * 新增商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    int insertGoodsCategory(GoodsCategoryEntity goodsCategory);

    /**
     * 修改商品分类
     * 
     * @param goodsCategory 商品分类
     * @return 结果
     */
    int updateGoodsCategory(GoodsCategoryEntity goodsCategory);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的商品分类主键集合
     * @return 结果
     */
    int deleteGoodsCategoryByIds(String[] ids);

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    int deleteGoodsCategoryById(String id);
}
