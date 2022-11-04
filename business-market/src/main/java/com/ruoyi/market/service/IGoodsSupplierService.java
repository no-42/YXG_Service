package com.ruoyi.market.service;

import java.util.List;
import com.ruoyi.market.domain.entity.GoodsSupplierEntity;
import com.ruoyi.market.domain.query.GoodsSupplierQuery;

/**
 * 商品供应商Service接口
 * 
 * @author thetbw
 * @date 2022-11-04
 */
public interface IGoodsSupplierService {
    /**
     * 查询商品供应商
     * 
     * @param id 商品供应商主键
     * @return 商品供应商
     */
    GoodsSupplierEntity selectGoodsSupplierById(String id);

    /**
     * 查询商品供应商列表
     * 
     * @param query 查询条件
     * @return 商品供应商集合
     */
    List<GoodsSupplierEntity> selectGoodsSupplierList(GoodsSupplierQuery query);

    /**
     * 新增商品供应商
     * 
     * @param goodsSupplier 商品供应商
     * @return 结果
     */
    int insertGoodsSupplier(GoodsSupplierEntity goodsSupplier);

    /**
     * 修改商品供应商
     * 
     * @param goodsSupplier 商品供应商
     * @return 结果
     */
    int updateGoodsSupplier(GoodsSupplierEntity goodsSupplier);

    /**
     * 批量删除商品供应商
     * 
     * @param ids 需要删除的商品供应商主键集合
     * @return 结果
     */
    int deleteGoodsSupplierByIds(String[] ids);

    /**
     * 删除商品供应商信息
     * 
     * @param id 商品供应商主键
     * @return 结果
     */
    int deleteGoodsSupplierById(String id);
}
