package com.ruoyi.generator.domain;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.IdDateEntity;
import com.ruoyi.common.core.domain.IdEntity;
import com.ruoyi.common.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Locale;

/**
 * 代码生成业务字段表 gen_table_column
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
@TableName(value = "gen_table_column", schema = "system")
public class GenTableColumn extends IdDateEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 归属表编号
     */
    @TableField("table_id")
    private String tableId;

    /**
     * 列名称
     */
    @TableField("column_name")
    private String columnName;

    /**
     * 列描述
     */
    @TableField("column_comment")
    private String columnComment;

    /**
     * 列类型
     */
    @TableField("column_type")
    private String columnType;

    /**
     * JAVA类型
     */
    @TableField("java_type")
    private String javaType;

    /**
     * JAVA字段名
     */
    @NotBlank(message = "Java属性不能为空")
    @TableField("java_field")
    private String javaField;

    /**
     * 是否主键（1是）
     */
    @TableField("is_pk")
    private Boolean isPk;

    /**
     * 是否自增（1是）
     */
    @TableField("is_increment")
    private Boolean isIncrement;

    /**
     * 是否必填（1是）
     */
    @TableField("is_required")
    private Boolean isRequired;

    /**
     * 是否为插入字段（1是）
     */
    @TableField("is_insert")
    private Boolean isInsert;

    /**
     * 是否编辑字段（1是）
     */
    @TableField("is_edit")
    private Boolean isEdit;

    /**
     * 是否列表字段（1是）
     */
    @TableField("is_list")
    private Boolean isList;

    /**
     * 是否查询字段（1是）
     */
    @TableField("is_query")
    private Boolean isQuery;

    /**
     * 查询方式（EQ等于、NE不等于、GT大于、LT小于、LIKE模糊、BETWEEN范围）
     */
    @TableField("query_type")
    private String queryType;

    /**
     * 显示类型（input文本框、textarea文本域、select下拉框、checkbox复选框、radio单选框、datetime日期控件、image图片上传控件、upload文件上传控件、editor富文本控件）
     */
    @TableField("html_type")
    private String htmlType;

    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    public boolean isSuperColumn() {
        return isSuperColumn(this.javaField);
    }

    public static boolean isSuperColumn(String javaField) {
        return StringUtils.equalsAnyIgnoreCase(javaField,
                // BaseEntity
                "createBy", "createTime", "updateBy", "updateTime", "remark",
                // TreeEntity
                "parentName", "parentId", "orderNum", "ancestors");
    }

    public boolean isUsableColumn() {
        return isUsableColumn(javaField);
    }

    public static boolean isUsableColumn(String javaField) {
        // isSuperColumn()中的名单用于避免生成多余Domain属性，若某些属性在生成页面时需要用到不能忽略，则放在此处白名单
        return StringUtils.equalsAnyIgnoreCase(javaField, "parentId", "orderNum", "remark");
    }

    public String readConverterExp() {
        String remarks = StringUtils.substringBetween(this.columnComment, "（", "）");
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(remarks)) {
            for (String value : remarks.split(" ")) {
                if (StringUtils.isNotEmpty(value)) {
                    Object startStr = value.subSequence(0, 1);
                    String endStr = value.substring(1);
                    sb.append("").append(startStr).append("=").append(endStr).append(",");
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        } else {
            return this.columnComment;
        }
    }

    public String capJavaField() {
        return javaField.substring(0, 1).toUpperCase() + javaField.substring(1);
    }
}
