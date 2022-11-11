<template>
  <view class="search">
    <nut-searchbar v-model="searchStore.searchValue"
                   @search="searchStore.doSearch"
                   placeholder="请输入关键词进行搜索">
      <template #leftin>
        <nut-icon size="14" name="search2"></nut-icon>
      </template>
      <template #rightout>
        <text @tap="searchStore.doSearch" @click="searchStore.doSearch">搜索</text>
      </template>
    </nut-searchbar>
  </view>
</template>

<script setup>
import {searchStore} from "@/store/search";
searchStore.searchValue = ""
</script>

<style lang="scss">
.search {

}
</style>