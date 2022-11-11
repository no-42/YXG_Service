import {reactive, ref} from "vue";
import Taro from '@tarojs/taro'


export const searchStore = reactive({
    searchValue: '',
    doSearch() {
        Taro.switchTab({
            url: 'pages/shop/shop'
        })
    }
})