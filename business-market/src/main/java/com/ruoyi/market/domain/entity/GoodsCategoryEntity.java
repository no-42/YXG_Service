package com.ruoyi.market.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.IdEntity;

/**
 * 商品分类对象 goods_category
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
@TableName(value = "goods_category",schema = "market")
public class GoodsCategoryEntity extends IdEntity{
    
    
    /**
     *  分类名称 
     */
    @TableField("name")
    private String name;
    

    
}
