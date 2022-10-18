package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ruoyi.system.domain.query.SysRoleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.entity.SysRoleEntity;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.domain.entity.SysRoleDeptEntity;
import com.ruoyi.system.domain.entity.SysRoleMenuEntity;
import com.ruoyi.system.domain.entity.SysUserRoleEntity;
import com.ruoyi.system.mapper.SysRoleDeptMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysRoleMenuMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysRoleService;

/**
 * 角色 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

    @Override
    public List<SysRoleEntity> selectRoleList(SysRoleQuery query) {
        return roleMapper.selectRoleList(query);
    }
    
    @Override
    public List<SysRoleEntity> selectRolesByUserId(String userId) {
        List<SysRoleEntity> userRoles = roleMapper.selectRolePermissionByUserId(userId);
        List<SysRoleEntity> roles = selectRoleAll();
        for (SysRoleEntity role : roles) {
            for (SysRoleEntity userRole : userRoles) {
                if (role.getId().equals(userRole.getId())) {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }
    
    @Override
    public Set<String> selectRolePermissionByUserId(String userId) {
        List<SysRoleEntity> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRoleEntity perm : perms) {
            if (StringUtils.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getKey().trim().split(",")));
            }
        }
        return permsSet;
    }
    
    @Override
    public List<SysRoleEntity> selectRoleAll() {
        return SpringUtils.getAopProxy(this).selectRoleList(new SysRoleQuery());
    }
    
    @Override
    public List<String> selectRoleListByUserId(String userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }
    
    @Override
    public SysRoleEntity selectRoleById(String roleId) {
        return roleMapper.selectRoleById(roleId);
    }
    
    @Override
    public String checkRoleNameUnique(SysRoleEntity role) {
        SysRoleEntity info = roleMapper.checkRoleNameUnique(role.getName());
        if (StringUtils.isNotNull(info) && !info.getId().equals(role.getId())) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    
    @Override
    public String checkRoleKeyUnique(SysRoleEntity role) {
        SysRoleEntity info = roleMapper.checkRoleKeyUnique(role.getKey());
        if (StringUtils.isNotNull(info) && !info.getId().equals(role.getId())) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    
    @Override
    public void checkRoleAllowed(SysRoleEntity role) {
        if (StringUtils.isNotNull(role.getId()) && role.isAdmin()) {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }
    
    @Override
    public void checkRoleDataScope(String roleId) {
        if (!SecurityUtils.isAdmin()) {
            SysRoleQuery role = new SysRoleQuery();
            role.setId(roleId);
            List<SysRoleEntity> roles = SpringUtils.getAopProxy(this).selectRoleList(role);
            if (StringUtils.isEmpty(roles)) {
                throw new ServiceException("没有权限访问角色数据！");
            }
        }
    }
    
    @Override
    public int countUserRoleByRoleId(String roleId) {
        return userRoleMapper.countUserRoleByRoleId(roleId);
    }
    
    @Override
    @Transactional
    public int insertRole(SysRoleEntity role) {
        // 新增角色信息
        roleMapper.insertRole(role);
        return insertRoleMenu(role);
    }
    
    @Override
    @Transactional
    public int updateRole(SysRoleEntity role) {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getId());
        return insertRoleMenu(role);
    }
    
    @Override
    public int updateRoleStatus(SysRoleEntity role) {
        return roleMapper.updateRole(role);
    }
    
    @Override
    @Transactional
    public int authDataScope(SysRoleEntity role) {
        // 修改角色信息
        roleMapper.updateRole(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }
    
    public int insertRoleMenu(SysRoleEntity role) {
        int rows = 1;
        // 新增用户与角色管理
        List<SysRoleMenuEntity> list = new ArrayList<>();
        for (String menuId : role.getMenuIds()) {
            SysRoleMenuEntity rm = new SysRoleMenuEntity();
            rm.setRoleId(role.getId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = roleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }
    
    public int insertRoleDept(SysRoleEntity role) {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<SysRoleDeptEntity> list = new ArrayList<>();
        for (String deptId : role.getDeptIds()) {
            SysRoleDeptEntity rd = new SysRoleDeptEntity();
            rd.setRoleId(role.getId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0) {
            rows = roleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }
    
    @Override
    @Transactional
    public int deleteRoleById(String roleId) {
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(roleId);
        return roleMapper.deleteRoleById(roleId);
    }
    
    @Override
    @Transactional
    public int deleteRoleByIds(String[] roleIds) {
        for (String roleId : roleIds) {
            SysRoleEntity role = new SysRoleEntity();
            role.setId(roleId);
            checkRoleAllowed(role);
            checkRoleDataScope(roleId);
            role = selectRoleById(roleId);
            if (countUserRoleByRoleId(roleId) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", role.getName()));
            }
        }
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenu(roleIds);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDept(roleIds);
        return roleMapper.deleteRoleByIds(roleIds);
    }
    
    @Override
    public int deleteAuthUser(SysUserRoleEntity userRole) {
        return userRoleMapper.deleteUserRoleInfo(userRole);
    }
    
    @Override
    public int deleteAuthUsers(String roleId, String[] userIds) {
        return userRoleMapper.deleteUserRoleInfos(roleId, userIds);
    }
    
    @Override
    public int insertAuthUsers(String roleId, String[] userIds) {
        // 新增用户与角色管理
        List<SysUserRoleEntity> list = new ArrayList<>();
        for (String userId : userIds) {
            SysUserRoleEntity ur = new SysUserRoleEntity();
            ur.setUserId(userId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return userRoleMapper.batchUserRole(list);
    }
}
