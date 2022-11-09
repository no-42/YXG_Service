package com.ruoyi.market.domain.dto;


import com.ruoyi.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品信息
 */
@Getter
@Setter
@ToString
public class GoodsInfoDto {

    private String id;


    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String name;


    /**
     * 商品价格
     */
    @Excel(name = "价格")
    private String price;

    /**
     * 商品价格单位
     */
    private String priceUnit;


    /**
     * 商品分类
     */
    private String categoryId;

    /**
     * 类型名称
     */
    @Excel(name = "分类")
    private String categoryName;

    /**
     * 商品规格
     */
    private String specId;

    /**
     * 商品规格名称
     */
    @Excel(name = "规格")
    private String specName;


    /**
     * 商品所属供应商
     */
    private String supplierId;

    /**
     * 供应商名称
     */
    @Excel(name = "供应商")
    private String supplierName;

    /**
     * 商品来源
     */
    private String originId;

    /**
     * 来源名称
     */
    @Excel(name = "产地")
    private String originName;

}
