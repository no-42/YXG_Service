package com.ruoyi.system.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.IdEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 系统访问记录表 sys_logininfor
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_logininfor", schema = "system")
public class SysLoginInfoEntity extends IdEntity {
    
    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    @TableField("user_name")
    private String userName;

    /**
     * 登录状态 0成功 1失败
     */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    @TableField("status")
    private String status;

    /**
     * 登录IP地址
     */
    @Excel(name = "登录地址")
    @TableField("ipaddr")
    private String ipaddr;

    /**
     * 登录地点
     */
    @Excel(name = "登录地点")
    @TableField("login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器")
    @TableField("browser")
    private String browser;

    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    @TableField("os")
    private String os;

    /**
     * 提示消息
     */
    @Excel(name = "提示消息")
    @TableField("msg")
    private String msg;

    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @TableField("login_time")
    private Date loginTime;

}
