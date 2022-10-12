import { createApp } from 'vue'
import NutUI from "@nutui/nutui-taro"
//TODO 等bug https://github.com/jdf2e/nutui/issues/1735 解决
// import "@nutui/nutui-taro/dist/style.css";

import '@nutui/nutui-taro/dist/styles/themes/default.scss';
import './app.scss'

const App = createApp({
  onShow (options) {},
  // 入口组件不需要实现 render 方法，即使实现了也会被 taro 所覆盖
})

App.use(NutUI)

export default App
