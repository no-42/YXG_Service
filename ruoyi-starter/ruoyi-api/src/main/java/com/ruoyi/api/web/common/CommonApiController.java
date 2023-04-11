package com.ruoyi.api.web.common;

import com.ruoyi.api.web.ApiController;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.entity.SysDictDataEntity;
import com.ruoyi.common.utils.ConfigUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.system.domain.query.SysDictDataQuery;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private ISysDictDataService sysDictDataService;
    
    @Autowired
    private ISysConfigService sysConfigService;
    
    @GetMapping("/dict/{type}")
    public R<List<SysDictDataEntity>> getDict(@PathVariable("type") String dictType) {
        SysDictDataQuery query = new SysDictDataQuery();
        query.setType(dictType);
        query.setEnable(true);
        if (!dictType.startsWith("miniapp")){
            return R.ok();
        }
        return R.ok(sysDictDataService.selectDictDataList(query));
    }


    @GetMapping("/config/{key}")
    public R<String> getConfig(@PathVariable("key") String configKey) {
        if (!configKey.startsWith("miniapp")){
            return R.ok();
        }
        return R.ok(sysConfigService.selectConfigByKey(configKey));
    }
}
