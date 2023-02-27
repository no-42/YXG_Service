package com.ruoyi.common.utils.api;

import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.ruoyi.common.constant.ConfigConstants;
import com.ruoyi.common.utils.ConfigUtils;
import com.ruoyi.common.utils.JSONUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信接口请求
 * 仅Api接口调用，禁止后台调用
 *
 * @author bowen.tao
 */
public class WechatUtils {

    private static String accessToken = null;

    private static long expireAt = 0;

    private static long lastsCheckTime = 0;


    private static synchronized void checkAccessToken() {
        if (accessToken != null && expireAt - System.currentTimeMillis() > 60000) {
            return;
        }
        if (System.currentTimeMillis() - lastsCheckTime < 10000) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
//        params.put("appId", ConfigUtils.getConfigByKey(ConfigConstants.WECHAT_APP_ID));
//        params.put("secret", ConfigUtils.getConfigByKey(ConfigConstants.WECHAT_APP_SECRET));
        params.put("appId","wxb5b5e8bdf47062b1");
        params.put("secret", "214bde2c89e5bf5a268a2b65e855e39b");

        params.put("grant_type", "client_credential");
        String resultS = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token", params);
        JsonNode node = JSONUtils.parseAsJsonNode(resultS);
        accessToken = node.get("access_token").asText();
        lastsCheckTime = System.currentTimeMillis();
        expireAt = lastsCheckTime + (node.get("expires_in").asLong() * 1000);
    }

    /**
     * 微信登录
     */
    public static String jsCode2session(String code) {
        Map<String, Object> params = new HashMap<>();
//        params.put("appId", ConfigUtils.getConfigByKey(ConfigConstants.WECHAT_APP_ID));
//        params.put("secret", ConfigUtils.getConfigByKey(ConfigConstants.WECHAT_APP_SECRET));
        params.put("appId","wxb5b5e8bdf47062b1");
        params.put("secret", "214bde2c89e5bf5a268a2b65e855e39b");
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        return HttpUtil.get("https://api.weixin.qq.com/sns/jscode2session", params);
    }


    /**
     * 获取手机号
     */
    public static String getPhoneNumber(String code) {
        checkAccessToken();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        return HttpRequest.post("https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=" + accessToken).body(JSONUtils.toJSON(params))
                .timeout(HttpGlobalConfig.getTimeout()).execute().body();
    }
}
