package com.ruoyi.admin.web.controller.monitor;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.query.SysLoginInfoQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.admin.web.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.admin.service.SysPasswordService;
import com.ruoyi.system.domain.entity.SysLoginInfoEntity;
import com.ruoyi.system.service.ISysLoginInfoService;

/**
 * 系统访问记录
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/logininfo")
public class SysLogininforController extends BaseController {
    @Autowired
    private ISysLoginInfoService loginInfoService;

    @Autowired
    private SysPasswordService passwordService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLoginInfoQuery info) {
        startPage();
        List<SysLoginInfoEntity> list = loginInfoService.selectLoginInfoList(info);
        return getDataTable(list);
    }

    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('monitor:logininfo:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLoginInfoQuery logininfor) {
        List<SysLoginInfoEntity> list = loginInfoService.selectLoginInfoList(logininfor);
        ExcelUtil<SysLoginInfoEntity> util = new ExcelUtil<SysLoginInfoEntity>(SysLoginInfoEntity.class);
        util.exportExcel(response, list, "登录日志");
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfo:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable String[] infoIds) {
        return toAjax(loginInfoService.deleteLoginInfoByIds(infoIds));
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfo:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        loginInfoService.cleanLoginInfo();
        return success();
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfo:unlock')")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public AjaxResult unlock(@PathVariable("userName") String userName) {
        passwordService.clearLoginRecordCache(userName);
        return success();
    }
}
