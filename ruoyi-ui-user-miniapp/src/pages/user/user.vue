<template>
  <view class="user-container">
    <view class="user_header">
      <view v-if="userStore.isLogin()" class="user-info-container">
        <view class="user-avatar">
          <cover-image class='img'
                       :src='userStore.userInfo.avatar'/>
        </view>
        <view class="user-name">
          <text>{{ userStore.userInfo.name }}</text>
        </view>
      </view>
      <view v-else class="user-login-container">
        <nut-button class="login-button" open-type="getPhoneNumber" @getphonenumber="getPhoneNumber" @click="onUserLogin">微信登录</nut-button>
      </view>
    </view>
    <view class="system_menu">
      <nut-cell-group>
        <nut-cell title="联系客服"></nut-cell>
        <nut-cell title="联系地址" desc="江苏省南京市"></nut-cell>
        <nut-cell title="联系电话" desc="17714353449"></nut-cell>
      </nut-cell-group>
    </view>
  </view>

</template>
<script setup name="User">
import Taro from '@tarojs/taro'
import {userStore} from "@/store/user"

function getPhoneNumber(e) {
  if (e.detail.errMsg === "getPhoneNumber:ok") {
    userStore.loginWithWxMobile(e.detail.code)
  } else {
    Taro.showToast({
      title: '您已拒绝获取手机号',
      icon: 'error',
      duration: 2000
    })
  }
  console.log(e)
}

function onUserLogin(e) {
  if(process.env.TARO_ENV === "weapp" ){
    return
  }
  //非微信端的登录方式
  Taro.showToast({
    title: '不支持的登录方式',
    icon: 'error',
    duration: 2000
  })
}

</script>

<style lang="scss">

.system_menu {
  padding-top: 0.1rem;
  background-color: #f7f8fa;
  width: 95%;
  border-radius: 5px;
  margin: -2rem auto 0;
}

.user-container {
  background-color: #f7f8fa;
  min-height: 100%;
}


.user_header {
  background-color: #1ab394;
  height: 10rem;
}

.user-info-container {
  text-align: left;
  padding: 10px;
  height: 100%;
}

.user-avatar {
  text-align: center;
  padding-top: 10px;

  .img {
    height: 80px;
    width: 80px;
    border-radius: 80px;
    display: inline-block;
  }
}

.user-name {
  text-align: center;
}

.user-login-container {
  text-align: center;
  height: 100%;
  line-height: 100%;
  padding-top: 3rem;

}
</style>