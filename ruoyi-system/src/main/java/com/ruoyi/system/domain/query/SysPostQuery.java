package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysPostQuery extends BaseQuery {
    private String code;

    private Boolean enable;
    
    private String name;
}
