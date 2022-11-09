<template>
  <creatable-select placeholder="请选择供应商数据" @add-open="resetForm" v-model="value" :tableOptions="tableOptions"
                    :query-options="queryOptions"
                    field-key="id" label-key="name"
                    :model-label="name" :fetch-func="listSupplier">
    <template #add="{close}">
      <el-form ref="supplierRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="供应商名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入供应商名称"/>
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
import {listSupplier, addSupplier} from '../../api/market/supplier'
import {computed, getCurrentInstance, ref} from "vue";

const {proxy} = getCurrentInstance()

const emit = defineEmits(['update:modelValue'])
let props = defineProps({
  modelValue: [String],
  modelName: String
})

let name = ref(props.modelName)

const tableOptions = {
  "name": "供应商",
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
    {required: true, message: "供应商名称不能为空", trigger: "blur"}
  ],
}

function resetForm() {
  proxy.$refs["supplierRef"] && proxy.$refs["supplierRef"].resetFields()
}

function submitForm(close) {
  proxy.$refs["supplierRef"].validate(valid => {
    if (valid) {
      addSupplier(form.value).then(() => {
        proxy.$modal.msgSuccess("新增成功");
        close()
      });
    }
  });
}
</script>