package com.ruoyi.quartz.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysJobQuery extends BaseQuery {

    private String name;

    private String group;

    private Boolean enable;

    private String invokeTarget;
}
