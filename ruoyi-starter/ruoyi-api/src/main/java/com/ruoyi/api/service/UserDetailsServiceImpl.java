package com.ruoyi.api.service;

import com.ruoyi.common.core.domain.LoginUser;
import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.service.IMemberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 这个没用到
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Autowired
    private IMemberInfoService memberInfoService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberInfoEntity memberInfo = memberInfoService.selectMemberInfoByPhone(username);
        LoginUser loginUser = new LoginUser();
        loginUser.setUser(memberInfo);
        return null;
    }
}
