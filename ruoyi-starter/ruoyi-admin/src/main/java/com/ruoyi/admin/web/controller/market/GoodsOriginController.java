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
import com.ruoyi.market.domain.entity.GoodsOriginEntity;
import com.ruoyi.market.service.IGoodsOriginService;
import com.ruoyi.market.domain.query.GoodsOriginQuery;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品产地Controller
 * 
 * @author thetbw
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/market/origin")
public class GoodsOriginController extends BaseController {
    
    @Autowired
    private IGoodsOriginService goodsOriginService;

    /**
     * 查询商品产地列表
     */
    @PreAuthorize("@ss.hasPermi('market:origin:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsOriginQuery query) {
        startPage();
        List<GoodsOriginEntity> list = goodsOriginService.selectGoodsOriginList(query);
        return getDataTable(list);
    }

    /**
     * 导出商品产地列表
     */
    @PreAuthorize("@ss.hasPermi('market:origin:export')")
    @Log(title = "商品产地", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsOriginQuery query) {
        List<GoodsOriginEntity> list = goodsOriginService.selectGoodsOriginList(query);
        ExcelUtil<GoodsOriginEntity> util = new ExcelUtil<GoodsOriginEntity>(GoodsOriginEntity.class);
        util.exportExcel(response, list, "商品产地数据");
    }

    /**
     * 获取商品产地详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:origin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(goodsOriginService.selectGoodsOriginById(id));
    }

    /**
     * 新增商品产地
     */
    @PreAuthorize("@ss.hasPermi('market:origin:add')")
    @Log(title = "商品产地", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsOriginEntity goodsOrigin) {
        return toAjax(goodsOriginService.insertGoodsOrigin(goodsOrigin));
    }

    /**
     * 修改商品产地
     */
    @PreAuthorize("@ss.hasPermi('market:origin:edit')")
    @Log(title = "商品产地", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsOriginEntity goodsOrigin) {
        return toAjax(goodsOriginService.updateGoodsOrigin(goodsOrigin));
    }

    /**
     * 删除商品产地
     */
    @PreAuthorize("@ss.hasPermi('market:origin:remove')")
    @Log(title = "商品产地", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(goodsOriginService.deleteGoodsOriginByIds(ids));
    }
}
