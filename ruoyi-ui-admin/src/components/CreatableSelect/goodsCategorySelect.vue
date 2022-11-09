<template>
  <creatable-select placeholder="请选择分类数据" v-model="value" :tableOptions="tableOptions" :query-options="queryOptions"
                    field-key="id" label-key="name" @add-open="resetForm"
                    :model-label="name" :fetch-func="listCategory">
    <template #add="{close}">
      <el-form ref="categoryRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称"/>
        </el-form-item>
      </el-form>
      <div class="dialog-footer">
        <el-button type="primary" @click="submitForm(close)">确 定</el-button>
        <el-button @click="close">取 消</el-button>
      </div>
    </template>

  </creatable-select>
</template>

<script setup>
import CreatableSelect from './index'
import {listCategory, addCategory} from '../../api/market/category'
import {computed, getCurrentInstance, ref} from "vue";

const {proxy} = getCurrentInstance()

const emit = defineEmits(['update:modelValue'])
let props = defineProps({
  modelValue: [String],
  modelName: String
})

let name = ref(props.modelName)

const tableOptions = {
  "name": "分类名称",
}

const queryOptions = {
  "name": "名称"
}
const value = computed({
  get() {
    return props.modelValue
  },
  set(v) {
    emit('update:modelValue', v)
  }
})

const form = ref({})
const rules = {
  name: [
    {required: true, message: "分类名称不能为空", trigger: "blur"}
  ]
}

function resetForm() {
  proxy.$refs["categoryRef"] && proxy.$refs["categoryRef"].resetFields()
}

function submitForm(close) {
  proxy.$refs["categoryRef"].validate(valid => {
    if (valid) {
      addCategory(form.value).then(() => {
        proxy.$modal.msgSuccess("新增成功");
        close()
      });
    }
  });
}
</script>