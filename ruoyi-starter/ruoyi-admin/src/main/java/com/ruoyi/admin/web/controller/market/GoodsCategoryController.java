package com.ruoyi.admin.web.controller.market;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.market.domain.entity.GoodsCategoryEntity;
import com.ruoyi.market.service.IGoodsCategoryService;
import com.ruoyi.market.domain.query.GoodsCategoryQuery;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品分类Controller
 *
 * @author thetbw
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/market/category")
public class GoodsCategoryController extends BaseController {

    @Autowired
    private IGoodsCategoryService goodsCategoryService;

    /**
     * 查询商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('market:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsCategoryQuery query) {
        startPage();
        List<GoodsCategoryEntity> list = goodsCategoryService.selectGoodsCategoryList(query);
        return getDataTable(list);
    }

    /**
     * 导出商品分类列表
     */
    @PreAuthorize("@ss.hasPermi('market:category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsCategoryQuery query) {
        List<GoodsCategoryEntity> list = goodsCategoryService.selectGoodsCategoryList(query);
        ExcelUtil<GoodsCategoryEntity> util = new ExcelUtil<>(GoodsCategoryEntity.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(goodsCategoryService.selectGoodsCategoryById(id));
    }

    /**
     * 新增商品分类
     */
    @PreAuthorize("@ss.hasPermi('market:category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsCategoryEntity goodsCategory) {
        return toAjax(goodsCategoryService.insertGoodsCategory(goodsCategory));
    }

    /**
     * 修改商品分类
     */
    @PreAuthorize("@ss.hasPermi('market:category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsCategoryEntity goodsCategory) {
        return toAjax(goodsCategoryService.updateGoodsCategory(goodsCategory));
    }

    /**
     * 删除商品分类
     */
    @PreAuthorize("@ss.hasPermi('market:category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(goodsCategoryService.deleteGoodsCategoryByIds(ids));
    }
}
