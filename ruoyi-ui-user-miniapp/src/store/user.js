import {reactive} from "vue";
import {getAuth, saveAuth, clearAuth} from "../utils/auth";
import {getWechatSession, getLoginStatus, wechatLoginWithMobile} from "@/api/login"
import Taro from '@tarojs/taro'

/**
 * 用户数据存储
 */
export const userStore = reactive({
    userInfo: null,
    token: null,
    openId: null,
    unionId: null,
    _saveSession(data) {
        this.userInfo = data['memberInfo'];
        this.openId = data['openId']
        this.token = data['token']
        this.unionId = data['unionId']
        if (this.token) {
            saveAuth(this.token)
        }
    },
    async init() {
        let token = getAuth();
        if (!token) {
            return false;
        }
        try {
            let res = await getLoginStatus()
            this._saveSession(res.data)
        } catch (e) {
            console.error("登录状态已过期", e)
            this.loginOut()
            return false;
        }
        return true
    },
    async initSession() {
        Taro.login().then(wxRes => {
            getWechatSession(wxRes['code']).then(res => {
                let session = res.data;
                this._saveSession(session)
            }).catch(() => {
                this.loginOut()
            })
        })
    },
    isLogin() {
        return !!this.token
    },
    async loginWithWxMobile(code) {
        wechatLoginWithMobile(code, this.openId).then(res => {
            let session = res.data;
            this._saveSession(session)
        }).catch(res => {
            console.error("手机登录失败", res)
            clearAuth()
        })
    },
    loginOut() {
        this.openId = null
        this.userInfo = null
        this.token = null
        this.unionId = null
        clearAuth()
    }
})