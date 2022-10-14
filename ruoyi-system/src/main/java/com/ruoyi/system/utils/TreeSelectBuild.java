package com.ruoyi.system.utils;

import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.entity.SysDeptEntity;
import com.ruoyi.system.domain.entity.SysMenuEntity;

import java.util.stream.Collectors;

/*** 
 * 构造树选择器
 */
public class TreeSelectBuild {

    public static TreeSelect sysDeptBuild(SysDeptEntity sysDeptEntity) {
        TreeSelect treeSelect = new TreeSelect();
        treeSelect.setId(sysDeptEntity.getId());
        treeSelect.setLabel(sysDeptEntity.getName());
        treeSelect.setChildren(sysDeptEntity.getChildren().stream().map(TreeSelectBuild::sysDeptBuild).collect(Collectors.toList()));
        return treeSelect;
    }

    public static TreeSelect sysMenuBuild(SysMenuEntity sysMenuEntity) {
        TreeSelect treeSelect = new TreeSelect();
        treeSelect.setId(sysMenuEntity.getId());
        treeSelect.setLabel(sysMenuEntity.getName());
        treeSelect.setChildren(sysMenuEntity.getChildren().stream().map(TreeSelectBuild::sysMenuBuild).collect(Collectors.toList()));
        return treeSelect;
    }
}
