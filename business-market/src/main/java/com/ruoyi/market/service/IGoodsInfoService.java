package com.ruoyi.market.service;

import java.util.List;

import com.ruoyi.market.domain.dto.GoodsInfoDto;
import com.ruoyi.market.domain.entity.GoodsInfoEntity;
import com.ruoyi.market.domain.query.GoodsInfoQuery;

/**
 * 商品信息Service接口
 * 
 * @author thetbw
 * @date 2022-11-04
 */
public interface IGoodsInfoService {
    /**
     * 查询商品信息
     * 
     * @param categoryId 商品信息主键
     * @return 商品信息
     */
    GoodsInfoDto selectGoodsInfoByCategoryId(String categoryId);

    /**
     * 查询商品信息列表
     * 
     * @param query 查询条件
     * @return 商品信息集合
     */
    List<GoodsInfoDto> selectGoodsInfoList(GoodsInfoQuery query);

    /**
     * 新增商品信息
     * 
     * @param goodsInfo 商品信息
     * @return 结果
     */
    int insertGoodsInfo(GoodsInfoEntity goodsInfo);

    /**
     * 修改商品信息
     * 
     * @param goodsInfo 商品信息
     * @return 结果
     */
    int updateGoodsInfo(GoodsInfoEntity goodsInfo);

    /**
     * 批量删除商品信息
     * 
     * @param categoryIds 需要删除的商品信息主键集合
     * @return 结果
     */
    int deleteGoodsInfoByCategoryIds(String[] categoryIds);

    /**
     * 删除商品信息信息
     * 
     * @param categoryId 商品信息主键
     * @return 结果
     */
    int deleteGoodsInfoByCategoryId(String categoryId);
}
