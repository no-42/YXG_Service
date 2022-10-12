<template>
  <view class="index">
    <!--  搜索栏   -->
    <nut-searchbar v-model="searchValue">
      <template v-slot:rightout>
        搜索
      </template>
    </nut-searchbar>
    <!-- 轮播 -->
    <nut-swiper :init-page="page" :pagination-visible="true" pagination-color="#426543" auto-play="3000">
      <nut-swiper-item v-for="item in swiperList" :key="item">
        <img :src="item" alt="" />
      </nut-swiper-item>
    </nut-swiper>
  </view>
</template>

<script>
import {reactive, toRefs} from 'vue';

export default {
  name: 'Home',
  components: {},
  setup() {
    const state = reactive({
      searchValue: '',
      page:2,
      swiperList: [ 'https://storage.360buyimg.com/jdc-article/NutUItaro34.jpg',
        'https://storage.360buyimg.com/jdc-article/NutUItaro2.jpg',
        'https://storage.360buyimg.com/jdc-article/welcomenutui.jpg',
        'https://storage.360buyimg.com/jdc-article/fristfabu.jpg'],
    });
    
    return {
      ...toRefs(state)
    }
  }
}
</script>

<style lang="scss">
.nut-swiper-item {
  line-height: 150px;
  img {
    width: 100%;
    height: 100%;
  }
}
</style>
