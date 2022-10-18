package com.ruoyi.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.QueryField;
import com.ruoyi.common.core.domain.BaseQuery;
import com.ruoyi.common.utils.spring.SpringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class MybatisPlusUtils {


    public static <T, V extends BaseQuery> QueryWrapper<T> buildQueryWrapper(V query) {
        List<Field> fields = getAllFields(query.getClass());
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        for (Field field : fields) {
            QueryField queryField = field.getAnnotation(QueryField.class);
            if (queryField != null) {
                switch (queryField.type()) {
                    case EQ:
                        eq(wrapper, queryField, field, query);
                        break;
                    case NEQ:
                        neq(wrapper, queryField, field, query);
                        break;
                    case GT:
                        gt(wrapper, queryField, field, query);
                        break;
                    case LT:
                        lt(wrapper, queryField, field, query);
                        break;
                    case LIKE:
                        like(wrapper, queryField, field, query);
                        break;
                    case GE:
                        ge(wrapper, queryField, field, query);
                        break;
                    case LE:
                        le(wrapper, queryField, field, query);
                        break;
                    case IN:
                        in(wrapper, queryField, field, query);
                        break;
                    case NIN:
                        notIn(wrapper, queryField, field, query);
                        break;
                }
            }
        }
        return wrapper;
    }

    private static <T> T getFieldValue(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return (T) field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("获取 ’" + field.getName() + "‘的值异常", e);
        }
    }

    private static <V extends BaseQuery> void eq(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.eq(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void neq(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.ne(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void gt(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.gt(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void lt(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.lt(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void like(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.like(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), "_" + getFieldValue(field, query) + "_");
    }

    private static <V extends BaseQuery> void ge(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.ge(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void le(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        wrapper.le(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), getFieldValue(field, query));
    }

    private static <V extends BaseQuery> void in(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            Collection<?> collection = getFieldValue(field, query);
            wrapper.in(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), collection);
        } else if (CharSequence.class.isAssignableFrom(field.getType())) {
            String s = getFieldValue(field, query);
            List<String> list = Arrays.asList(s.split(","));
            wrapper.in(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), list);
        } else {
            throw new RuntimeException(field.getName() + " 错误的数据类型,使用in必须为Collection或者CharSequence的子类");
        }
    }

    private static <V extends BaseQuery> void notIn(QueryWrapper<?> wrapper, QueryField queryField, Field field, V query) {
        if (Collection.class.isAssignableFrom(field.getType())) {
            Collection<?> collection = getFieldValue(field, query);
            wrapper.notIn(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), collection);
        } else if (CharSequence.class.isAssignableFrom(field.getType())) {
            String s = getFieldValue(field, query);
            List<String> list = Arrays.asList(s.split(","));
            wrapper.notIn(StringUtils.isNotEmpty(queryField.column()) ? queryField.column() : field.getName(), list);
        } else {
            throw new RuntimeException(field.getName() + " 错误的数据类型,使用notIn必须为Collection或者CharSequence的子类");
        }
    }


    private static final Map<Class<?>, List<Field>> cache = new Hashtable<>();

    private static List<Field> getAllFields(Class<?> query) {
        if (!SpringUtils.isDev()) {
            if (cache.containsKey(query)) {
                return cache.get(query);
            }
        }
        Class<?> superClass = query;
        List<Field> queryFields = new ArrayList<>();
        while (superClass != Object.class) {
            queryFields.addAll(Arrays.asList(query.getFields()));
            superClass = superClass.getSuperclass();
        }
        if (!SpringUtils.isDev()) {
            cache.put(query, queryFields);
        }
        return queryFields;
    }
}
