<template>
  <creatable-select placeholder="请选择规格数据" @add-open="resetForm" v-model="value" :tableOptions="tableOptions"
                    :query-options="queryOptions"
                    field-key="id" label-key="name"
                    :model-label="name" :fetch-func="listSpec">
    <template #add="{close}">
      <el-form ref="specRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="规格名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入规格名称"/>
        </el-form-item>
        <el-form-item label="规格值" prop="value">
          <el-input v-model="form.value" placeholder="请输入规格值"/>
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
import {listSpec, addSpec} from '../../api/market/spec'
import {computed, getCurrentInstance, ref} from "vue";

const {proxy} = getCurrentInstance()

const emit = defineEmits(['update:modelValue'])
let props = defineProps({
  modelValue: [String],
  modelName: String
})

let name = ref(props.modelName)

const tableOptions = {
  "name": "规格名称",
  "value": "规格值"
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
    {required: true, message: "规格名称不能为空", trigger: "blur"}
  ],
  value: [
    {required: true, message: "规格值不能为空", trigger: "blur"}
  ]
}

function resetForm() {
  proxy.$refs["specRef"] && proxy.$refs["specRef"].resetFields()
}

function submitForm(close) {
  proxy.$refs["specRef"].validate(valid => {
    if (valid) {
      addSpec(form.value).then(() => {
        proxy.$modal.msgSuccess("新增成功");
        close()
      });
    }
  });
}
</script>