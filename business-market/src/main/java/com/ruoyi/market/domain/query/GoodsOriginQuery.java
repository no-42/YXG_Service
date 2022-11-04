package com.ruoyi.market.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品产地查询条件
 *
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
public class GoodsOriginQuery extends BaseQuery {


    /**
     * 商品来源
     */
    @QueryField(type = QueryField.CompareType.LIKE)
    private String name;


}
