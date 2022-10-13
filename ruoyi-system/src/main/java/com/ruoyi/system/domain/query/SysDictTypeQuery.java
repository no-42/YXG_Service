package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysDictTypeQuery extends BaseQuery {
    
    private String name;
    
    private Boolean enable;
    
    private String type;
    
    private String createTimeBegin;
    
    private String createTimeEnd;
}
