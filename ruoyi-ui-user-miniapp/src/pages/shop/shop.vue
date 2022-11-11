<template>
  <view class="shop">
    <nut-searchbar v-model="searchStore.searchValue"
                   @click-input="goSearch"
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

      <nut-radiogroup v-model="queryParams.categoryId" @change="categorySelectOpen =false;getList()">
        <nut-radio shape="button" :label="null">全部</nut-radio>
        <nut-radio shape="button" :label="category.id" v-for="category in categoryList">{{ category.name }}</nut-radio>
      </nut-radiogroup>

    </nut-popup>
    <nut-menu>
      <nut-menu-item title="规格" v-model="queryParams.specId"/>
      <nut-menu-item title="产地" v-model="queryParams.originId">
        <nut-radiogroup direction="horizontal" v-model="queryParams.categoryId"
                        @change="categorySelectOpen =false;getList()">
          <nut-radio shape="button" :label="null">全部</nut-radio>
          <nut-radio shape="button" :label="category.id" v-for="category in categoryList">{{
              category.name
            }}
          </nut-radio>
        </nut-radiogroup>
      </nut-menu-item>
    </nut-menu>
  </view>
</template>

<script setup>
import {searchStore} from "@/store/search";
import Taro from "@tarojs/taro";
import {reactive, ref} from "vue";
import {getAllCategory, getAllSpec, getAllOrigin, getAllSupplier, getGoodsList} from '@/api/market'

const categorySelectOpen = ref(false);
const categoryList = ref([])
const specList = ref([])
const originList = ref([])
const supplierList = ref([])

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
    specList.value = res.data
  })
  getAllOrigin().then(res => {
    originList.value = res.data
  })
  getAllSupplier().then(res => {
    supplierList.value = res.data
  })
}

init()


function getList() {

}

function goSearch() {
  Taro.navigateTo({
    url: 'pages/home/search/search'
  })
}


</script>
<style lang="scss">
.shop {
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
      margin-top: -10px;
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

}
</style>