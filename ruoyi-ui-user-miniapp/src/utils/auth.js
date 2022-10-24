import Taro from '@tarojs/taro'

const TOKEN_KEY = "USER_TOKEN"

function saveAuth(token) {
    console.log("开始保存token",token)
    Taro.setStorageSync(TOKEN_KEY, token)
}

function getAuth() {
    return Taro.getStorageSync(TOKEN_KEY)
}

function clearAuth() {
    Taro.removeStorageSync(TOKEN_KEY)
}

export {saveAuth, getAuth, clearAuth}