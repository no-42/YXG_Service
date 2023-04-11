<template>
  <view class="shop" :style="{height:pageHeight}">
    <view class="header" id="header">
      <nut-searchbar v-model="searchStore.searchValue"
                     @click-input="goSearch"
                     @change="refreshList"
                     placeholder="请输入关键词进行搜索">
        <template #leftout>
          <view class="category-button" @tap="categorySelectOpen=true">
            <nut-icon size="14" name="category"></nut-icon>
            <view class="category-button-text">分类</view>
          </view>
        </template>
      </nut-searchbar>
      <!--  分类选择   -->
      <nut-popup class="category-select" position="left"
                 v-model:visible="categorySelectOpen">
        <view class="title">
          <text>选择分类:</text>
        </view>

        <nut-radiogroup v-model="queryParams.categoryId" @change="categorySelectOpen =false;refreshList()">
          <nut-radio shape="button" :label="null">全部</nut-radio>
          <nut-radio shape="button" :label="category.id" v-for="category in categoryList">{{
              category.name
            }}
          </nut-radio>
        </nut-radiogroup>
      </nut-popup>
      <nut-menu>
        <nut-menu-item @change="refreshList" title="规格" :cols="2" :options="specOptions" v-model="queryParams.specId"/>
        <nut-menu-item @change="refreshList" title="产地" :cols="2" :options="originOptions"
                       v-model="queryParams.originId"/>
      </nut-menu>
    </view>
    <view class="content">
      <nut-empty image="empty" description="暂时没有内容" v-if="goodsList.length===0 && !loading">
        <div style="margin-top: 10px" v-if="queryParams.categoryId || queryParams.originId || queryParams.specId">
          <nut-button icon="refresh" type="primary" @click="resetQuery()">查看全部</nut-button>
        </div>
      </nut-empty>
      <scroll-view class="scroll-view" v-else :style="{height:scrollHeight}" :scroll-y="true" :enhanced="true"
                   @scrolltolower="nextPage">
        <view class="goods-item" v-for="goods in goodsList">
          <view class="goods-title">
            {{ goods.categoryName }} {{ goods.specName }}
          </view>
          <view class="goods-des">
            {{ goods.supplierName }}
            <text class="goods-origin">{{ goods.originName }}</text>
            <!--            <text class="goods-supplier">{{ goods.supplierName }}</text>-->
          </view>
          <view class="goods-buy">
            <view class="price" v-if="userStore.isLogin()">
              ￥{{ goods.price }}/{{ goods.priceUnit }}
            </view>
            <view class="concat" v-if="userStore.isLogin()">
              <nut-button size="small" type="primary" open-type="contact">联系购买</nut-button>
            </view>
            <view v-else class="login-ref">
              <nut-button size="small" type="info" @click="goLogin">登陆后查看价格</nut-button>
            </view>
          </view>
        </view>
      </scroll-view>
    </view>
  </view>
</template>

<script setup>
import {searchStore} from "@/store/search";
import {useDidShow} from '@tarojs/taro'
import Taro from "@tarojs/taro";
import {reactive, ref} from "vue";
import {getAllCategory, getAllSpec, getAllOrigin, getAllSupplier, getGoodsList} from '@/api/market'
import {userStore} from "../../store/user";
import {$} from '@tarojs/extend'
import {getWindowHeight} from '@/utils/app'
import {categoryStore} from "@/store/category"

let pageHeight = process.env.TARO_ENV === 'h5'?' calc(100% - 10px)':'100vh'
const scrollHeight = ref("500px")
const categorySelectOpen = ref(false);
const loading = ref(true)
const categoryList = ref([{
  text: "全部",
  value: null
}])
const specOptions = ref([{
  text: "全部",
  value: null
}])
const originOptions = ref([{
  text: "全部",
  value: null
}])
const goodsList = ref([])

const page = {
  pageNum: 1,
  pageSize: 50,
  hasNext: true
}

const queryParams = reactive({
  categoryId: null,
  specId: null,
  originId: null
})

function init() {
  getAllCategory().then(res => {
    categoryList.value = res.data
  })

  getAllSpec().then(res => {
    specOptions.value = [{
      text: "全部",
      value: null
    }]


    specOptions.value.push(...res.data.map((v) => {
      return {
        text: v.name,
        value: v.id
      }
    }))
  })
  getAllOrigin().then(res => {
    originOptions.value = [{
      text: "全部",
      value: null
    }]
    originOptions.value.push(...res.data.map((v) => {
      return {
        text: v.name,
        value: v.id
      }
    }))
  })

  if (categoryStore.categoryId) {
    queryParams.categoryId = categoryStore.categoryId
    categoryStore.categoryId = null
  }
  // getAllSupplier().then(res => {
  //   supplierList.value = res.data
  // })
}


function concat() {

}

function nextPage() {
  if (page.hasNext) {
    page.pageNum += 1;
    getList()
  }
}

let refreshTimer = null;

function refreshList() {
  if (refreshTimer) {
    clearTimeout(refreshTimer)
  }
  refreshTimer = setTimeout(() => {
    page.pageNum = 1;
    page.hasNext = true;
    goodsList.value = []
    getList()
  }, 100)
}

function resetQuery() {
  queryParams.categoryId = null;
  queryParams.specId = null
  queryParams.originId = null
  searchStore.searchValue = '';
  refreshList()
}

function getList() {
  loading.value = true
  Taro.showLoading({
    title: '加载中',
  })
  queryParams.search = searchStore.searchValue
  getGoodsList(queryParams, page.pageNum, page.pageSize).then(res => {
    if (!res.data || res.data.length !== page.pageSize) {
      page.hasNext = false;
    }
    goodsList.value.push(...res.data)
  }).finally(() => {
    Taro.hideLoading()
    loading.value = false
  })
}

function goSearch() {
  Taro.navigateTo({
    url: '/pages/home/search/search'
  })
}

function goLogin() {
  Taro.switchTab({
    url: '/pages/user/user'
  })
}

async function initSize() {
  let total = getWindowHeight()
  console.log("t", total)
  let header = await $("#header").height()
  console.log("h", header)
  scrollHeight.value = (total - header - 15) + "px";
}

useDidShow(() => {
  init()
  refreshList()
  setTimeout(()=>{
    initSize()
  },50)
  
})
Taro.onWindowResize(initSize)

</script>
<style lang="scss">
@import "src/app";

.shop {


  .header {

    .title {
      padding: 10px 10px;
      width: 100%;
      margin-bottom: 10px;
      box-shadow: 0 0 5px #bbbbbb;
    }

    .category-button {
      text-align: center;

      .category-button-text {
        font-size: 10px;
        margin-top: -5px;
      }

    }

    .category-select .popup-left {
      height: 100%;
      width: 50%;
      text-align: center;

      .nut-radiogroup {
        width: 90%;
      }

      .nut-radio__button {
        width: 80%;
      }
    }


    .nutui-popup__content-wrapper {
      width: 100%;
    }
  }

  .content {

    .scroll-view {
      height: 100%;
      background-color: $background-color-grey;
    }

    .goods-item {
      height: 70px;
      padding: 10px;
      margin: 10px;
      background-color: white;
      position: relative;
      font-size: 15px;
      
      @media (max-width: 300px) {
        font-size: 13px;
      }

      .goods-title {
        font-weight: 700;
      }

      .goods-des {
        margin-top: 10px;
        font-size: 14px;

        @media (max-width: 300px) {
          font-size: 11px;
        }
      }

      .goods-origin {
        display: inline-block;
        border-radius: 10px;
        padding: 1px 10px;
        color: white;
        background-color: #0785ff;
      }

      .goods-buy {
        position: absolute;
        top: 10px;
        right: 10px;
        text-align: center;

        .price {
          color: #0785ff;
        }
      }
    }
  }


}
</style>