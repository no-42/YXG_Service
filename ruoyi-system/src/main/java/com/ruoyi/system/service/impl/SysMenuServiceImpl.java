package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruoyi.system.domain.query.SysMenuQuery;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.utils.TreeSelectBuild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.system.domain.entity.SysMenuEntity;
import com.ruoyi.common.core.entity.SysRoleEntity;
import com.ruoyi.common.core.entity.SysUserEntity;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.vo.MetaVo;
import com.ruoyi.system.domain.vo.RouterVo;
import com.ruoyi.system.mapper.SysMenuMapper;
import com.ruoyi.system.mapper.SysRoleMapper;
import com.ruoyi.system.mapper.SysRoleMenuMapper;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 菜单 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysMenuEntity> selectMenuList(String userId) {
        return selectMenuList(new SysMenuQuery(), userId);
    }

    @Override
    public List<SysMenuEntity> selectMenuList(SysMenuQuery query, String userId) {
        List<SysMenuEntity> menuList;
        // 管理员显示所有菜单信息
        SysUserEntity user = sysUserMapper.selectUserById(userId);
        if (user.isAdmin()) {
            menuList = menuMapper.selectMenuList(query);
        } else {
            menuList = menuMapper.selectMenuListByUserId(query, userId);
        }
        return menuList;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(String userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenuEntity> selectMenuTreeByUserId(String userId) {
        List<SysMenuEntity> menus;
        SysUserEntity user = sysUserMapper.selectUserById(userId);
        if (user.isAdmin()) {
            menus = menuMapper.selectMenuTreeAll();
        } else {
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return getChildPerms(menus, null);
    }

    @Override
    public List<String> selectMenuListByRoleId(String roleId) {
        SysRoleEntity role = roleMapper.selectRoleById(roleId);
        return menuMapper.selectMenuListByRoleId(roleId, role.getMenuCheckStrictly());
    }

    @Override
    public List<RouterVo> buildMenus(List<SysMenuEntity> menus) {
        List<RouterVo> routers = new LinkedList<>();
        for (SysMenuEntity menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(!menu.getVisible());
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
            List<SysMenuEntity> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && UserConstants.TYPE_DIR.equals(menu.getType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getIsCache(), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (StringUtils.isEmpty(menu.getParentId()) && isInnerLink(menu)) {
                router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    @Override
    public List<SysMenuEntity> buildMenuTree(List<SysMenuEntity> menus) {
        List<SysMenuEntity> returnList = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        for (SysMenuEntity dept : menus) {
            tempList.add(dept.getId());
        }
        for (SysMenuEntity menu : menus) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<SysMenuEntity> menus) {
        List<SysMenuEntity> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelectBuild::sysMenuBuild).collect(Collectors.toList());
    }

    @Override
    public SysMenuEntity selectMenuById(String menuId) {
        return menuMapper.selectMenuById(menuId);
    }

    @Override
    public boolean hasChildByMenuId(String menuId) {
        int result = menuMapper.hasChildByMenuId(menuId);
        return result > 0;
    }

    @Override
    public boolean checkMenuExistRole(String menuId) {
        int result = roleMenuMapper.checkMenuExistRole(menuId);
        return result > 0;
    }

    @Override
    public int insertMenu(SysMenuEntity menu) {
        return menuMapper.insertMenu(menu);
    }

    @Override
    public int updateMenu(SysMenuEntity menu) {
        return menuMapper.updateMenu(menu);
    }

    @Override
    public int deleteMenuById(String menuId) {
        return menuMapper.deleteMenuById(menuId);
    }

    @Override
    public String checkMenuNameUnique(SysMenuEntity menu) {
        SysMenuEntity info = menuMapper.checkMenuNameUnique(menu.getName(), menu.getParentId());
        if (StringUtils.isNotNull(info) && !info.getId().equals(menu.getId())) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    private String getRouteName(SysMenuEntity menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(SysMenuEntity menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (StringUtils.isEmpty(menu.getParentId()) && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (StringUtils.isEmpty(menu.getParentId()) && UserConstants.TYPE_DIR.equals(menu.getType())
                && !menu.getIsFrame()) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    private String getComponent(SysMenuEntity menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && StringUtils.isNotEmpty(menu.getParentId()) && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isMenuFrame(SysMenuEntity menu) {
        return StringUtils.isEmpty(menu.getParentId()) && UserConstants.TYPE_MENU.equals(menu.getType())
                && !menu.getIsFrame();
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isInnerLink(SysMenuEntity menu) {
        return !menu.getIsFrame() && StringUtils.ishttp(menu.getPath());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    private boolean isParentView(SysMenuEntity menu) {
        return StringUtils.isNotEmpty(menu.getParentId()) && UserConstants.TYPE_DIR.equals(menu.getType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<SysMenuEntity> getChildPerms(List<SysMenuEntity> list, String parentId) {
        List<SysMenuEntity> returnList = new ArrayList<>();
        for (SysMenuEntity t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == null && parentId == null || (t.getParentId() != null && t.getParentId().equals(parentId))) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysMenuEntity> list, SysMenuEntity t) {
        // 得到子节点列表
        List<SysMenuEntity> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenuEntity tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenuEntity> getChildList(List<SysMenuEntity> list, SysMenuEntity t) {
        List<SysMenuEntity> tlist = new ArrayList<>();
        for (SysMenuEntity n : list) {
            if (n.getParentId() != null && n.getParentId().equals(t.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenuEntity> list, SysMenuEntity t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 内链域名特殊字符替换
     */
    public String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{Constants.HTTP, Constants.HTTPS},
                new String[]{"" , ""});
    }
}
