package com.ruoyi.api.web;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PageUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

public abstract class ApiController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }


    protected void startPage() {
        PageUtils.startPage(false);
    }
}
