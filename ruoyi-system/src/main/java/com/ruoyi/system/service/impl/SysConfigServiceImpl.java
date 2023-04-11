package com.ruoyi.system.service.impl;

import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;

import com.ruoyi.system.domain.query.SysConfigQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.entity.SysConfigEntity;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 参数配置 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {
    @Autowired
    private SysConfigMapper configMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingConfigCache();
    }

    @Override
    public SysConfigEntity selectConfigById(String configId) {
        SysConfigEntity config = new SysConfigEntity();
        config.setId(configId);
        return configMapper.selectConfig(config);
    }

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数key
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
//        String configValue = Convert.toStr(redisCache.getCacheObject(getCacheKey(configKey)));
//        if (StringUtils.isNotEmpty(configValue)) {
//            return configValue;
//        }
        SysConfigEntity config = new SysConfigEntity();
        config.setKey(configKey);
        SysConfigEntity retConfig = configMapper.selectConfig(config);
        if (StringUtils.isNotNull(retConfig)) {
//            redisCache.setCacheObject(getCacheKey(configKey), retConfig.getValue());
            return retConfig.getValue();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }

    @Override
    public List<SysConfigEntity> selectConfigList(SysConfigQuery query) {
        return configMapper.selectConfigList(query);
    }

    @Override
    public int insertConfig(SysConfigEntity config) {
        int row = configMapper.insertConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(config.getKey()), config.getValue());
        }
        return row;
    }

    @Override
    public int updateConfig(SysConfigEntity config) {
        int row = configMapper.updateConfig(config);
        if (row > 0) {
            redisCache.setCacheObject(getCacheKey(config.getKey()), config.getValue());
        }
        return row;
    }

    @Override
    public void deleteConfigByIds(String[] configIds) {
        for (String configId : configIds) {
            SysConfigEntity config = selectConfigById(configId);
            if (StringUtils.equals(UserConstants.YES, config.getType())) {
                throw new ServiceException(String.format("内置参数【%1$s】不能删除 ", config.getKey()));
            }
            configMapper.deleteConfigById(configId);
            redisCache.deleteObject(getCacheKey(config.getKey()));
        }
    }

    @Override
    public void loadingConfigCache() {
        List<SysConfigEntity> configsList = configMapper.selectConfigList(new SysConfigQuery());
        for (SysConfigEntity config : configsList) {
            redisCache.setCacheObject(getCacheKey(config.getKey()), config.getValue());
        }
    }

    @Override
    public void clearConfigCache() {
        Collection<String> keys = redisCache.keys(CacheConstants.SYS_CONFIG_KEY + "*");
        redisCache.deleteObject(keys);
    }

    @Override
    public void resetConfigCache() {
        clearConfigCache();
        loadingConfigCache();
    }

    @Override
    public String checkConfigKeyUnique(SysConfigEntity config) {
        String configId = StringUtils.isEmpty(config.getId()) ? null : config.getId();
        SysConfigEntity info = configMapper.checkConfigKeyUnique(config.getKey());
        if (StringUtils.isNotNull(info) && !info.getId().equals(configId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
    
    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
