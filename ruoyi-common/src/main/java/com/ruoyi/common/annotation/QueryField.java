package com.ruoyi.common.annotation;

import java.lang.annotation.*;

/**
 * 使用此注解声明当前字段为一个查询字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QueryField {

    /**
     * 当前查询字段对应数据库，默认取字段名称
     */
    String column() default "";

    /**
     * 当前字段对比类型
     */
    CompareType type() default CompareType.EQ;

    ActiveCondition condition() default ActiveCondition.NOT_EMPTY;

    enum CompareType {
        EQ, NEQ, GT, LT, LIKE, GE, LE, IN, NIN
    }

    enum ActiveCondition {
        ALL, NOT_EMPTY, NOT_NULL
    }
}
