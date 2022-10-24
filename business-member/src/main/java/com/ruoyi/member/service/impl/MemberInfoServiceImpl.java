package com.ruoyi.member.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.domain.entity.MemberOpenEntity;
import com.ruoyi.member.domain.query.MemberInfoQuery;
import com.ruoyi.member.mapper.MemberOpenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.member.mapper.MemberInfoMapper;
import com.ruoyi.member.service.IMemberInfoService;
import org.springframework.util.Assert;

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

    @Autowired
    private MemberOpenMapper memberOpenMapper;

    @Override
    public MemberInfoEntity selectMemberInfoById(String id) {
        return memberInfoMapper.selectById(id);
    }

    @Override
    public MemberInfoEntity selectMemberInfoByPhone(String phone) {
        QueryWrapper<MemberInfoEntity> memberInfoSelect = new QueryWrapper<>();
        memberInfoSelect.eq("phone", phone);
        return memberInfoMapper.selectOne(memberInfoSelect);
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

    @Override
    public MemberInfoEntity selectMemberInfoByOpenInfo(String openType, String openId) {
        QueryWrapper<MemberOpenEntity> memberOpenSelect = new QueryWrapper<>();
        memberOpenSelect.eq("open_type", openType);
        memberOpenSelect.eq("open_id", openId);
        MemberOpenEntity openInfo = memberOpenMapper.selectOne(memberOpenSelect);
        if (openInfo == null) {
            return null;
        }
        return memberInfoMapper.selectById(openInfo.getMemberId());
    }

    @Override
    public MemberInfoEntity createMemberInfoWithOpenInfo(MemberInfoEntity memberInfo, MemberOpenEntity openInfo) {
        memberInfo.setCreateTime(new Date());
        memberInfoMapper.insert(memberInfo);
        openInfo.setMemberId(memberInfo.getId());
        openInfo.setCreateTime(new Date());
        memberOpenMapper.insert(openInfo);
        return memberInfo;
    }
}
