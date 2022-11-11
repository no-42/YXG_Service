import Taro from '@tarojs/taro'
import {baseUrl} from '../config'
import {getAuth} from './auth'

const errorHandleInterceptor = (chain) => {
    const requestParams = chain.requestParams
    return chain.proceed(requestParams)
        .then(res => {
            if (res.statusCode !== 200) {
                Taro.showToast({
                    title: '网络异常',
                    icon: 'error',
                    duration: 2000
                })
                throw "网络异常"
            } else {
                if (res.data.code !== 200) {
                    let msg = res.data.msg ? res.data.msg : "请求异常";
                    Taro.showToast({
                        title: msg,
                        icon: 'error',
                        duration: 2000
                    })
                    throw msg;
                }
            }
            return res.data
        })
}

Taro.addInterceptor(errorHandleInterceptor)

export default function (url, method, data) {
    let header = {};
    let auth = getAuth();
    if (auth) {
        header.Authorization = "Bearer " + auth
    }

    return Taro.request({
        url: baseUrl + url,
        method: method,
        data: data,
        header: {
            ...header
        }
    })

}