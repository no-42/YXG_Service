<template>
  <view class="index">
    <!--  搜索栏   -->
    <view class="header">
      <nut-searchbar @tap="goSearch" @click="goSearch" placeholder="请输入关键词进行搜索">
        <template #leftin>
          <nut-icon size="14" name="search2"></nut-icon>
        </template>
        <template #rightout>
          搜索
        </template>
      </nut-searchbar>
      <!-- 轮播 -->
      <nut-swiper v-if="swiperList && swiperList.length > 0" :pagination-visible="true" pagination-color="#426543" auto-play="3000">
        <nut-swiper-item v-for="item in swiperList" :key="item">
          <img :src="item" alt=""/>
        </nut-swiper-item>
      </nut-swiper>
    </view>
    <view class="category-list">
      <text class="title">产品分类</text>
      <nut-skeleton :loading="categoryLoad" height="15px" width="300px" row="5" title animated>
        <nut-empty image="empty" description="暂时没有内容" v-if="categoryList && categoryList.length===0"></nut-empty>
        <nut-grid class="category-main" :border="false" :column-num="3" v-if="categoryList.length!==0">
          <nut-grid-item @click="categoryStore.goCategory(category.id)" v-for="category in categoryList">
            <view class="category-item">
              {{ category.name }}
            </view>
          </nut-grid-item>
        </nut-grid>
      </nut-skeleton>
    </view>
    <view class="notice-list">
      <text class="title">公司动态</text>
      <nut-skeleton :loading="noticeLoad" height="15px" width="300px" row="5" title animated>
        <nut-empty image="empty" description="暂时没有内容" v-if="noticeList && noticeList.length===0"></nut-empty>
        <view class="notice-main">
          <view class="notice-item" v-for="notice in noticeList">
            <view class="notice-title">{{ notice.label }}</view>
            <view class="notice-des" v-if="notice.value !== 'null'">
              {{ notice.value }}
            </view>
          </view>
        </view>
      </nut-skeleton>
    </view>
  </view>
</template>

<script setup>
import {ref} from 'vue';
import {loadDict} from '@/api/common'
import {getAllCategory} from '@/api/market'
import {useDidShow} from '@tarojs/taro'
import {categoryStore} from "@/store/category"
import Taro from "@tarojs/taro";

const categoryLoad = ref(true)
const noticeLoad = ref(true)
const swiperList = ref([])
const noticeList = ref([])
const categoryList = ref([])

function goSearch() {
  Taro.navigateTo({
    url: '/pages/home/search/search'
  })
}


useDidShow(() => {
  getAllCategory().then(res => {
    categoryList.value = res.data
    categoryLoad.value = false
  })

  loadDict("miniapp_swiper").then(res => {
    let dict = res.data;
    let value = []
    if(dict){
        for (let dictElement of dict) {
          value.push(dictElement.value)
        }
    }
    swiperList.value = value
  })

  loadDict("miniapp_notice").then(res => {
    noticeLoad.value = false
    noticeList.value = res.data
  })
})

</script>

<style lang="scss">
.index {
  background-color: #f7f7f7;

  .header {
    padding-bottom: 10px;
    background-color: white;
  }

  .nut-swiper-item {
    line-height: 150px;

    img {
      width: 100%;
      height: 100%;
    }
  }

  .category-list {
    background-color: white;
    margin-top: 20px;
    padding: 10px;

    .category-main {
      min-height: 100px;

      .nut-grid-item {
        width: 0;
      }

      .category-item {
        font-size: 13px;
        text-align: center;
        padding: 5px 10px;
        border-radius: 5px;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        box-sizing: border-box;
        width: 100%;
        height: 35px;
        background-color: #f7f7f7;
        
        @media (max-width: 300px) {
          font-size: 11px;
          padding: 1px 2px;
          border-radius: 3px;
          height: 24px;
        }
      }
    }
  }

  .notice-list {
    background-color: white;
    padding: 10px;

    .notice-main {
      .notice-item {
        margin: 5px;
        padding: 10px 10px 20px 10px;
        background-color: #f7f7f7;
        border-radius: 5px;


        .notice-title {
          font-size: 16px;
        }

        .notice-des {
          margin-top: 10px;
          font-size: 14px;
        }
      }


    }
  }

  //加载动画
  .skeleton {
    display: block;
    margin-top: 5px;
  }

}

</style>
