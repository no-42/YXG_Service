package com.ruoyi.common.core.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;

/**
 * 字典数据表 sys_dict_data
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_dict_data", schema = "system")
public class SysDictDataEntity extends IdDateEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 字典排序
     */
    @Excel(name = "字典排序", cellType = ColumnType.NUMERIC)
    @TableField("sort")
    private Long sort;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    @Excel(name = "字典标签")
    @TableField("label")
    private String label;

    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    @Excel(name = "字典键值")
    @TableField("value")
    private String value;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    @Excel(name = "字典类型")
    @TableField("type")
    private String type;

    /**
     * 样式属性（其他样式扩展）
     */
    @Size(min = 0, max = 100, message = "样式属性长度不能超过100个字符")
    @TableField("css_class")
    private String cssClass;

    /**
     * 表格字典样式
     */
    @TableField("list_class")
    private String listClass;

    /**
     * 是否默认
     */
    @Excel(name = "是否默认", readConverterExp = "true=是,false=否")
    @TableField("is_default")
    private Boolean isDefault;

    /**
     * 状态
     */
    @Excel(name = "状态", readConverterExp = "true=正常,false=停用")
    @TableField("enable")
    private Boolean enable;
}
