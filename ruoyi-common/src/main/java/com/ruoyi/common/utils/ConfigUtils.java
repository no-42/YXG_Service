package com.ruoyi.common.utils;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.spring.SpringUtils;

/**
 * 获取配置信息
 *
 * @author thetbw
 */
public class ConfigUtils {


    /**
     * 获取配置信息
     *
     * @param key 配置信息key
     * @return 结果
     */
    public static String getConfigByKey(String key) {
        return SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key));
    }

    /**
     * 获取配置信息
     *
     * @param key          配置信息key
     * @param defaultValue 当key不存在的时候的默认值
     * @return 结果
     */
    public static String getConfigByKey(String key, String defaultValue) {
        return SpringUtils.getBean(RedisCache.class).hasKey(key) ?
                SpringUtils.getBean(RedisCache.class).getCacheObject(getCacheKey(key)) : defaultValue;
    }


    private static String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
