package com.ruoyi.api.domain.resp.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WechatSessionResp {
    private String openId;
    
    private String unionId;
    
    private String token;
    
    private MemberInfoResp memberInfo;
}
