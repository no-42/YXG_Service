package com.ruoyi.common.core.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;

/**
 * 字典类型表 sys_dict_type
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_dict_type", schema = "system")
public class SysDictTypeEntity extends IdDateEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 字典名称
     */
    @Excel(name = "字典名称")
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    @TableField("name")
    private String name;

    /**
     * 字典类型
     */
    @Excel(name = "字典类型")
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
    @TableField("type")
    private String type;

    /**
     * 状态
     */
    @Excel(name = "状态", readConverterExp = "true=正常,false=停用")
    @TableField("enable")
    private Boolean enable;
}
