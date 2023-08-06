package com.ruoyi.api.domain.resp.login;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jnr.ffi.annotations.In;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("login")
public class WebLoginWithName {  //用于在客户端进行登陆时获取数据对应页面为 LoginWithName.vue
        @TableId(type = IdType.AUTO)
        public Integer id;
        public Integer number;
        public Integer passwordId;
}
