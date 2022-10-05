package com.ruoyi.system.domain;

import com.ruoyi.common.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 缓存信息
 *
 * @author ruoyi
 */
@Getter
@Setter
@ToString
public class SysCache {
    /**
     * 缓存名称
     */
    private String name = "";

    /**
     * 缓存键名
     */
    private String key = "";

    /**
     * 缓存内容
     */
    private String value = "";

    /**
     * 备注
     */
    private String remark = "";

    public SysCache() {

    }

    public SysCache(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public SysCache(String name, String key, String value) {
        this.name = StringUtils.replace(name, ":", "");
        this.key = StringUtils.replace(key, name, "");
        this.value = value;
    }

}
