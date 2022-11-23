import {reactive} from "vue";
import Taro from '@tarojs/taro'


export const categoryStore = reactive({
    categoryId: null,
    goCategory(id) {
        this.categoryId = id
        Taro.switchTab({
            url: '/pages/shop/shop'
        })
    }
})