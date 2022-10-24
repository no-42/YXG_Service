import {reactive} from "vue";
import {getAuth, saveAuth, clearAuth} from "../utils/auth";
import {getWechatSession, wechatLoginWithMobile} from "@/api/login"
import Taro from '@tarojs/taro'

/**
 * 用户数据存储
 */
export const userStore = reactive({
    userInfo: null,
    token: null,
    openId: null,
    unionId: null,
    init() {
        console.log("开始初始化用户")
        let token = getAuth();
        if (!token) {
            return false;
        }
        this.token = token;
        return true
    },
    initSession() {
        Taro.login().then(wxRes => {
            getWechatSession(wxRes['code']).then(res => {
                let session = res.data;
                this.userInfo = session['memberInfo'];
                this.openId = session['openId']
                this.token = session['token']
                this.unionId = session['unionId']
                if (this.token){
                    saveAuth(this.token)
                }
            })
        })

    },
    isLogin() {
        return !!this.token
    },
    loginWithWxMobile(code) {
        wechatLoginWithMobile(code,this.openId).then(res => {
            let session = res.data;
            this.userInfo = session['memberInfo'];
            this.openId = session['openId']
            this.token = session['token']
            this.unionId = session['unionId']
            console.log(session,this)
            saveAuth(this.token)
        }).catch(res => {
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