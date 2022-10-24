package com.ruoyi.api.domain.resp.login;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 会员信息
 */
@Getter
@Setter
@ToString
public class MemberInfoResp {

    private String name;

    private String phone;

    private String avatar;
}
