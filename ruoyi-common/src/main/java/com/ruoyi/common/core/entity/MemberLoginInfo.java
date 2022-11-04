package com.ruoyi.common.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ruoyi.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录用户信息
 */
@Getter
@Setter
@ToString
public class MemberLoginInfo {

    /**
     * 会员id
     */
    private String id;

    /**
     *  用户名称 
     */
    private String name;

    /**
     *  用户手机号 
     */
    private String phone;

    /**
     *  用户头像url 
     */
    private String avatar;

    /**
     * 微信or支付宝的unionId
     */
    private String openId;
}
