package com.ruoyi.market.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.IdEntity;

/**
 * 商品规格对象 goods_spec
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
@TableName(value = "goods_spec",schema = "market")
public class GoodsSpecEntity extends IdEntity{
    
    /**
     *  规格名称 
     */
    @TableField("name")
    private String name;
    
    /**
     *  规格值 
     */
    @TableField("value")
    private String value;
    

    
}
