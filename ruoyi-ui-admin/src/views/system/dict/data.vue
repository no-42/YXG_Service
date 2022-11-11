<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="字典名称" prop="type">
        <el-select v-model="queryParams.type">
          <el-option
              v-for="item in typeOptions"
              :key="item.id"
              :label="item.name"
              :value="item.type"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="字典标签" prop="label">
        <el-input
            v-model="queryParams.label"
            placeholder="请输入字典标签"
            clearable
            @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="enable">
        <el-select v-model="queryParams.enable" placeholder="数据状态" clearable>
          <el-option :value="true" label="启用"/>
          <el-option :value="false" label="禁用"/>
        </el-select>
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
            v-hasPermi="['system:dict:add']"
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
            v-hasPermi="['system:dict:edit']"
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
            v-hasPermi="['system:dict:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Download"
            @click="handleExport"
            v-hasPermi="['system:dict:export']"
        >导出
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="warning"
            plain
            icon="Close"
            @click="handleClose"
        >关闭
        </el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--      <el-table-column label="字典编码" align="center" prop="id"/>-->
      <el-table-column label="字典标签" align="center" prop="label">
        <template #default="scope">
          <span v-if="scope.row.listClass == '' || scope.row.listClass == 'default'">{{ scope.row.label }}</span>
          <el-tag v-else :type="scope.row.listClass == 'primary' ? '' : scope.row.listClass">{{
            scope.row.label
            }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="字典键值" align="center" prop="value"/>
      <el-table-column label="字典排序" align="center" prop="sort"/>
      <el-table-column label="状态" align="center" prop="enable">
        <template #default="scope">
          {{ scope.row.enable ? "启用" : "禁用" }}
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
              type="text"
              icon="Edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:dict:edit']"
          >修改
          </el-button>
          <el-button
              type="text"
              icon="Delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:dict:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="dataRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="字典类型">
          <el-input v-model="form.type" :disabled="true"/>
        </el-form-item>
        <el-form-item label="数据标签" prop="lable">
          <el-input v-model="form.label" placeholder="请输入数据标签"/>
        </el-form-item>
        <el-form-item label="数据键值" prop="value">
          <el-input v-model="form.value" placeholder="请输入数据键值"/>
        </el-form-item>
        <el-form-item label="样式属性" prop="cssClass">
          <el-input v-model="form.cssClass" placeholder="请输入样式属性"/>
        </el-form-item>
        <el-form-item label="显示排序" prop="sort">
          <el-input-number v-model="form.sort" controls-position="right" :min="0"/>
        </el-form-item>
        <el-form-item label="回显样式" prop="listClass">
          <el-select v-model="form.listClass">
            <el-option
                v-for="item in listClassOptions"
                :key="item.value"
                :label="item.label + '(' + item.value + ')'"
                :value="item.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="enable">
          <el-radio-group v-model="form.enable">
            <el-radio :label="true">启用</el-radio>
            <el-radio :label="false">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
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

<script setup name="Data">
    import {ref, getCurrentInstance, reactive, toRefs} from "vue"
    import {useRoute} from 'vue-router'
    import useDictStore from '@/store/modules/dict'
    import {optionselect as getDictOptionselect, getType} from "@/api/system/dict/type";
    import {listData, getData, delData, addData, updateData} from "@/api/system/dict/data";

    const {proxy} = getCurrentInstance();
    const {sys_normal_disable} = proxy.useDict("sys_normal_disable");

    const dataList = ref([]);
    const open = ref(false);
    const loading = ref(true);
    const showSearch = ref(true);
    const ids = ref([]);
    const single = ref(true);
    const multiple = ref(true);
    const total = ref(0);
    const title = ref("");
    const defaultDictType = ref("");
    const typeOptions = ref([]);
    const route = useRoute();
    // 数据标签回显样式
    const listClassOptions = ref([
        {value: "default", label: "默认"},
        {value: "primary", label: "主要"},
        {value: "success", label: "成功"},
        {value: "info", label: "信息"},
        {value: "warning", label: "警告"},
        {value: "danger", label: "危险"}
    ]);

    const data = reactive({
        form: {},
        queryParams: {
            pageNum: 1,
            pageSize: 10,
            name: undefined,
            type: undefined,
            enable: undefined
        },
        rules: {
            label: [{required: true, message: "数据标签不能为空", trigger: "blur"}],
            value: [{required: true, message: "数据键值不能为空", trigger: "blur"}],
            sort: [{required: true, message: "数据顺序不能为空", trigger: "blur"}]
        }
    });

    const {queryParams, form, rules} = toRefs(data);

    /** 查询字典类型详细 */
    function getTypes(dictId) {
        getType(dictId).then(response => {
            queryParams.value.type = response.data.type;
            defaultDictType.value = response.data.type;
            getList();
        });
    }

    /** 查询字典类型列表 */
    function getTypeList() {
        getDictOptionselect().then(response => {
            typeOptions.value = response.data;
        });
    }

    /** 查询字典数据列表 */
    function getList() {
        loading.value = true;
        listData(queryParams.value).then(response => {
            dataList.value = response.rows;
            total.value = response.total;
            loading.value = false;
        });
    }

    /** 取消按钮 */
    function cancel() {
        open.value = false;
        reset();
    }

    /** 表单重置 */
    function reset() {
        form.value = {
            code: undefined,
            label: undefined,
            value: undefined,
            cssClass: undefined,
            listClass: "default",
            sort: 0,
            enable: undefined,
            remark: undefined
        };
        proxy.resetForm("dataRef");
    }

    /** 搜索按钮操作 */
    function handleQuery() {
        queryParams.value.pageNum = 1;
        getList();
    }

    /** 返回按钮操作 */
    function handleClose() {
        const obj = {path: "/system/dict"};
        proxy.$tab.closeOpenPage(obj);
    }

    /** 重置按钮操作 */
    function resetQuery() {
        proxy.resetForm("queryRef");
        queryParams.value.type = defaultDictType;
        handleQuery();
    }

    /** 新增按钮操作 */
    function handleAdd() {
        reset();
        open.value = true;
        title.value = "添加字典数据";
        form.value.type = queryParams.value.type;
    }

    /** 多选框选中数据 */
    function handleSelectionChange(selection) {
        ids.value = selection.map(item => item.id);
        single.value = selection.length !== 1;
        multiple.value = !selection.length;
    }

    /** 修改按钮操作 */
    function handleUpdate(row) {
        reset();
        const dictCode = row.id || ids.value;
        getData(dictCode).then(response => {
            form.value = response.data;
            open.value = true;
            title.value = "修改字典数据";
        });
    }

    /** 提交按钮 */
    function submitForm() {
        proxy.$refs["dataRef"].validate(valid => {
            if (valid) {
                if (form.value.id !== undefined) {
                    updateData(form.value).then(response => {
                        useDictStore().removeDict(queryParams.value.type);
                        proxy.$modal.msgSuccess("修改成功");
                        open.value = false;
                        getList();
                    });
                } else {
                    addData(form.value).then(response => {
                        useDictStore().removeDict(queryParams.value.type);
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
        const dictCodes = row.dictCode || ids.value;
        proxy.$modal.confirm('是否确认删除字典编码为"' + dictCodes + '"的数据项？').then(function () {
            return delData(dictCodes);
        }).then(() => {
            getList();
            proxy.$modal.msgSuccess("删除成功");
            useDictStore().removeDict(queryParams.value.dictType);
        }).catch(() => {
        });
    }

    /** 导出按钮操作 */
    function handleExport() {
        proxy.download("system/dict/data/export", {
            ...queryParams.value
        }, `dict_data_${new Date().getTime()}.xlsx`);
    }

    getTypes(route.params && route.params.dictId);
    getTypeList();
</script>
