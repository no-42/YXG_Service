package com.ruoyi.market.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品信息查询条件
 *
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
public class GoodsInfoQuery extends BaseQuery {


    /**
     * 商品分类
     */
    private String categoryId;


    /**
     * 商品名称
     */
    private String name;

    /**
     * 供应商id
     */
    private String supplierId;

    /**
     * 规则id
     */
    private String specId;

    /**
     * 产地id
     */
    private String originId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 搜索字段
     */
    private String search;


}
