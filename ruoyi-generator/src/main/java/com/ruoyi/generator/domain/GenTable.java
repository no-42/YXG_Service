package com.ruoyi.generator.domain;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;
import com.ruoyi.generator.constant.GenConstants;
import com.ruoyi.common.utils.StringUtils;

/**
 * 业务表 gen_table
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "gen_table", schema = "system")
public class GenTable extends IdDateEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @NotBlank(message = "表名称不能为空")
    @TableField("table_name")
    private String tableName;

    /**
     * 表模式
     */
    @NotBlank(message = "表模式不能为空")
    @TableField("schema_name")
    private String schemaName;

    /**
     * 表描述
     */
    @NotBlank(message = "表描述不能为空")
    @TableField("table_comment")
    private String tableComment;

    /**
     * 关联父表的表名
     */
    @TableField("sub_table_name")
    private String subTableName;

    /**
     * 本表关联父表的外键名
     */
    @TableField("sub_table_fk_name")
    private String subTableFkName;

    /**
     * 实体类名称(首字母大写)
     */
    @NotBlank(message = "实体类名称不能为空")
    @TableField("class_name")
    private String className;

    /**
     * 使用的模板（crud单表操作 tree树表操作 sub主子表操作）
     */
    @TableField("tpl_category")
    private String tplCategory;

    /**
     * 生成包路径
     */
    @NotBlank(message = "生成包路径不能为空")
    @TableField("package_name")
    private String packageName;

    /**
     * 生成模块名
     */
    @NotBlank(message = "生成模块名不能为空")
    @TableField("module_name")
    private String moduleName;

    /**
     * 生成业务名
     */
    @NotBlank(message = "生成业务名不能为空")
    @TableField("business_name")
    private String businessName;

    /**
     * 生成功能名
     */
    @NotBlank(message = "生成功能名不能为空")
    @TableField("function_name")
    private String functionName;

    /**
     * 生成作者
     */
    @NotBlank(message = "作者不能为空")
    @TableField("function_author")
    private String functionAuthor;

    /**
     * 生成代码方式（0zip压缩包 1自定义路径）
     */
    @TableField("gen_type")
    private String genType;

    /**
     * 生成路径（不填默认项目路径）
     */
    @TableField("gen_path")
    private String genPath;

    /**
     * 主键信息
     * TODO dto
     */
    private GenTableColumn pkColumn;

    /**
     * 子表信息
     * TODO dto
     */
    private GenTable subTable;

    /**
     * 表列信息
     */
    @Valid
    private List<GenTableColumn> columns;

    /**
     * 其它生成选项
     */
    @TableField("options")
    private String options;

    /**
     * 树编码字段
     */
    private String treeCode;

    /**
     * 树父编码字段
     */
    private String treeParentCode;

    /**
     * 树名称字段
     */
    private String treeName;

    /**
     * 上级菜单ID字段
     */
    private String parentMenuId;

    /**
     * 上级菜单名称字段
     */
    private String parentMenuName;


    public boolean isSub() {
        return isSub(this.tplCategory);
    }

    public static boolean isSub(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_SUB, tplCategory);
    }

    public boolean isTree() {
        return isTree(this.tplCategory);
    }

    public static boolean isTree(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_TREE, tplCategory);
    }

    public boolean isCrud() {
        return isCrud(this.tplCategory);
    }

    public static boolean isCrud(String tplCategory) {
        return tplCategory != null && StringUtils.equals(GenConstants.TPL_CRUD, tplCategory);
    }

    public boolean isSuperColumn(String javaField) {
        return isSuperColumn(this.tplCategory, javaField);
    }

    public static boolean isSuperColumn(String tplCategory, String javaField) {
        if (isTree(tplCategory)) {
            return StringUtils.equalsAnyIgnoreCase(javaField,
                    ArrayUtils.addAll(GenConstants.TREE_ENTITY, GenConstants.BASE_ENTITY));
        }
        return StringUtils.equalsAnyIgnoreCase(javaField, GenConstants.BASE_ENTITY);
    }
}