package com.ruoyi.common.annotation;

/**
 * 使用此注解声明当前字段为一个查询字段
 */
public @interface QueryField {
    
    /**
     * 当前字段对比类型
     */
    CompareType type() default CompareType.EQUALS;

    enum CompareType {
        EQUALS, GT, LT, LIKE, GT_EQUALS, LT_EQUALS, IN
    }
}
