package com.ruoyi.common.core.entity;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.*;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.core.domain.IdDateEntity;
import com.ruoyi.common.core.entity.SysDeptEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.annotation.Excels;
import com.ruoyi.common.xss.Xss;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "sys_user", schema = "system")
public class SysUserEntity extends IdDateEntity {

    /**
     * 部门ID
     */
    @Excel(name = "部门编号", type = Type.IMPORT)
    @TableField("dept_id")
    private String deptId;

    /**
     * 用户账号
     */
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    @Excel(name = "登录名称")
    @TableField("user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    @Excel(name = "用户名称")
    @TableField("nick_name")
    private String nickName;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    @Excel(name = "用户邮箱")
    @TableField("email")
    private String email;

    /**
     * 手机号码
     */
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    @Excel(name = "手机号码")
    @TableField("phone")
    private String phone;

    /**
     * 用户性别
     */
    @Excel(name = "用户性别", readConverterExp = "0=男,1=女,2=未知")
    @TableField("sex")
    private String sex;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @TableField("password")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    @TableField("enable")
    private Boolean enable;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private Boolean delFlag;

    /**
     * 最后登录IP
     */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    @TableField("login_date")
    private Date loginDate;

    /**
     * 部门对象
     * TODO 放到dto
     */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    @TableField(exist = false)
    private SysDeptEntity dept;

    /**
     * 角色对象
     * TODO 放到DTO
     */
    @TableField(exist = false)
    private List<SysRoleEntity> roles;

    /**
     * 角色组
     * TODO 放到dto
     */
    @TableField(exist = false)
    private String[] roleIds;

    /**
     * 岗位组
     * TODO 放到dto
     */
    @TableField(exist = false)
    private String[] postIds;

    /**
     * 角色ID
     * TODO 放到dto
     */
    @TableField(exist = false)
    private String roleId;
    
    @JsonIgnore
    public boolean isAdmin(){
        return isAdmin(this.getUserName());
    }
   
    @JsonIgnore
    public static boolean isAdmin(String name){
        return "admin".equals(name);
    }

}
