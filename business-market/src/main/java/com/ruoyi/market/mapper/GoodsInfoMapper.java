package com.ruoyi.market.mapper;

import com.ruoyi.market.domain.dto.GoodsInfoDto;
import com.ruoyi.market.domain.entity.GoodsInfoEntity;
import com.ruoyi.common.core.domain.BaseMapper;
import com.ruoyi.market.domain.query.GoodsInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品信息Mapper接口
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@Mapper
public interface GoodsInfoMapper extends BaseMapper<GoodsInfoEntity> {


    /**
     * 查询商品信息列表
     * @param query 查询条件
     * @return 结果
     */
    List<GoodsInfoDto> selectGoodsInfoDtoList(GoodsInfoQuery query);

    /**
     * 根据id查询商品信息
     * @param id 商品id
     * @return 结果
     */
    GoodsInfoDto selectGoodsInfoDtoById(String id);
}
