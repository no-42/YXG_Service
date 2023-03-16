package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.query.SysPostQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.entity.SysPostEntity;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysPostService;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysPostServiceImpl implements ISysPostService {
    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Override
    public List<SysPostEntity> selectPostList(SysPostQuery query) {
        return postMapper.selectPostList(query);
    }

    @Override
    public List<SysPostEntity> selectPostAll() {
        return postMapper.selectPostAll();
    }

    @Override
    public SysPostEntity selectPostById(String postId) {
        return postMapper.selectPostById(postId);
    }

    @Override
    public List<String> selectPostListByUserId(String userId) {
        return postMapper.selectPostListByUserId(userId);
    }

    @Override
    public String checkPostNameUnique(SysPostEntity post) {
        SysPostEntity info = postMapper.checkPostNameUnique(post.getName());
        if (StringUtils.isNotNull(info)) {
            return info.getId().equals(post.getId()) ? UserConstants.UNIQUE : UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public String checkPostCodeUnique(SysPostEntity post) {
        SysPostEntity info = postMapper.checkPostCodeUnique(post.getCode());
        if (StringUtils.isNotNull(info) && !info.getId().equals(post.getId())) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int countUserPostById(String postId) {
        return userPostMapper.countUserPostById(postId);
    }

    @Override
    public int deletePostById(String postId) {
        return postMapper.deletePostById(postId);
    }

    @Override
    public int deletePostByIds(String[] postIds) {
        for (String postId : postIds) {
            SysPostEntity post = selectPostById(postId);
            if (countUserPostById(postId) > 0) {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getName()));
            }
        }
        return postMapper.deletePostByIds(postIds);
    }

    @Override
    public int insertPost(SysPostEntity post) {
        return postMapper.insertPost(post);
    }

    @Override
    public int updatePost(SysPostEntity post) {
        return postMapper.updatePost(post);
    }
}
