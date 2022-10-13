package com.ruoyi.quartz.domain.query;

import com.ruoyi.common.core.domain.BaseQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SysJobLogQuery extends BaseQuery {

    private String jobName;

    private String jobGroup;

    private String status;

    private String invokeTarget;

    private String createTimeBegin;

    private String createTimeEnd;

}
