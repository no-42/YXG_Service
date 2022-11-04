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
import com.ruoyi.market.domain.entity.GoodsSupplierEntity;
import com.ruoyi.market.service.IGoodsSupplierService;
import com.ruoyi.market.domain.query.GoodsSupplierQuery;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品供应商Controller
 *
 * @author thetbw
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/market/supplier")
public class GoodsSupplierController extends BaseController {

    @Autowired
    private IGoodsSupplierService goodsSupplierService;

    /**
     * 查询商品供应商列表
     */
    @PreAuthorize("@ss.hasPermi('market:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsSupplierQuery query) {
        startPage();
        List<GoodsSupplierEntity> list = goodsSupplierService.selectGoodsSupplierList(query);
        return getDataTable(list);
    }

    /**
     * 导出商品供应商列表
     */
    @PreAuthorize("@ss.hasPermi('market:supplier:export')")
    @Log(title = "商品供应商", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsSupplierQuery query) {
        List<GoodsSupplierEntity> list = goodsSupplierService.selectGoodsSupplierList(query);
        ExcelUtil<GoodsSupplierEntity> util = new ExcelUtil<>(GoodsSupplierEntity.class);
        util.exportExcel(response, list, "商品供应商数据");
    }

    /**
     * 获取商品供应商详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:supplier:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(goodsSupplierService.selectGoodsSupplierById(id));
    }

    /**
     * 新增商品供应商
     */
    @PreAuthorize("@ss.hasPermi('market:supplier:add')")
    @Log(title = "商品供应商", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsSupplierEntity goodsSupplier) {
        return toAjax(goodsSupplierService.insertGoodsSupplier(goodsSupplier));
    }

    /**
     * 修改商品供应商
     */
    @PreAuthorize("@ss.hasPermi('market:supplier:edit')")
    @Log(title = "商品供应商", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsSupplierEntity goodsSupplier) {
        return toAjax(goodsSupplierService.updateGoodsSupplier(goodsSupplier));
    }

    /**
     * 删除商品供应商
     */
    @PreAuthorize("@ss.hasPermi('market:supplier:remove')")
    @Log(title = "商品供应商", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(goodsSupplierService.deleteGoodsSupplierByIds(ids));
    }
}
