package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色和部门关联 sys_role_dept
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_role_dept", schema = "system")
public class SysRoleDeptEntity extends BaseEntity {
    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    /**
     * 部门ID
     */
    @TableField("dept_id")
    private String deptId;

}
