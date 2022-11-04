package com.ruoyi.market.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.core.domain.IdEntity;

/**
 * 商品信息对象 goods_info
 *
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
@TableName(value = "goods_info", schema = "market")
public class GoodsInfoEntity extends IdEntity {

    /**
     * 商品分类
     */
    @TableField("category_id")
    private String categoryId;

    /**
     * 商品规格
     */
    @TableField("spec_id")
    private String specId;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品所属供应商
     */
    @TableField("supplier_id")
    private String supplierId;

    /**
     * 商品来源
     */
    @TableField("origin_id")
    private String originId;

    /**
     * 商品价格
     */
    @TableField("price")
    private String price;

    /**
     * 商品价格单位
     */
    @TableField("price_unit")
    private String priceUnit;


}
