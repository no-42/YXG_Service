package com.ruoyi.admin.web.controller.order;

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
import com.ruoyi.order.domain.entity.OrderInfoEntity;
import com.ruoyi.order.service.IOrderInfoService;
import com.ruoyi.order.domain.query.OrderInfoQuery;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单信息Controller
 *
 * @author thetbw
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/order/orderInfo")
public class OrderInfoController extends BaseController {

    @Autowired
    private IOrderInfoService orderInfoService;

    /**
     * 查询订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:orderInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderInfoQuery query) {
        startPage();
        List<OrderInfoEntity> list = orderInfoService.selectOrderInfoList(query);
        return getDataTable(list);
    }
    
    /**
     * 获取订单信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(orderInfoService.selectOrderInfoById(id));
    }
}
