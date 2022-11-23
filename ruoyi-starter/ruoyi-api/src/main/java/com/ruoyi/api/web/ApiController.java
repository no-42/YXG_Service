package com.ruoyi.api.web;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PageUtils;
import com.ruoyi.common.utils.StringUtils;
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

        // String 移除空格，忽略null和undefined
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.length() == 0 || text.trim().length() == 0) {
                    setValue(null);
                    return;
                }
                text = StringUtils.trim(text);
                if ("null".equals(text) || "undefiend".equals(text)) {
                    setValue(null);
                    return;
                }
                setValue(text);
            }
        });
    }


    protected void startPage() {
        PageUtils.startPage(false);
    }
}
