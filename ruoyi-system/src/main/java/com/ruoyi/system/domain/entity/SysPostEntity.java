package com.ruoyi.system.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;

/**
 * 岗位表 sys_post
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_post", schema = "system")
public class SysPostEntity extends IdDateEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 岗位编码
     */
    @Excel(name = "岗位编码")
    @NotBlank(message = "岗位编码不能为空")
    @Size(min = 0, max = 64, message = "岗位编码长度不能超过64个字符")
    @TableField("code")
    private String code;

    /**
     * 岗位名称
     */
    @Excel(name = "岗位名称")
    @NotBlank(message = "岗位名称不能为空")
    @Size(min = 0, max = 50, message = "岗位名称长度不能超过50个字符")
    @TableField("name")
    private String name;

    /**
     * 岗位排序
     */
    @Excel(name = "岗位排序")
    @NotNull(message = "显示顺序不能为空")
    @TableField("sort")
    private Integer sort;

    /**
     * 状态（0正常 1停用）
     */
    @Excel(name = "状态", readConverterExp = "true=正常,false=停用")
    @TableField("enable")
    private Boolean enable;

    /**
     * 用户是否存在此岗位标识 默认不存在
     * TODO 这个待确定
     */
    @TableField(exist = false)
    private boolean flag = false;

}
