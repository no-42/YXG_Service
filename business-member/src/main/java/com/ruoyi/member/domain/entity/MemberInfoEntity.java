package com.ruoyi.member.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.IdEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 会员信息对象 member_info
 * 
 * @author thetbw
 * @date 2022-10-18
 */
@Getter
@Setter
@ToString
@TableName(value = "member_info",schema = "member")
public class MemberInfoEntity extends IdEntity {
    
    /**
     *  用户名称 
     */
    @TableField("name")
    @Excel(name = "用户名称")
    private String name;
    
    /**
     *  用户手机号 
     */
    @TableField("phone")
    @Excel(name = "用户手机号")
    private String phone;
    
    /**
     *  用户头像url 
     */
    @TableField("avatar")
    private String avatar;
    
    @TableField("create_time")
    private Date createTime;

    
}
