package com.ruoyi.api.web.market;

import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.market.domain.dto.GoodsInfoDto;
import com.ruoyi.market.domain.entity.GoodsCategoryEntity;
import com.ruoyi.market.domain.entity.GoodsOriginEntity;
import com.ruoyi.market.domain.entity.GoodsSpecEntity;
import com.ruoyi.market.domain.entity.GoodsSupplierEntity;
import com.ruoyi.market.domain.query.GoodsInfoQuery;
import com.ruoyi.market.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Anonymous
@RequestMapping("/market")
public class MarketApiController extends ApiController {

    @Autowired
    private IGoodsCategoryService categoryService;

    @Autowired
    private IGoodsInfoService goodsInfoService;

    @Autowired
    private IGoodsOriginService originService;

    @Autowired
    private IGoodsSpecService specService;

    @Autowired
    private IGoodsSupplierService supplierService;

    @GetMapping("/category")
    public R<List<GoodsCategoryEntity>> getAllCategory() {
        return R.ok(categoryService.selectGoodsCategoryList(null));
    }

    @GetMapping("/spec")
    public R<List<GoodsSpecEntity>> getAllSpec() {
        return R.ok(specService.selectGoodsSpecList(null));
    }

    @GetMapping("/origin")
    public R<List<GoodsOriginEntity>> getAllOrigin() {
        return R.ok(originService.selectGoodsOriginList(null));
    }

    @GetMapping("/supplier")
    public R<List<GoodsSupplierEntity>> getAllSupplier() {
        return R.ok(supplierService.selectGoodsSupplierList(null));
    }


    @GetMapping("/goods")
    public R<List<GoodsInfoDto>> selectGoodsList(GoodsInfoQuery query) {
        startPage();
        List<GoodsInfoDto> value = goodsInfoService.selectGoodsInfoList(query);
        return R.ok(value);
    }

}
