package com.ruoyi.common.core.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门表 sys_dept
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_dept", schema = "system")
public class SysDeptEntity extends IdDateEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 父部门ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 祖级列表
     */
    @TableField("ancestors")
    private String ancestors;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    @TableField("name")
    private String name;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 负责人
     */
    @TableField("leader")
    private String leader;

    /**
     * 联系电话
     */
    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    @TableField("email")
    private String email;

    /**
     * 部门状态
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 删除标志
     */
    @TableField("del_flag")
    private Boolean delFlag;

    /**
     * 父部门名称
     * TODO 这个应该移动到dto中
     */
    @TableField(exist = false)
    private String parentName;

    /**
     * 子部门
     * TODO 这个移动
     */
    @TableField(exist = false)
    private List<SysDeptEntity> children = new ArrayList<>();

}
