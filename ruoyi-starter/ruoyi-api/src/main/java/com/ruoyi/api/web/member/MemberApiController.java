package com.ruoyi.api.web.member;

import com.ruoyi.api.service.TokenService;
import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.core.domain.LoginUser;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.entity.MemberLoginInfo;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.service.IMemberInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberApiController extends ApiController {

    @Autowired
    private IMemberInfoService memberInfoService;

    @Autowired
    private TokenService tokenService;

    /**
     * 更新会员信息
     */
    @PutMapping
    public R<MemberLoginInfo> updateMemberInfo(@RequestBody MemberInfoEntity memberInfo) {
        memberInfo.setId(SecurityUtils.getUserId());
        memberInfoService.updateMemberInfo(memberInfo);
        LoginUser<MemberLoginInfo> loginUser = SecurityUtils.getLoginUser();
        MemberLoginInfo memberLoginInfo = new MemberLoginInfo();
        memberInfo = memberInfoService.selectMemberInfoById(memberInfo.getId());
        BeanUtils.copyProperties(memberInfo, memberLoginInfo);
        loginUser.setUser(memberLoginInfo);
        tokenService.setLoginUser(loginUser);
        return R.ok(memberLoginInfo);
    }
}
