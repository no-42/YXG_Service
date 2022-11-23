import request from '../utils/request'

/**
 * 初始化微信登录session <仅微信>
 * @param code 微信登录码
 */
function getWechatSession(code) {
    return request("/login/wechatSession?code=" + code, "POST", null)
}

/**
 * 使用手机号和微信openId登录 <仅微信>
 * @param code 微信手机号获取code
 * @param openId 微信openId
 */
function wechatLoginWithMobile(code, openId) {
    return request("/login/wechat?code=" + code + "&openId=" + openId, "POST", null)
}

/**
 * 获取当前登录信息
 */
function getLoginStatus() {
    return request("/login/status", "GET", null)
}

/**
 * 使用手机号和密码登录
 * @param mobile 手机号
 * @param password 密码
 * @param verifyCode 验证码 TODO 可选
 */
function loginWithMobile(mobile, password, verifyCode) {
    return request("/login/mobile", "POST", {mobile, password, verifyCode})
}

function loginOut() {
    return request("/logout", "POST", null)
}


export {getWechatSession, wechatLoginWithMobile, getLoginStatus, loginWithMobile, loginOut}