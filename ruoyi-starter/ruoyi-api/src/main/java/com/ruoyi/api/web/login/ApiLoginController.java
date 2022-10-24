package com.ruoyi.api.web.login;

import com.ruoyi.api.domain.resp.login.WechatSessionResp;
import com.ruoyi.api.service.login.MemberLoginService;
import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Anonymous
@RequestMapping("/login")
public class ApiLoginController extends ApiController {

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
        return R.ok(memberLoginService.wechatLogin(code,openId));
    }

    @PostMapping("/wechatSession")
    public R<WechatSessionResp> getWeChatSession(@RequestParam("code") String code) {
        return R.ok(memberLoginService.getWeChatSession(code));
    }

    @PostMapping("/alipay")
    public void loginWithAlipay() {

    }


}
