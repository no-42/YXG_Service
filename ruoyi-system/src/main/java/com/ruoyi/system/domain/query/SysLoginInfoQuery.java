package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysLoginInfoQuery extends BaseQuery {

    private String ipaddr;

    private String status;

    private String userName;

    private String loginTimeBegin;

    private String loginTimeEnd;
}
