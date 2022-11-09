package com.ruoyi.market.service.impl;

import java.util.Arrays;
import java.util.List;

import com.ruoyi.market.domain.dto.GoodsInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.market.mapper.GoodsInfoMapper;
import com.ruoyi.market.domain.entity.GoodsInfoEntity;
import com.ruoyi.market.domain.query.GoodsInfoQuery;
import com.ruoyi.market.service.IGoodsInfoService;

/**
 * 商品信息Service业务层处理
 *
 * @author thetbw
 * @date 2022-11-04
 */
@Service
public class GoodsInfoServiceImpl implements IGoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Override
    public GoodsInfoDto selectGoodsInfoByCategoryId(String categoryId) {
        return goodsInfoMapper.selectGoodsInfoDtoById(categoryId);
    }

    @Override
    public List<GoodsInfoDto> selectGoodsInfoList(GoodsInfoQuery query) {
        return goodsInfoMapper.selectGoodsInfoDtoList(query);
    }

    @Override
    public int insertGoodsInfo(GoodsInfoEntity goodsInfo) {
        return goodsInfoMapper.insert(goodsInfo);
    }

    @Override
    public int updateGoodsInfo(GoodsInfoEntity goodsInfo) {
        return goodsInfoMapper.updateById(goodsInfo);
    }

    @Override
    public int deleteGoodsInfoByCategoryIds(String[] categoryIds) {
        return goodsInfoMapper.deleteBatchIds(Arrays.asList(categoryIds));
    }

    @Override
    public int deleteGoodsInfoByCategoryId(String categoryId) {
        return goodsInfoMapper.deleteById(categoryId);
    }
}
