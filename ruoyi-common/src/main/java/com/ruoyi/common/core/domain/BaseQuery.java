package com.ruoyi.common.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 基本查询类
 *
 * @author thetbw
 */
@Getter
@Setter
@ToString
public class BaseQuery {
    private Map<String, Object> params = new HashMap<>();
}
