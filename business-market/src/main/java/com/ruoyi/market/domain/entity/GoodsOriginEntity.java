package com.ruoyi.market.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.IdEntity;

/**
 * 商品产地对象 goods_origin
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Getter
@Setter
@ToString
@TableName(value = "goods_origin",schema = "market")
public class GoodsOriginEntity extends IdEntity{
    
    
    /**
     *  商品来源 
     */
    @TableField("name")
    private String name;
    

    
}
