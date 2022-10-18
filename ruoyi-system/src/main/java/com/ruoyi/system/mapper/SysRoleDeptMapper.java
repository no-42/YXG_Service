package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.core.domain.BaseMapper;
import com.ruoyi.system.domain.entity.SysRoleDeptEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色与部门关联表 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDeptEntity> {
    /**
     * 通过角色ID删除角色和部门关联
     *
     * @param roleId 角色ID
     * @return 结果
     */
     int deleteRoleDeptByRoleId(String roleId);

    /**
     * 批量删除角色部门关联信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteRoleDept(String[] ids);

    /**
     * 查询部门使用数量
     *
     * @param deptId 部门ID
     * @return 结果
     */
     int selectCountRoleDeptByDeptId(String deptId);

    /**
     * 批量新增角色部门信息
     *
     * @param roleDeptList 角色部门列表
     * @return 结果
     */
     int batchRoleDept(List<SysRoleDeptEntity> roleDeptList);
}
