package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysRoleQuery extends BaseQuery {

    private String id;

    private String name;

    private Boolean enable;

    private String key;

    private String createTimeBegin;

    private String createTimeEnd;

}
