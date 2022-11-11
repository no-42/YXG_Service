<template>
  <view class="user-container">
    <view class="user_header">
      <view v-if="userStore.isLogin()" class="user-info-container">
        <view class="user-avatar">
          <cover-image class='img'
                       :src='userAvatar'/>
        </view>
        <view class="user-name">
          <text>{{ userStore.userInfo.name }}</text>
        </view>
        <view class="user-update">
          <nut-button size="mini" plain type="info" @tap="onUpdateMemberInfo">更新会员信息</nut-button>
        </view>
      </view>
      <view v-else class="user-login-container">
        <nut-button class="login-button" type="info" open-type="getPhoneNumber" @getphonenumber="getPhoneNumber"
                    @click="onUserLogin">微信登录
        </nut-button>
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
import {computed} from 'vue'
import defaultAvatar from "@/img/avatar.png"
import {updateMemberInfo} from '@/api/member'

const userAvatar = computed(() => {
  if (userStore.userInfo && userStore.userInfo.avatar) {
    return userStore.userInfo.avatar
  } else {
    return defaultAvatar
  }
})

function onUpdateMemberInfo() {
  Taro.getUserProfile({
    desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
    success: (res) => {
      updateMemberInfo({
        name: res.userInfo.nickName,
        avatar: res.userInfo.avatarUrl
      }).then(res => {
        userStore.userInfo = res.data
      })
    }
  })
}


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
  if (process.env.TARO_ENV === "weapp") {
    return
  }
  userStore.login()
}

</script>

<style lang="scss">
.user-container {
  background-color: #f7f8fa;
  min-height: 100%;

  .system_menu {
    padding-top: 0.1rem;
    background-color: #f7f8fa;
    width: 95%;
    border-radius: 5px;
    margin: 0 auto 0;
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
      
      img{
        width: 100%;
        height: 100%;
      }
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

  .user-update {
    padding-top: 0.6rem;
    text-align: center;
  }
}
</style>