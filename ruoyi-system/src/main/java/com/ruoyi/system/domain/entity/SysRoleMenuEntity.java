package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_role_menu", schema = "system")
public class SysRoleMenuEntity extends BaseEntity {
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private String menuId;
}
