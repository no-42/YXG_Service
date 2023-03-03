package com.ruoyi.order.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.core.domain.IdDateEntity;

/**
 * 订单信息对象 order_info
 *
 * @author thetbw
 * @date 2023-03-03
 */
@Getter
@Setter
@ToString
@TableName(value = "order_info", schema = "\"order\"")
public class OrderInfoEntity extends IdDateEntity {

    /**
     * id
     */
    @TableField("id")
    private String id;

    /**
     * 订单编号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 外部订单号
     */
    @TableField("out_order_no")
    private String outOrderNo;

    /**
     * 下单时间
     */
    @TableField("order_time")
    private Date orderTime;

    /**
     * 总价
     */
    @TableField("total_price")
    private String totalPrice;

    /**
     * 订单状态
     */
    @TableField("status")
    private String status;

    /**
     * 最终支付项id
     */
    @TableField("pay_order_id")
    private String payOrderId;

    /**
     * 订单用户id
     */
    @TableField("member_id")
    private String memberId;


}
