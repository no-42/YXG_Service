package com.ruoyi.system.domain.entity;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单权限表 sys_menu
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_menu", schema = "system")
public class SysMenuEntity extends IdDateEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    @TableField("name")
    private String name;


    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private String parentId;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    @TableField("order_num")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    @TableField("path")
    private String path;

    /**
     * 组件路径
     */
    @Size(min = 0, max = 200, message = "组件路径不能超过255个字符")
    @TableField("component")
    private String component;

    /**
     * 路由参数
     */
    @TableField("query")
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    @TableField("is_frame")
    private Boolean isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @TableField("is_cache")
    private Boolean isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    @NotBlank(message = "菜单类型不能为空")
    @TableField("type")
    private String type;

    /**
     * 显示状态
     */
    @TableField("visible")
    private Boolean visible;

    /**
     * 状态
     */
    @TableField("enable")
    private Boolean enable;

    /**
     * 权限字符串
     */
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    @TableField("perms")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysMenuEntity> children = new ArrayList<>();


    /**
     * 父菜单名称
     * TODO 这个放到dto中
     */
    @TableField(exist = false)
    private String parentName;


}
