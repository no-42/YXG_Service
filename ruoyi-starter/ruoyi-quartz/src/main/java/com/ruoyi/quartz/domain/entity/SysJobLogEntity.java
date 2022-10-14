package com.ruoyi.quartz.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.core.domain.IdEntity;
import com.ruoyi.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 定时任务调度日志表 sys_job_log
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
public class SysJobLogEntity extends IdEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    @Excel(name = "任务名称")
    @TableField("job_name")
    private String jobName;

    /**
     * 任务组名
     */
    @Excel(name = "任务组名")
    @TableField("job_group")
    private String jobGroup;

    /**
     * 调用目标字符串
     */
    @Excel(name = "调用目标字符串")
    @TableField("invoke_target")
    private String invokeTarget;

    /**
     * 日志信息
     */
    @Excel(name = "日志信息")
    @TableField("message")
    private String message;

    /**
     * 执行状态（0正常 1失败）
     */
    @Excel(name = "执行状态", readConverterExp = "0=正常,1=失败")
    @TableField("status")
    private String status;

    /**
     * 异常信息
     */
    @Excel(name = "异常信息")
    @TableField("exception_info")
    private String exceptionInfo;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 停止时间
     */
    private Date stopTime;

    /**
     * 创建时间
     */
    private Date createTime;


}
