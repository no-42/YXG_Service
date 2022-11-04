package com.ruoyi.market.domain.query;

import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品分类查询条件
 *
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
public class GoodsCategoryQuery extends BaseQuery {

    
    /**
     * 分类名称
     */
    @QueryField(type = QueryField.CompareType.LIKE)
    private String name;


}
