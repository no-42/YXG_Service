package com.ruoyi.api.web.login;

import com.ruoyi.api.domain.resp.login.WechatSessionResp;
import com.ruoyi.api.service.login.MemberLoginService;
import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Anonymous
@RequestMapping("/login")
public class LoginApiController extends ApiController {

    @Autowired
    private MemberLoginService memberLoginService;

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
    public R<WechatSessionResp> loginWithMobile(@RequestPart("mobile") String mobile,
                                                @RequestPart("password") String password,
                                                @RequestPart(value = "verifyCode", required = false) String verifyCode) {
        return R.ok(memberLoginService.loginWithMobile(mobile,password));
    }

    @GetMapping("/status")
    public R<WechatSessionResp> loginStatus() {
        return R.ok(memberLoginService.getLoginStatus());
    }

    @PostMapping("/alipay")
    public R<String> loginWithAlipay() {
        return R.fail("不支持的方法");
    }


}
