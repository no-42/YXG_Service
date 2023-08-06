package com.ruoyi.api.service.login.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.api.domain.resp.login.WebLoginWithName;
import com.ruoyi.api.mappers.LoginMapper;
import com.ruoyi.api.service.login.MemberLogin;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends ServiceImpl< LoginMapper,WebLoginWithName> implements MemberLogin {
}
