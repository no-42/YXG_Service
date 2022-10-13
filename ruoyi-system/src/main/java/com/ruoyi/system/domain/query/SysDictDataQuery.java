package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysDictDataQuery extends BaseQuery {

    private String type;

    private String label;

    private Boolean enable;
}
