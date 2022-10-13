package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户和岗位关联 sys_user_post
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_user_post", schema = "system")
public class SysUserPostEntity extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 岗位ID
     */
    @TableField("post_id")
    private String postId;

}
