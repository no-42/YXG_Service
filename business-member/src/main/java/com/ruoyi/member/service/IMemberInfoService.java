package com.ruoyi.member.service;

import java.util.List;

import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.domain.entity.MemberOpenEntity;
import com.ruoyi.member.domain.query.MemberInfoQuery;

/**
 * 会员信息Service接口
 *
 * @author thetbw
 * @date 2022-10-18
 */
public interface IMemberInfoService {
    /**
     * 查询会员信息
     *
     * @param id 会员信息主键
     * @return 会员信息
     */
    MemberInfoEntity selectMemberInfoById(String id);

    /**
     * 根据手机号查询用户信息
     * @param phone 手机号
     * @return 用户信息
     */
    MemberInfoEntity selectMemberInfoByPhone(String phone);

    /**
     * 查询会员信息列表
     *
     * @param query 查询条件
     * @return 会员信息集合
     */
    List<MemberInfoEntity> selectMemberInfoList(MemberInfoQuery query);

    /**
     * 新增会员信息
     *
     * @param memberInfo 会员信息
     * @return 结果
     */
    int insertMemberInfo(MemberInfoEntity memberInfo);

    /**
     * 修改会员信息
     *
     * @param memberInfo 会员信息
     * @return 结果
     */
    int updateMemberInfo(MemberInfoEntity memberInfo);

    /**
     * 批量删除会员信息
     *
     * @param ids 需要删除的会员信息主键集合
     * @return 结果
     */
    int deleteMemberInfoByIds(String[] ids);

    /**
     * 删除会员信息信息
     *
     * @param id 会员信息主键
     * @return 结果
     */
    int deleteMemberInfoById(String id);

    /**
     * 根据第三方信息查询会员
     */
    MemberInfoEntity selectMemberInfoByOpenInfo(String openType, String openId);


    /**
     * 根据第三方信息创建会员
     * @param memberInfo 会员信息
     * @param openInfo 第三方信息
     */
    MemberInfoEntity createMemberInfoWithOpenInfo(MemberInfoEntity memberInfo, MemberOpenEntity openInfo);
}
