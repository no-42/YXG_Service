<template>
  <div class="app-container home">
    <el-row>
      <el-col :span="12">
        <creatable-select v-model="value" placeholder="请选择分类数据" :query-options="{name:{label:'名称'},age:'年龄',sex:'性别'}" :data="data">
          <template #add>
            这是新增
          </template>
        </creatable-select>
      </el-col>
      <el-col :span="12">
        <goods-spec-select v-model="v2"  model-name="hahah"></goods-spec-select>
      </el-col>
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

