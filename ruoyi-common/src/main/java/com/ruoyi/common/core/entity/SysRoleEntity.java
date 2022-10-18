package com.ruoyi.common.core.entity;

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
 * 角色表 sys_role
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_role", schema = "system")
public class SysRoleEntity extends IdDateEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @Excel(name = "角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    @TableField("name")
    private String name;

    /**
     * 角色权限
     */
    @Excel(name = "角色权限")
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    @TableField("key")
    private String key;

    /**
     * 角色排序
     */
    @Excel(name = "角色排序")
    @NotNull(message = "显示顺序不能为空")
    @TableField("sort")
    private Integer sort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限,5=仅本人数据权限")
    @TableField("data_scope")
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    @TableField("menu_check_strictly")
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）
     */
    @TableField("dept_check_strictly")
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @Excel(name = "角色状态", readConverterExp = "true=正常,false=停用")
    @TableField("enable")
    private Boolean enable;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private Boolean delFlag;

    /**
     * 用户是否存在此角色标识 默认不存在
     * TODO 放到dto中
     */
    @TableField(exist = false)
    private boolean flag = false;

    /**
     * 菜单组
     * TODO 放到dto中
     */
    @TableField(exist = false)
    private String[] menuIds;

    /**
     * 部门组（数据权限）
     * TODO 放到dto中
     */
    @TableField(exist = false)
    private String[] deptIds;


    public boolean isAdmin() {
        return isAdmin(this.getName());
    }

    public static boolean isAdmin(String name) {
        return "admin".equals(name);
    }

}
