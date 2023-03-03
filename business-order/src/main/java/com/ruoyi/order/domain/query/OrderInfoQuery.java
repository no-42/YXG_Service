package com.ruoyi.order.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 订单信息查询条件
 *
 * @author thetbw
 * @date 2023-03-03
 */
@Getter
@Setter
@ToString
public class OrderInfoQuery extends BaseQuery {


    /**
     * 订单编号
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String orderNo;


    /**
     * 外部订单号
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String outOrderNo;


    /**
     * 订单状态
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String status;


    /**
     * 订单用户id
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String memberId;


}
