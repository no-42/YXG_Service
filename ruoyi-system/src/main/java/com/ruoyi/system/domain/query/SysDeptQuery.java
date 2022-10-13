package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysDeptQuery extends BaseQuery {

    private String id;

    private String parentId;

    private String name;

    private Boolean enable;
}
