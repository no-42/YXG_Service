<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="name">
        <el-input v-model="queryParams.name" clearable placeholder="请输入商品名称"/>
      </el-form-item>
      <el-form-item label="分类名称" prop="categoryName">
        <el-input v-model="queryParams.categoryName" clearable placeholder="请输入商品分类"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
            v-hasPermi="['market:goods:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['market:goods:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['market:goods:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['market:goods:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="goodsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="名称" align="center" prop="name"/>
      <el-table-column label="价格" align="center" prop="price">
        <template #default="{row}">
          {{row.price}} / {{row.priceUnit}}
        </template>
      </el-table-column>
      <el-table-column label="分类" align="center" prop="categoryName"/>
      <el-table-column label="规格" align="center" prop="specName"/>
      <el-table-column label="供应商" align="center" prop="supplierName"/>
      <el-table-column label="产地" align="center" prop="originName"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
              type="text"
              icon="Edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['market:goods:edit']"
          >修改
          </el-button>
          <el-button
              type="text"
              icon="Delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['market:goods:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改商品信息对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body :close-on-click-modal="false">
      <el-form ref="goodsRef" :model="form" :rules="rules" label-width="100px">
        <el-row>
          <el-form-item label="商品名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入商品名称"/>
          </el-form-item>
        </el-row>
        <el-row>
          <el-col :sm="12">
            <el-form-item label="商品价格" prop="name">
              <el-input v-model="form.price" placeholder="请输入商品价格"/>
            </el-form-item>
          </el-col>
          <el-col :sm="12">
            <el-form-item label="价格单位" prop="priceUnit">
              <el-input v-model="form.priceUnit" placeholder="请输入商品价格单位"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :sm="12">
            <el-form-item label="分类" property="categoryId">
              <goods-category-select v-model="form.categoryId" :model-name="form.categoryName"></goods-category-select>
            </el-form-item>

          </el-col>
          <el-col :sm="12">
            <el-form-item label="规格" property="specId">
              <goods-spec-select v-model="form.specId" :model-name="form.specName"></goods-spec-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :sm="12">
            <el-form-item label="供应商" property="supplierId">
              <goods-supplier-select v-model="form.supplierId" :model-name="form.supplierName"></goods-supplier-select>
            </el-form-item>
          </el-col>
          <el-col :sm="12">
            <el-form-item label="产地" property="originId">
              <goods-origin-select v-model="form.originId" :model-name="form.originName"></goods-origin-select>
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Goods">
import {getCurrentInstance, ref, toRefs, reactive} from 'vue';
import {listGoods, getGoods, delGoods, addGoods, updateGoods} from "@/api/market/goods";
import GoodsSpecSelect from '@/components/CreatableSelect/goodsSpecSelect'
import GoodsOriginSelect from '@/components/CreatableSelect/goodsOriginSelect'
import GoodsCategorySelect from '@/components/CreatableSelect/goodsCategorySelect'
import GoodsSupplierSelect from '@/components/CreatableSelect/goodsSupplierSelect'

const {proxy} = getCurrentInstance();

const goodsList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    categoryName: null,
    name: null,
  },
  rules: {}
});

const {queryParams, form, rules} = toRefs(data);

/** 查询商品信息列表 */
function getList() {
  loading.value = true;
  listGoods(queryParams.value).then(response => {
    goodsList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    categoryId: null,
    specId: null,
    name: null,
    supplierId: null,
    originId: null,
    price: null,
    priceUnit: null
  };
  proxy.resetForm("goodsRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加商品信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const goodsId = row.id || ids.value
  getGoods(goodsId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改商品信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["goodsRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateGoods(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addGoods(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const goodsId = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除商品信息编号为"' + goodsId + '"的数据项？').then(function () {
    return delGoods(goodsId);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {
  });
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('market/goods/export', {
    ...queryParams.value
  }, `goods_${new Date().getTime()}.xlsx`)
}

getList();
</script>
