package com.ruoyi.api.web.common;

import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.entity.SysDictDataEntity;
import com.ruoyi.common.utils.ConfigUtils;
import com.ruoyi.common.utils.DictUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 公共接口
 *
 * @author bowentao
 */
@Anonymous
@RestController
@RequestMapping("/common")
public class CommonApiController extends ApiController {


    @GetMapping("/dict/{type}")
    public R<List<SysDictDataEntity>> getDict(@PathVariable("type") String dictType) {
        return R.ok(DictUtils.getDictCache(dictType));
    }


    @GetMapping("/config/{key}")
    public R<String> getConfig(@PathVariable("key") String configKey) {
        return R.ok(ConfigUtils.getConfigByKey(configKey));
    }
}
