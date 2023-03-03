package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.entity.OrderInfoEntity;
import com.ruoyi.order.domain.query.OrderInfoQuery;

/**
 * 订单信息Service接口
 * 
 * @author thetbw
 * @date 2023-03-03
 */
public interface IOrderInfoService {
    /**
     * 查询订单信息
     * 
     * @param id 订单信息主键
     * @return 订单信息
     */
    OrderInfoEntity selectOrderInfoById(String id);

    /**
     * 查询订单信息列表
     * 
     * @param query 查询条件
     * @return 订单信息集合
     */
    List<OrderInfoEntity> selectOrderInfoList(OrderInfoQuery query);

    /**
     * 新增订单信息
     * 
     * @param orderInfo 订单信息
     * @return 结果
     */
    int insertOrderInfo(OrderInfoEntity orderInfo);

    /**
     * 修改订单信息
     * 
     * @param orderInfo 订单信息
     * @return 结果
     */
    int updateOrderInfo(OrderInfoEntity orderInfo);

    /**
     * 批量删除订单信息
     * 
     * @param ids 需要删除的订单信息主键集合
     * @return 结果
     */
    int deleteOrderInfoByIds(String[] ids);

    /**
     * 删除订单信息信息
     * 
     * @param id 订单信息主键
     * @return 结果
     */
    int deleteOrderInfoById(String id);
}
