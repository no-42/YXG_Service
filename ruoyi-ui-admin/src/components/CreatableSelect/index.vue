<template>
  <div @click="handleFocus" class="creatable-select">
    <el-input v-model="label" :suffix-icon="inputIcon" clearable :placeholder="props.placeholder"></el-input>
    <el-drawer
        v-model="open"
        :size="width"
        :title="props.placeholder"
        direction="rtl"
    >
      <el-row>
        <el-form size="small" ref="searchForm" :model="queryParams" v-if="props.queryOptions">
          <el-col :span="12" :gutter="10" v-for="(value,key) in props.queryOptions">
            <el-form-item :label="typeof value === 'object' ? value.label : value" :prop="key">
              <el-input size="small" v-model="queryParams[key]" @change="handleQueryChange"
                        :placeholder="typeof value === 'object' ? value.placeholder : ``"></el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <el-row class="action" v-if="$slots.add">
        <el-button type="success" size="small" @click="handleAdd">新增</el-button>
      </el-row>


      <el-table :data="selectData" v-loading="loading" @row-click="handleRowClick">
        <el-table-column v-if="!props.tableOptions" label="名称">
          <template #default="scope">
            {{ scope.row }}
          </template>
        </el-table-column>
        <el-table-column v-if="props.tableOptions" :prop="key" :label="value"
                         v-for="(value,key) in props.tableOptions"/>
      </el-table>

      <el-drawer
          v-model="add"
          title="新增数据"
          :append-to-body="true"
      >
        <slot name="add" :close="handleClose"></slot>
      </el-drawer>

      <template #footer v-if="props.fetchFunc">
        <pagination
            v-show="total > 0"
            :total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
        />
      </template>
    </el-drawer>
  </div>
</template>


<script setup>
import {ArrowUp, ArrowDown} from '@element-plus/icons-vue'
import {computed, ref, reactive, getCurrentInstance} from "vue";

const {proxy} = getCurrentInstance();

const props = defineProps({
  //value
  modelValue: [String, Number],
  //选择数据，固定数组
  data: {
    type: Array,
    default: []
  },
  fieldKey: String,
  labelKey: String,
  modelLabel: String,
  tableOptions: [Object],
  queryOptions: [Object],
  //选择数据，远程分页请求
  fetchFunc: [Function],
  placeholder: String
})
const emit = defineEmits(['update:modelValue', 'update:modelLabel', 'add-open', 'change', 'create'])
const open = ref(false)
const add = ref(false)
const inputIcon = computed(() => open.value ? ArrowUp : ArrowDown)
const selectData = ref(props.data)
const loading = ref(false)
const form = ref({})
const total = ref(10)

let _label = props.modelLabel ? props.modelLabel : props.modelValue
if (props.labelKey && props.fieldKey && !props.fetchFunc && props.modelValue) {
  for (let value of props.data) {
    if (value[props.fieldKey] === props.modelValue) {
      _label = value[props.labelKey]
      break
    }
  }
}
const label = ref(_label)

let width = 700
if (window.innerWidth < width) {
  width = window.innerWidth - 50
}


const queryParams = reactive({
  pageNum: 1,
  pageSize: 10
})


const value = computed({
  get() {
    return props.modelValue
  },
  set(v) {
    emit('update:modelValue', v)
  }
})


function getList() {
  if (!props.fetchFunc) {
    return;
  }
  //TODO 实现本地查询
  loading.value = true
  props.fetchFunc(queryParams).then((response) => {
    selectData.value = response.rows;
    total.value = response.total;
    loading.value = false;
  })
}

function handleAdd() {
  add.value = true
  emit('add-open')
}

function handleQueryChange() {
  getList()
}

function handleFocus(e) {
  if (e.target.parentNode && e.target.parentNode.getAttribute("class") && e.target.parentNode.getAttribute("class").includes('el-input__clear')) {
    return
  }
  open.value = true
  queryParams.pageSize = 10;
  queryParams.pageNum = 1
  proxy.resetForm('searchForm')
  getList()
}

function handleRowClick(row) {
  open.value = false
  let value = props.fieldKey ? row[props.fieldKey] : row;
  if (label.value !== value) {
    emit('change')
  }
  label.value = props.labelKey ? row[props.labelKey] : value;
  emit('update:modelLabel', label.value)
  emit('update:modelValue', value)
}

function handleClose() {
  add.value = false
  getList()
}

</script>

<style lang='scss' scoped>
.creatable-select {
  position: relative;

  .select-div {
    position: absolute;
    top: 40px;
    width: 100%;
    box-shadow: 0 0 5px #9c9c9a;
    border-radius: var(--el-input-border-radius, var(--el-border-radius-base));
  }

  .select-create {
    padding: 5px;
  }

  :is(.el-table) :is(tr) {
    cursor: pointer;
  }

  :is(.el-table) :is(tr) {
    cursor: pointer;
  }

  :is(.el-overlay) :is(.el-drawer__header) {
    margin-bottom: 10px;
  }

  .action {
    margin-bottom: 10px;
  }
}
</style>