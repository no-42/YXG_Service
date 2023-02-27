package com.ruoyi.api.web.common;

import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController extends ApiController {
    
    @GetMapping
    public AjaxResult index(){
        return AjaxResult.success("服务器运行中");
    }
}
