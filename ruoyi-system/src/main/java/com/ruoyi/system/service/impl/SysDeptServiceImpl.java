package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.query.SysDeptQuery;
import com.ruoyi.system.utils.TreeSelectBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.entity.SysDeptEntity;
import com.ruoyi.common.core.entity.SysRoleEntity;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.service.ISysDeptService;

/**
 * 部门管理 服务实现
 *
 * @author ruoyi
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {
    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<SysDeptEntity> selectDeptList(SysDeptQuery query) {
        return deptMapper.selectDeptList(query);
    }

    @Override
    public List<SysDeptEntity> buildDeptTree(List<SysDeptEntity> depts) {
        List<SysDeptEntity> returnList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        for (SysDeptEntity dept : depts) {
            tempList.add(dept.getId());
        }
        for (SysDeptEntity dept : depts) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId())) {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty()) {
            returnList = depts;
        }
        return returnList;
    }

    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDeptEntity> depts) {
        List<SysDeptEntity> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelectBuild::sysDeptBuild).collect(Collectors.toList());
    }

    @Override
    public List<String> selectDeptListByRoleId(String roleId) {
        SysRoleEntity role = roleMapper.selectRoleById(roleId);
        return deptMapper.selectDeptListByRoleId(roleId, role.getDeptCheckStrictly());
    }

    @Override
    public SysDeptEntity selectDeptById(String deptId) {
        return deptMapper.selectDeptById(deptId);
    }


    @Override
    public int selectNormalChildrenDeptById(String deptId) {
        return deptMapper.selectNormalChildrenDeptById(deptId);
    }

    @Override
    public boolean hasChildByDeptId(String deptId) {
        int result = deptMapper.hasChildByDeptId(deptId);
        return result > 0;
    }

    @Override
    public boolean checkDeptExistUser(String deptId) {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0;
    }

    @Override
    public String checkDeptNameUnique(SysDeptEntity dept) {
        String deptId = StringUtils.isNotEmpty(dept.getId()) ? dept.getId() : null;
        SysDeptEntity info = deptMapper.checkDeptNameUnique(dept.getName(), dept.getParentId());
        if (StringUtils.isNotNull(info)) {
            return info.getId().equals(deptId) ? UserConstants.UNIQUE : UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public void checkDeptDataScope(String deptId) {
        if (!SecurityUtils.isAdmin()) {
            SysDeptQuery query = new SysDeptQuery();
            query.setId(deptId);
            List<SysDeptEntity> depts = SpringUtils.getAopProxy(this).selectDeptList(query);
            if (StringUtils.isEmpty(depts)) {
                throw new ServiceException("没有权限访问部门数据！");
            }
        }
    }

    @Override
    public int insertDept(SysDeptEntity dept) {
        SysDeptEntity info = deptMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!info.getEnable()) {
            throw new ServiceException("部门停用，不允许新增");
        }
        if (dept.getParentId() == null) {
            dept.setAncestors(null);
        } else if (info.getAncestors() == null) {
            dept.setAncestors(dept.getParentId());
        } else {
            dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        }
        return deptMapper.insertDept(dept);
    }

    @Override
    public int updateDept(SysDeptEntity dept) {
        SysDeptEntity newParentDept = deptMapper.selectDeptById(dept.getParentId());
        SysDeptEntity oldDept = deptMapper.selectDeptById(dept.getId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
            String newAncestors;
            if (newParentDept.getAncestors() == null) {
                newAncestors = newParentDept.getId();
            } else {
                newAncestors = newParentDept.getAncestors() + "," + newParentDept.getId();
            }
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getId(), newAncestors, oldAncestors);
        }
        int result = deptMapper.updateDept(dept);
        if (dept.getEnable() && StringUtils.isNotEmpty(dept.getAncestors())
                && !StringUtils.equals("0", dept.getAncestors())) {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(dept);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(SysDeptEntity dept) {
        String ancestors = dept.getAncestors();
        String[] deptIds = Convert.toStrArray(ancestors);
        deptMapper.updateDeptStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId       被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(String deptId, String newAncestors, String oldAncestors) {
        List<SysDeptEntity> children = deptMapper.selectChildrenDeptById(deptId);
        for (SysDeptEntity child : children) {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0) {
            deptMapper.updateDeptChildren(children);
        }
    }

    @Override
    public int deleteDeptById(String deptId) {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDeptEntity> list, SysDeptEntity t) {
        // 得到子节点列表
        List<SysDeptEntity> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDeptEntity tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysDeptEntity> getChildList(List<SysDeptEntity> list, SysDeptEntity t) {
        List<SysDeptEntity> sysDeptEntities = new ArrayList<>();
        for (SysDeptEntity n : list) {
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().equals(t.getId())) {
                sysDeptEntities.add(n);
            }
        }
        return sysDeptEntities;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDeptEntity> list, SysDeptEntity t) {
        return getChildList(list, t).size() > 0;
    }
}
