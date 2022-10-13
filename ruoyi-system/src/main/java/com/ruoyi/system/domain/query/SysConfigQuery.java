package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 参数配置查询条件
 * @author thetbw
 */
@Getter
@Setter
@ToString
public class SysConfigQuery extends BaseQuery {
    
    private String id;
    
    private String key;
    
    private String name;
    
    private String type;
    
    private String createTimeBegin;
    
    private String createTimeEnd;
}
