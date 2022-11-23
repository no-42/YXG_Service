<template>
  <view class="user-login">
    <view class="header">用户登录</view>
    <nut-form class="form" :model-value="formData" ref="form">
      <nut-form-item class="form-item" :rules="[{required:true,message:'请输入手机号'}]" prop="mobile" required label="手机号">
        <input class="nut-input-text" v-model="formData.mobile" placeholder="请输入手机号" type="text"/>
      </nut-form-item>
      <nut-form-item class="form-item" :rules="[{required:true,message:'请输入密码'}]" prop="password" required label="密码">
        <input class="nut-input-text" v-model="formData.password" placeholder="请输入密码" type="password"/>
      </nut-form-item>
    </nut-form>
    <view class="footer">
      <nut-button class="login-button" @click="handleLogin" type="primary">登录</nut-button>
    </view>
  </view>

</template>

<script setup>

import {reactive, ref} from "vue";
import {userStore} from "@/store/user"

import Taro from "@tarojs/taro";

definePageConfig({
  navigationBarTitleText: '登录',
  navigationBarBackgroundColor: '#1ab394'
})

const form = ref(null)
const formData = reactive({
  mobile: null,
  password: null
})

function handleLogin() {
  form.value.validate().then(({valid}) => {
    if (valid) {
      userStore.login(formData.mobile, formData.password, null).then(() => {
        Taro.switchTab({
          url: '/pages/user/user'
        })
      })
    }
  })
}

</script>

<style lang="scss">
.user-login {
  .header {
    margin-top: 20px;
    font-weight: 700;
    text-align: center;
  }

  .form {
    margin: 10px auto;
    width: 80%;
  }

  .footer {
    text-align: center;

    .login-button {
      width: 100px;
    }

  }
}
</style>