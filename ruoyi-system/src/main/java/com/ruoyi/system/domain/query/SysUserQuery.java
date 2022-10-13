package com.ruoyi.system.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysUserQuery extends BaseQuery {
    private String id;
    
    private String roleId;

    private String userName;

    private Boolean enable;

    private String phone;

    private String deptId;

    private String createTimeBegin;

    private String createTimeEnd;

}
