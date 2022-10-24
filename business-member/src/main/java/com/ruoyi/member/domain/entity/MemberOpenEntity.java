package com.ruoyi.member.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.core.domain.IdDateEntity;

import java.util.Date;

/**
 * 第三方会员对象 member_open
 *
 * @author thetbw
 * @date 2022-10-21
 */
@Getter
@Setter
@ToString
@TableName(value = "member_open",schema = "member")
public class MemberOpenEntity extends IdEntity {
    

    /**
     *  所属会员id 
     */
    @TableField("member_id")
    private String memberId;

    /**
     *  第三方平台类型 
     */
    @TableField("open_type")
    private String openType;

    /**
     *  第三方平台的用户id 
     */
    @TableField("open_id")
    private String openId;

    /**
     *  第三方平台的授权token 
     */
    @TableField("auth_token")
    private String authToken;

    /**
     *  扩展信息 
     */
    @TableField("extend_info")
    private String extendInfo;


    @TableField("create_time")
    private Date createTime;


}
