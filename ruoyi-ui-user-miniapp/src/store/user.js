import {reactive} from "vue";
import {getAuth, saveAuth, clearAuth} from "../utils/auth";
import {getWechatSession, getLoginStatus, wechatLoginWithMobile, loginWithMobile, loginOut} from "@/api/login"
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
        return new Promise((resolve, reject) =>{
            if (process.env.TARO_ENV === "weapp") {
                Taro.login().then(wxRes => {
                    getWechatSession(wxRes['code']).then(res => {
                        let session = res.data;
                        this._saveSession(session)
                        resolve()
                    }).catch((e) => {
                        reject(e)
                        this.loginOut()
                    })
                })
            }
        })
    },
    isLogin() {
        return !!this.token
    },
    async loginWithWxMobile(code) {
        if (!this.openId){
            await this.initSession()
        }
        wechatLoginWithMobile(code, this.openId).then(res => {
            let session = res.data;
            this._saveSession(session)
        }).catch(res => {
            console.error("手机登录失败", res)
            clearAuth()
        })
    },
    async login(mobile, password, verifyCode) {
        try {
            let res = await loginWithMobile(mobile, password, verifyCode);
            let session = res.data;
            this._saveSession(session)
        } catch (error) {
            console.error("手机号登录失败", error)
            clearAuth()
            throw error
        }
    },
    loginOut() {
        loginOut().finally(() => {
            this.openId = null
            this.userInfo = null
            this.token = null
            this.unionId = null
            clearAuth()
        })

    }
})