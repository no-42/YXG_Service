package com.ruoyi.api.web.login;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.ruoyi.api.domain.resp.login.WebLoginWithName;
import com.ruoyi.api.domain.resp.login.WechatSessionResp;
import com.ruoyi.api.service.login.Impl.LoginService;
import com.ruoyi.api.service.login.MemberLoginService;
import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Anonymous
@RequestMapping("/login")
public class LoginApiController extends ApiController {

    @Autowired
    private MemberLoginService memberLoginService;

    @Autowired
    private LoginService loginService;

    @PostMapping
    public R<String> defaultLogin() {
        return R.ok(null);
    }

    @PostMapping("/wechat")
    public R<WechatSessionResp> loginWithWeChat(@RequestParam("code") String code,
                                                @RequestParam("openId") String openId) {
        //TODO 关于前端undefined 的处理
        return R.ok(memberLoginService.wechatLogin(code, openId));
    }

    @PostMapping("/wechatSession")
    public R<WechatSessionResp> getWeChatSession(@RequestParam("code") String code) {
        return R.ok(memberLoginService.getWeChatSession(code));
    }

    @PostMapping("/mobile")
    public R<WechatSessionResp> loginWithMobile(@RequestBody JsonNode json) {
        return R.ok(memberLoginService.loginWithMobile(json.get("mobile").asText(), json.get("password").asText()));
    }

    @GetMapping("/status")
    public R<WechatSessionResp> loginStatus() {
        return R.ok(memberLoginService.getLoginStatus());
    }

    @PostMapping("/alipay")
    public R<String> loginWithAlipay() {
        return R.fail("不支持的方法");
    }

    @PostMapping("/WithName")
    public R<WebLoginWithName> loginWithName(@PathVariable("number")String number,@PathVariable("password") String passwprd){
        LambdaQueryWrapper<WebLoginWithName> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(WebLoginWithName::getPasswordId,passwprd);
        lambdaQueryWrapper.eq(WebLoginWithName::getNumber,number);
        loginService.getOne(lambdaQueryWrapper);
        return R.ok();
    }


}
