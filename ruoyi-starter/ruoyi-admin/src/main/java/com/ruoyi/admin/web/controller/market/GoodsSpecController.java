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
import com.ruoyi.market.domain.entity.GoodsSpecEntity;
import com.ruoyi.market.service.IGoodsSpecService;
import com.ruoyi.market.domain.query.GoodsSpecQuery;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商品规格Controller
 *
 * @author thetbw
 * @date 2022-11-04
 */
@RestController
@RequestMapping("/market/spec")
public class GoodsSpecController extends BaseController {

    @Autowired
    private IGoodsSpecService goodsSpecService;

    /**
     * 查询商品规格列表
     */
    @PreAuthorize("@ss.hasPermi('market:spec:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsSpecQuery query) {
        startPage();
        List<GoodsSpecEntity> list = goodsSpecService.selectGoodsSpecList(query);
        return getDataTable(list);
    }

    /**
     * 导出商品规格列表
     */
    @PreAuthorize("@ss.hasPermi('market:spec:export')")
    @Log(title = "商品规格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsSpecQuery query) {
        List<GoodsSpecEntity> list = goodsSpecService.selectGoodsSpecList(query);
        ExcelUtil<GoodsSpecEntity> util = new ExcelUtil<>(GoodsSpecEntity.class);
        util.exportExcel(response, list, "商品规格数据");
    }

    /**
     * 获取商品规格详细信息
     */
    @PreAuthorize("@ss.hasPermi('market:spec:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(goodsSpecService.selectGoodsSpecById(id));
    }

    /**
     * 新增商品规格
     */
    @PreAuthorize("@ss.hasPermi('market:spec:add')")
    @Log(title = "商品规格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsSpecEntity goodsSpec) {
        return toAjax(goodsSpecService.insertGoodsSpec(goodsSpec));
    }

    /**
     * 修改商品规格
     */
    @PreAuthorize("@ss.hasPermi('market:spec:edit')")
    @Log(title = "商品规格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsSpecEntity goodsSpec) {
        return toAjax(goodsSpecService.updateGoodsSpec(goodsSpec));
    }

    /**
     * 删除商品规格
     */
    @PreAuthorize("@ss.hasPermi('market:spec:remove')")
    @Log(title = "商品规格", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(goodsSpecService.deleteGoodsSpecByIds(ids));
    }
}
