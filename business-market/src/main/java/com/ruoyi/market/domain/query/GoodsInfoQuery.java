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
    @QueryField(type = QueryField.CompareType.EQ)
    private String categoryId;


    /**
     * 商品名称
     */
    @QueryField(type = QueryField.CompareType.LIKE)
    private String name;

    /**
     * 分类名称
     */
    private String categoryName;
    
    
    

}
