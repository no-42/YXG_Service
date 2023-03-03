<template>
  <div class="app-container home">
    <el-row>
      <h1>welcome</h1>
    </el-row>

  </div>
</template>

<script setup name="Index">
import CreatableSelect from '../components/CreatableSelect/index'
import GoodsSpecSelect from '../components/CreatableSelect/goodsSpecSelect'
import {ref, watch} from "vue";
let data = [123123,1231,21312]
const value = ref('')

const v2 = ref('1')

</script>

<style scoped lang="scss">
.home {

}
</style>

