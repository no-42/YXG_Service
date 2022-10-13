package com.ruoyi.system.domain.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.xss.Xss;

/**
 * 通知公告表 sys_notice
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_notice", schema = "system")
public class SysNoticeEntity extends IdDateEntity {

    /**
     * 公告标题
     */
    @Xss(message = "公告标题不能包含脚本字符")
    @NotBlank(message = "公告标题不能为空")
    @Size(min = 0, max = 50, message = "公告标题不能超过50个字符")
    @TableField("title")
    private String title;

    /**
     * 公告类型（1通知 2公告）
     */
    @TableField("type")
    private String type;

    /**
     * 公告内容
     */
    @TableField("content")
    private String content;

    /**
     * 公告状态（0正常 1关闭）
     */
    @TableField("enable")
    private Boolean enable;

}
