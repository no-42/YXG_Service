package com.ruoyi.framework.aspectj;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.BaseQuery;
import com.ruoyi.common.core.domain.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.core.entity.SysRoleEntity;
import com.ruoyi.common.core.entity.SysUserEntity;
import com.ruoyi.common.utils.StringUtils;

/**
 * 数据过滤处理
 * TODO 数据权限暂时失效
 *
 * @author ruoyi
 */
@Aspect
@Component
@Slf4j
public class DataScopeAspect {
    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Before("@annotation(controllerDataScope)")
    public void doBefore(JoinPoint point, DataScope controllerDataScope) throws Throwable {
        clearDataScope(point);
        handleDataScope(point, controllerDataScope);
    }

    protected void handleDataScope(final JoinPoint joinPoint, DataScope controllerDataScope) {
        // 获取当前的用户
        LoginUser<?> loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser)) {
            if (loginUser.getUser() instanceof SysUserEntity) {
                SysUserEntity currentUser = (SysUserEntity) loginUser.getUser();
                // 如果是超级管理员，则不过滤数据
                if (StringUtils.isNotNull(currentUser) && !currentUser.isAdmin()) {
                    dataScopeFilter(joinPoint, currentUser, controllerDataScope.deptAlias(),
                            controllerDataScope.userAlias());
                }
            }
        }
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param user      用户
     * @param deptAlias 部门别名
     * @param userAlias 用户别名
     */
    public static void dataScopeFilter(JoinPoint joinPoint, SysUserEntity user, String deptAlias, String userAlias) {
        StringBuilder sqlString = new StringBuilder();
        List<String> conditions = new ArrayList<>();

        for (SysRoleEntity role : user.getRoles()) {
            String dataScope = role.getDataScope();
            if (!DATA_SCOPE_CUSTOM.equals(dataScope) && conditions.contains(dataScope)) {
                continue;
            }
            if (DATA_SCOPE_ALL.equals(dataScope)) {
                sqlString = new StringBuilder();
                break;
            } else if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
                sqlString.append(StringUtils.format(
                        " OR {}.id IN ( SELECT dept_id FROM \"system\".sys_role_dept WHERE role_id = {} ) " , deptAlias,
                        role.getId()));
            } else if (DATA_SCOPE_DEPT.equals(dataScope)) {
                sqlString.append(StringUtils.format(" OR {}.id = {} " , deptAlias, user.getDeptId()));
            } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
                sqlString.append(StringUtils.format(
                        " OR {}.id IN ( SELECT id FROM  \"system\".sys_dept WHERE id = {} or {} = ANY(STRING_TO_ARRAY(ancestors, ',')) )" ,
                        deptAlias, user.getDeptId(), user.getDeptId()));
            } else if (DATA_SCOPE_SELF.equals(dataScope)) {
                if (StringUtils.isNotBlank(userAlias)) {
                    sqlString.append(StringUtils.format(" OR {}.id = {} " , userAlias, user.getId()));
                } else {
                    // 数据权限为仅本人且没有userAlias别名不查询任何数据
                    sqlString.append(StringUtils.format(" OR {}.id = 0 " , deptAlias));
                }
            }
            conditions.add(dataScope);
        }

        if (StringUtils.isNotBlank(sqlString.toString())) {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseQuery) {
                BaseQuery query = (BaseQuery) params;
                query.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
            } else {
                log.error("当前mapper错误，没有BaseQuery参数，但是却使用了DataScope注解");
            }
        }
    }

    /**
     * 拼接权限sql前先清空params.dataScope参数防止注入
     */
    private void clearDataScope(final JoinPoint joinPoint) {
        Object params = joinPoint.getArgs()[0];
        if (StringUtils.isNotNull(params) && params instanceof BaseQuery) {
            BaseQuery query = (BaseQuery) params;
            query.getParams().put(DATA_SCOPE, "");
        }
    }
}
