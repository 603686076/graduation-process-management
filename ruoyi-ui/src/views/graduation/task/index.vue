<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="任务类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择任务类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item> -->
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="完成角色" prop="target">
        <el-select v-model="queryParams.target" placeholder="请选择完成角色" clearable size="small">
          <el-option
            v-for="dict in targetOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="上传文件名称规则" prop="uploadRegexName">
        <el-input
          v-model="queryParams.uploadRegexName"
          placeholder="请输入上传文件名称规则"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="模板文件名称" prop="filename">
        <el-input
          v-model="queryParams.filename"
          placeholder="请输入模板文件名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="发布者ID" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入发布者ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="文件类型后缀" prop="extName">
        <el-input
          v-model="queryParams.extName"
          placeholder="请输入文件类型后缀"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['graduation:task:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['graduation:task:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['graduation:task:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['graduation:task:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="任务ID" align="center" prop="id" /> -->
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="描述" align="center" prop="description" />
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="任务类型" align="center" prop="type" :formatter="typeFormat" /> -->
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="完成角色" align="center" prop="target" :formatter="targetFormat" />
      <!-- <el-table-column label="模板文件名" align="center" prop="uploadRegexName" /> -->
      <!-- 上传文件名称规则和模板文件名称弄反了,就这样吧,只能先反着了 -->
       <el-table-column label="上传文件名称规则" align="center" prop="filename" />
      <!-- <el-table-column label="发布者ID" align="center" prop="teacherId" />
      <el-table-column label="文件类型后缀" align="center" prop="extName" />  -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['graduation:task:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['graduation:task:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable size="small"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker clearable size="small"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="任务类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择任务类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item> -->
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="完成角色">
          <el-radio-group v-model="form.target">
            <el-radio
              v-for="dict in targetOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传文件名称规则" prop="filename">
          <el-input v-model="form.filename" placeholder="请输入上传文件名称规则" />
        </el-form-item>
        <!-- <el-form-item label="模板文件名称" prop="filename">
          <el-input v-model="form.filename" placeholder="请输入模板文件名称" />
        </el-form-item>
        <el-form-item label="发布者ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入发布者ID" />
        </el-form-item>
        <el-form-item label="文件类型后缀" prop="extName">
          <el-input v-model="form.extName" placeholder="请输入文件类型后缀" /> -->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/graduation/task";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Task",
  components: {
  },
  data() {
    return {
      // 用户
      user: {},
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 任务表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 任务类型字典
      typeOptions: [],
      // 状态字典
      statusOptions: [],
      // 完成角色字典
      targetOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        description: null,
        startTime: null,
        endTime: null,
        type: null,
        status: null,
        target: null,
        uploadRegexName: null,
        filename: null,
        teacherId: null,
        extName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "任务类型不能为空", trigger: "change" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "blur" }
        ],
        target: [
          { required: true, message: "完成角色不能为空", trigger: "blur" }
        ],
        teacherId: [
          { required: true, message: "发布者ID不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getUser();
    this.getDicts("task_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("task_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("complete_role").then(response => {
      this.targetOptions = response.data;
    });
  },
  methods: {
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
      });
    },
    /** 查询任务列表 */
    getList() {
      this.loading = true;
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 任务类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 完成角色字典翻译
    targetFormat(row, column) {
      return this.selectDictLabel(this.targetOptions, row.target);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        description: null,
        startTime: null,
        endTime: null,
        type: null,
        status: "0",
        target: "0",
        uploadRegexName: null,
        filename: null,
        teacherId: null,
        extName: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTask(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.form.teacherId =  this.user.userId;
      this.form.type = 4;
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTask(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除任务编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTask(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('graduation/task/export', {
        ...this.queryParams
      }, `graduation_task.xlsx`)
    }
  }
};
</script>