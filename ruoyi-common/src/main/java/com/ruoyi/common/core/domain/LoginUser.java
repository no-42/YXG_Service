package com.ruoyi.common.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.core.entity.MemberLoginInfo;
import com.ruoyi.common.core.entity.SysUserEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
public class LoginUser<T> implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private T user;


    public LoginUser() {
    }

    public LoginUser(T user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public LoginUser(String userId, T user, Set<String> permissions) {
        this.userId = userId;
        this.user = user;
        this.permissions = permissions;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        if (user instanceof SysUserEntity) {
            return ((SysUserEntity) user).getPassword();
        } else if (user instanceof MemberLoginInfo) {
            return ((MemberLoginInfo) user).getName();
        }
        return null;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        if (user instanceof SysUserEntity) {
            return ((SysUserEntity) user).getUserName();
        } else if (user instanceof MemberLoginInfo) {
            return ((MemberLoginInfo) user).getName();
        }
        return null;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getUserId() {
        if (user instanceof MemberLoginInfo){
            return ((MemberLoginInfo) user).getId();
        }else if (user instanceof SysUserEntity){
            return ((SysUserEntity) user).getId();
        }else {
            throw new RuntimeException("未知的用户id");
        }
    }
}
