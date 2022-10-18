package com.ruoyi.admin.web.controller.member;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.admin.web.BaseController;
import com.ruoyi.member.domain.query.MemberInfoQuery;
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
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.member.domain.entity.MemberInfoEntity;
import com.ruoyi.member.service.IMemberInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员信息Controller
 *
 * @author thetbw
 * @date 2022-10-18
 */
@RestController
@RequestMapping("/member/memberInfo")
public class MemberInfoController extends BaseController {

    @Autowired
    private IMemberInfoService memberInfoService;

    /**
     * 查询会员信息列表
     */
    @PreAuthorize("@ss.hasPermi('member:memberInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberInfoQuery query) {
        startPage();
        List<MemberInfoEntity> list = memberInfoService.selectMemberInfoList(query);
        return getDataTable(list);
    }

    /**
     * 导出会员信息列表
     */
    @PreAuthorize("@ss.hasPermi('member:memberInfo:export')")
    @Log(title = "会员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberInfoQuery query) {
        List<MemberInfoEntity> list = memberInfoService.selectMemberInfoList(query);
        ExcelUtil<MemberInfoEntity> util = new ExcelUtil<MemberInfoEntity>(MemberInfoEntity.class);
        util.exportExcel(response, list, "会员信息数据");
    }

    /**
     * 获取会员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:memberInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(memberInfoService.selectMemberInfoById(id));
    }

    /**
     * 新增会员信息
     */
    @PreAuthorize("@ss.hasPermi('member:memberInfo:add')")
    @Log(title = "会员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberInfoEntity memberInfo) {
        return toAjax(memberInfoService.insertMemberInfo(memberInfo));
    }

    /**
     * 修改会员信息
     */
    @PreAuthorize("@ss.hasPermi('member:memberInfo:edit')")
    @Log(title = "会员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberInfoEntity memberInfo) {
        return toAjax(memberInfoService.updateMemberInfo(memberInfo));
    }

    /**
     * 删除会员信息
     */
    @PreAuthorize("@ss.hasPermi('member:memberInfo:remove')")
    @Log(title = "会员信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(memberInfoService.deleteMemberInfoByIds(ids));
    }
}
