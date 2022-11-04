package com.ruoyi.common.utils;

import com.ruoyi.common.core.domain.LoginUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.ServiceException;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {
    /**
     * 用户ID
     **/
    public static String getUserId() {
        try {
            return getLoginUser().getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("获取用户ID异常", HttpStatus.UNAUTHORIZED);
        }
    }
    

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUsername();
        } catch (Exception e) {
            throw new ServiceException("获取用户账户异常", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取用户
     **/
    public static <T> LoginUser<T> getLoginUser() {
        if (getAuthentication().isAuthenticated()){
            Authentication authentication = getAuthentication();
            if (authentication instanceof AnonymousAuthenticationToken){
                return null;
            }
            return (LoginUser<T>) authentication.getPrincipal();
        }else {
            return null;
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword     真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     *
     * @param name 用户ID
     * @return 结果
     */
    public static boolean isAdmin(String name) {
        return "admin".equals(name);
    }

    public static boolean isAdmin() {
        return isAdmin(getLoginUser().getUsername());
    }
}
