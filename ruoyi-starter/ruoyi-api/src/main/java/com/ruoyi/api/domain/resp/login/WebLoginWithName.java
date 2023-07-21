package com.ruoyi.api.domain.resp.login;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebLoginWithName {  //用于在客户端进行登陆时获取数据对应页面为 LoginWithName.vue
        public Integer phoneNumber;
        public Integer emailNumber;
        public Integer password;
}
