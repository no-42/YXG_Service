import {createApp} from 'vue'
import Taro from '@tarojs/taro'
import NutUI from "@nutui/nutui-taro"
import {userStore} from "@/store/user"
//TODO 等bug https://github.com/jdf2e/nutui/issues/1735 解决
// import "@nutui/nutui-taro/dist/style.css";

import '@nutui/nutui-taro/dist/styles/themes/default.scss';
import './app.scss'

const App = createApp({
    onShow(options) {
    },
    async onLaunch() {
        let success = await userStore.init();
        if (!success) {
            userStore.initSession()
        } else {
            Taro.checkSession().catch(() => {
                userStore.initSession()
            })
        }
    }
})

App.use(NutUI)

export default App
