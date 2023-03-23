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
          <nut-button size="mini" plain type="info"
                      v-if="userStore.userInfo && !userStore.userInfo.avatar"
                      @tap="onUpdateMemberInfo">
            更新会员信息
          </nut-button>
        </view>
      </view>
      <view v-else class="user-login-container">
        <nut-button v-if="PLATFORM === 'weapp'"
                    class="login-button"
                    type="info"
                    open-type="getPhoneNumber"
                    @getphonenumber="getPhoneNumber"
        >微信登录
        </nut-button>
        <nut-button v-else
                    class="login-button"
                    type="info"
                    @click="goLoginByPhone">登录
        </nut-button>
      </view>
    </view>
    <view class="system_menu">
      <nut-cell-group>

        <nut-cell title="联系客服" >
          <button class="contact-button" open-type="contact">联系客服</button>
        </nut-cell>
        <nut-cell title="联系地址" is-link :desc="appInfo.address">
          <template #link>
            <text/>
          </template>
        </nut-cell>
        <nut-cell title="联系电话" is-link @click="jumpToSystemPhone()" :desc="appInfo.phone">
          <template #link>
            <text/>
          </template>
        </nut-cell>
      </nut-cell-group>
      <nut-cell-group v-if="userStore.isLogin()">
        <nut-cell title="退出登录" is-link @click="logout">
          <template #link>
            <image class="exit" :src='exitIcon'/>
          </template>
        </nut-cell>
      </nut-cell-group>
    </view>
  </view>

</template>
<script setup name="User">
import Taro from '@tarojs/taro'
import {userStore} from "@/store/user"
import {computed, reactive} from 'vue'
import defaultAvatar from "@/img/avatar.png"
import exitIcon from '@/img/exit.png'
import {updateMemberInfo} from '@/api/member'
import {loadConfig} from '@/api/common'

const appInfo = reactive({
  address: null,
  phone: null
})

loadConfig("miniapp_baseinfo").then(res => {
  if (res.data) {
    let baseInfo = JSON.parse(res.data);
    appInfo.address = baseInfo.address
    appInfo.phone = baseInfo.phone
  }
})


const PLATFORM = process.env.TARO_ENV
const userAvatar = computed(() => {
  if (userStore.userInfo && userStore.userInfo.avatar) {
    return userStore.userInfo.avatar
  } else {
    return defaultAvatar
  }
})

function onUpdateMemberInfo() {
  Taro.getUserProfile({
    desc: '用于完善会员资料',
    success: (res) => {
      console.log("当前会员信息", res)
      updateMemberInfo({
        name: res.userInfo.nickName,
        avatar: res.userInfo.avatarUrl
      }).then(res => {
        userStore.userInfo = res.data
      })
    }
  })
}

function logout() {
  userStore.loginOut()
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

function goLoginByPhone() {
  Taro.navigateTo({
    url: '/pages/user/login'
  })
}

function jumpToSystemPhone() {
  if (appInfo.phone) {
    Taro.makePhoneCall({
      phoneNumber: appInfo.phone //仅为示例，并非真实的电话号码
    })
  }

}

</script>

<style lang="scss">
.user-container {
  background-color: #f7f8fa;
  min-height: 100vh;

  .system_menu {
    padding-top: 10px;
    background-color: #f7f8fa;
    width: 95%;
    border-radius: 5px;
    margin: 0 auto 0;


    //  联系客服按钮设置
    .contact-button{
      position: initial;
      margin-left: initial;
      margin-right: initial;
      padding-left: initial;
      padding-right: initial;
      border-width: initial;
      border-radius: initial;
      font-size: inherit;
      text-align: left;
      color: inherit;
      line-height: initial;

      

    }

    .contact-button::after {
      border: none;
    }
  }


  .user_header {
    background-color: #1ab394;
    min-height: 180px;
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

      img {
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
    padding-top: 80px;
  }

  .user-update {
    padding-top: 10px;
    text-align: center;
  }

  .exit {
    width: 20px;
    height: 20px;

    img {
      width: 100%;
      height: 100%;
    }
  }
}
</style>