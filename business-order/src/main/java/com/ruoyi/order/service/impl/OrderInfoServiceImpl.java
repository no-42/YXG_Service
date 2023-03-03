package com.ruoyi.order.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrderInfoMapper;
import com.ruoyi.order.domain.entity.OrderInfoEntity;
import com.ruoyi.order.domain.query.OrderInfoQuery;
import com.ruoyi.order.service.IOrderInfoService;

/**
 * 订单信息Service业务层处理
 *
 * @author thetbw
 * @date 2023-03-03
 */
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderInfoEntity selectOrderInfoById(String id) {
        return orderInfoMapper.selectById(id);
    }

    @Override
    public List<OrderInfoEntity> selectOrderInfoList(OrderInfoQuery query) {
        return orderInfoMapper.selectListByQuery(query);
    }


    @Override
    public int insertOrderInfo(OrderInfoEntity orderInfo) {
        return orderInfoMapper.insert(orderInfo);
    }

    @Override
    public int updateOrderInfo(OrderInfoEntity orderInfo) {
        return orderInfoMapper.updateById(orderInfo);
    }

    @Override
    public int deleteOrderInfoByIds(String[] ids) {
        return orderInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int deleteOrderInfoById(String id) {
        return orderInfoMapper.deleteById(id);
    }
}
