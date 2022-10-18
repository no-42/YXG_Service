package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.domain.BaseMapper;
import com.ruoyi.common.core.entity.SysRoleEntity;
import com.ruoyi.system.domain.query.SysRoleQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    /**
     * 根据条件分页查询角色数据
     *
     * @param query 角色信息
     * @return 角色数据集合信息
     */
    @DataScope(deptAlias = "d")
    List<SysRoleEntity> selectRoleList(SysRoleQuery query);

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
    List<SysRoleEntity> selectRolePermissionByUserId(String userId);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<SysRoleEntity> selectRoleAll();

    /**
     * 根据用户ID获取角色选择框列表
     *
     * @param userId 用户ID
     * @return 选中角色ID列表
     */
    List<String> selectRoleListByUserId(String userId);

    /**
     * 通过角色ID查询角色
     *
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    SysRoleEntity selectRoleById(String roleId);

    /**
     * 根据用户ID查询角色
     *
     * @param userName 用户名
     * @return 角色列表
     */
    List<SysRoleEntity> selectRolesByUserName(String userName);

    /**
     * 校验角色名称是否唯一
     *
     * @param roleName 角色名称
     * @return 角色信息
     */
    SysRoleEntity checkRoleNameUnique(String roleName);

    /**
     * 校验角色权限是否唯一
     *
     * @param roleKey 角色权限
     * @return 角色信息
     */
    SysRoleEntity checkRoleKeyUnique(String roleKey);

    /**
     * 修改角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int updateRole(SysRoleEntity role);

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     * @return 结果
     */
    int insertRole(SysRoleEntity role);

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    int deleteRoleById(String roleId);

    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    int deleteRoleByIds(String[] roleIds);
}
