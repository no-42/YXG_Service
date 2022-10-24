package com.ruoyi.api.service.login;

import com.fasterxml.jackson.databind.JsonNode;
import com.ruoyi.api.domain.resp.login.MemberInfoResp;
import com.ruoyi.api.domain.resp.login.WechatSessionResp;
import com.ruoyi.api.service.TokenService;
import com.ruoyi.common.core.domain.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.JSONUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.api.WechatUtils;
import com.ruoyi.member.constant.MemberConstant;
import com.ruoyi.common.core.entity.MemberLoginInfo;
import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.domain.entity.MemberOpenEntity;
import com.ruoyi.member.service.IMemberInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class MemberLoginService {

    @Autowired
    private IMemberInfoService memberInfoService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private HttpServletRequest request;

    public WechatSessionResp login(String name, String phone, String password, String openId) {
        
        MemberInfoEntity memberInfo = memberInfoService.selectMemberInfoByPhone(phone);
        LoginUser<MemberLoginInfo> loginUser = new LoginUser<>();
        MemberLoginInfo memberLoginInfo = new MemberLoginInfo();
        BeanUtils.copyProperties(memberInfo,memberLoginInfo);
        memberLoginInfo.setOpenId(openId);
        loginUser.setUser(memberLoginInfo);
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
        tokenService.createToken(loginUser);
        return buildLoginInfo(loginUser);
    }

    private WechatSessionResp buildLoginInfo(LoginUser<MemberLoginInfo> loginUser) {
        WechatSessionResp wechatSessionResp = new WechatSessionResp();
        wechatSessionResp.setOpenId(loginUser.getUser().getOpenId());
        wechatSessionResp.setToken(loginUser.getToken());
        MemberInfoResp memberInfoResp = new MemberInfoResp();
        BeanUtils.copyProperties(loginUser.getUser(), memberInfoResp);
        wechatSessionResp.setMemberInfo(memberInfoResp);
        return wechatSessionResp;
    }

    public WechatSessionResp getWeChatSession(String code) {
        if (SecurityUtils.getLoginUser() != null) {
            //当前用户已经登录，构造登录信息
            return buildLoginInfo(SecurityUtils.getLoginUser());
        }
        String result = WechatUtils.jsCode2session(code);
        JsonNode node = JSONUtils.parseAsJsonNode(result);
        if (node.get("errcode") != null) {
            throw new ServiceException("小程序自动登录异常:" + node.get("errmsg").asText());
        }
        String openId = node.get("openid").asText();
        //尝试登录
        MemberInfoEntity memberInfo = memberInfoService.selectMemberInfoByOpenInfo(MemberConstant.MEMBER_OPEN_TYPE_WECHAT, openId);
        if (memberInfo != null) {
            //如果openId已经存在
            return login(memberInfo.getName(), memberInfo.getPhone(), memberInfo.getPhone(), openId);
        }
        //用户不存在
        WechatSessionResp wechatSessionResp = new WechatSessionResp();
        wechatSessionResp.setOpenId(openId);
        wechatSessionResp.setUnionId(node.get("unionid") != null ? node.get("unionid").asText() : null);
        return wechatSessionResp;
    }


    /**
     * 微信登录
     *
     * @param code 登录授权码
     * @return 用户token
     */
    @Transactional(rollbackFor = Exception.class)
    public WechatSessionResp wechatLogin(String code, String openId) {
        String result = WechatUtils.getPhoneNumber(code);
        log.error(result);
        JsonNode jsonNode = JSONUtils.parseAsJsonNode(result);
        JsonNode phoneInfo = jsonNode.get("phone_info");
        String phone = phoneInfo.get("purePhoneNumber").asText();
        if (!"86".equals(phoneInfo.get("countryCode").asText())){
            throw new RuntimeException("不支持非国内的手机号");
        }
        MemberInfoEntity memberInfo = memberInfoService.selectMemberInfoByOpenInfo(MemberConstant.MEMBER_OPEN_TYPE_WECHAT, openId);
        if (memberInfo != null) {
            //如果openId已经存在
            return login(memberInfo.getName(), memberInfo.getPhone(), memberInfo.getPhone(), openId);
        }
        memberInfo = memberInfoService.selectMemberInfoByPhone(phone);
        if (memberInfo != null) {
            return login(memberInfo.getName(), memberInfo.getPhone(), memberInfo.getPhone(), openId);
        }
        //创建用户
        MemberOpenEntity open = new MemberOpenEntity();
        open.setOpenId(openId);
        open.setOpenType(MemberConstant.MEMBER_OPEN_TYPE_WECHAT);
        memberInfo = new MemberInfoEntity();
        memberInfo.setPhone(phone);
        memberInfo.setName(phone);
        memberInfo = memberInfoService.createMemberInfoWithOpenInfo(memberInfo, open);
        return login(memberInfo.getName(), memberInfo.getPhone(), memberInfo.getPhone(), openId);
    }
}
