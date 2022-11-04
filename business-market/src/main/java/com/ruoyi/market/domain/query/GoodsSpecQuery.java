package com.ruoyi.market.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品规格查询条件
 *
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
public class GoodsSpecQuery extends BaseQuery {


    /**
     * 规格名称
     */
    @QueryField(type = QueryField.CompareType.LIKE)
    private String name;


    /**
     * 规格值
     */
    @QueryField(type = QueryField.CompareType.EQ)
    private String value;


}
