package com.ruoyi.api.mappers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.ruoyi.api.domain.resp.login.WebLoginWithName;
import com.ruoyi.common.core.domain.BaseMapper;

public interface LoginMapper extends BaseMapper<WebLoginWithName> {
}
