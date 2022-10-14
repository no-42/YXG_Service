package com.ruoyi.system.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户登录对象
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
public class LoginBody {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;
}
