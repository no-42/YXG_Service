package com.ruoyi.admin.web.controller.market;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.market.domain.dto.GoodsInfoDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.admin.web.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.market.domain.entity.GoodsInfoEntity;
import com.ruoyi.market.service.IGoodsInfoService;
import com.ruoyi.market.domain.query.GoodsInfoQuery;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 *
 * @author thetbw
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/market/goods")
public class GoodsInfoController extends BaseController {

    @Autowired
    private IGoodsInfoService goodsInfoService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('market:goods:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsInfoQuery query) {
        startPage();
        List<GoodsInfoDto> list = goodsInfoService.selectGoodsInfoList(query);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('market:goods:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsInfoQuery query) {
        List<GoodsInfoDto> list = goodsInfoService.selectGoodsInfoList(query);
        ExcelUtil<GoodsInfoDto> util = new ExcelUtil<>(GoodsInfoDto.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:goods:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") String categoryId) {
        return AjaxResult.success(goodsInfoService.selectGoodsInfoByCategoryId(categoryId));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('market:goods:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsInfoEntity goodsInfo) {
        return toAjax(goodsInfoService.insertGoodsInfo(goodsInfo));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('market:goods:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsInfoEntity goodsInfo) {
        return toAjax(goodsInfoService.updateGoodsInfo(goodsInfo));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('market:goods:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable String[] categoryIds) {
        return toAjax(goodsInfoService.deleteGoodsInfoByCategoryIds(categoryIds));
    }
}
