package com.ruoyi.member.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.domain.query.MemberInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.member.mapper.MemberInfoMapper;
import com.ruoyi.member.service.IMemberInfoService;

/**
 * 会员信息Service业务层处理
 * 
 * @author thetbw
 * @date 2022-10-18
 */
@Service
public class MemberInfoServiceImpl implements IMemberInfoService {
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    @Override
    public MemberInfoEntity selectMemberInfoById(String id) {
        return memberInfoMapper.selectById(id);
    }
    
    @Override
    public List<MemberInfoEntity> selectMemberInfoList(MemberInfoQuery query) {
        return memberInfoMapper.selectListByQuery(query);
    }



    @Override
    public int insertMemberInfo(MemberInfoEntity memberInfo) {
        return memberInfoMapper.insert(memberInfo);
    }
    
    @Override
    public int updateMemberInfo(MemberInfoEntity memberInfo) {
        return memberInfoMapper.updateById(memberInfo);
    }
    
    @Override
    public int deleteMemberInfoByIds(String[] ids) {
        return memberInfoMapper.deleteBatchIds(Arrays.asList(ids));
    }
    
    @Override
    public int deleteMemberInfoById(String id) {
        return memberInfoMapper.deleteById(id);
    }
}
