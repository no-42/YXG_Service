package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysOperLogQuery extends BaseQuery {
    private String title;
    
    private String businessType;
    
    private String[] businessTypes;
    
    private Integer status;
    
    private String operName;
    
    private String operTimeBegin;
    
    private String operTimeEnd;
}
