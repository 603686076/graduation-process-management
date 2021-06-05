<template>
  <div class="app-container">
    <!-- <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    > -->
      <!-- <el-form-item label="任务ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="任务描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入任务描述"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件名称" prop="filename">
        <el-input
          v-model="queryParams.filename"
          placeholder="请输入文件名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form> -->

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['graduation:studenttask:add']"
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
          v-hasPermi="['graduation:studenttask:edit']"
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
          v-hasPermi="['graduation:studenttask:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['graduation:studenttask:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      :data="studenttaskList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="学生任务ID" align="center" prop="id" /> -->
      <!-- <el-table-column label="任务ID" align="center" prop="taskId" /> -->
      <el-table-column label="任务描述" align="center" prop="description" />
      <el-table-column label="文件名称" align="center" prop="filename" />
      <!-- <el-table-column label="学生ID" align="center" prop="studentId" /> -->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['graduation:studenttask:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['graduation:studenttask:remove']"
          >删除</el-button> -->
          <el-button
            size="mini"
            type="text"
            @click="handleViewAdvice(scope.row)"
            >查看教师建议</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改所有任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务ID" prop="taskId">
          <el-input v-model="form.taskId" placeholder="请输入任务ID" />
        </el-form-item>
        <el-form-item label="任务描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入任务描述" />
        </el-form-item>
        <el-form-item label="文件名称" prop="filename">
          <el-input v-model="form.filename" placeholder="请输入文件名称" />
        </el-form-item>
        <el-form-item label="学生ID" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生ID" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 查看教师意见对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="suggestionOpen"
      width="500px"
      append-to-body
    >
      <el-form
        ref="suggestionForm"
        :model="suggestionForm"
        :rules="rules"
        label-width="80px"
      >
        <el-form-item label="建议" prop="suggestion">
          <el-input :disabled="true" v-model="suggestionForm.suggestion" />
        </el-form-item>
      </el-form>
      <!-- <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitSuggestionForm"
          >确 定</el-button
        >
        <el-button @click="cancelSuggestionOpen">取 消</el-button>
      </div> -->
    </el-dialog>
  </div>
</template>

<script>
import {
  listStudenttask,
  getStudenttask,
  delStudenttask,
  addStudenttask,
  updateStudenttask,
} from "@/api/graduation/studenttask";
import { listStudenttaskView } from "@/api/graduation/viewsuggestions";
import { listSuggestion } from "@/api/graduation/suggestion";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Studenttask",
  components: {},
  data() {
    return {
      // 用户
      user: {},
      // 是否显示查看教师建议会话框
      suggestionOpen: false,
      // 查询出来的建议
      suggestList: [],
      // 教师建议表单参数（通过将学生任务id封装成suggestionForm表，去后台调用/list查询出整个建议的信息）
      suggestionForm: {},
      // 下载时通过包装文件名去后台查找返回的数据
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
      // 所有任务表格数据
      studenttaskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: null,
        description: null,
        filename: null,
        studentId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taskId: [
          { required: true, message: "任务ID不能为空", trigger: "blur" },
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getUser();
  },
  methods: {
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
        this.getList();
      });
    },
    /** 查询所有任务列表 */
    getList() {
      this.loading = true;
      this.queryParams.studentId = this.user.userId;
      listStudenttaskView(this.queryParams).then((response) => {
        this.studenttaskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        taskId: null,
        description: null,
        filename: null,
        updateTime: null,
        studentId: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    // 建议表单重置
    suggestionFormReset() {
      this.suggestionForm = {
        id: null,
        teacherId: null,
        studentId: null,
        taskId: null,
        suggestion: null,
        updateTime: null,
      };
    },
    /** 查看教师建议按钮 */
    handleViewAdvice(row) {
      this.suggestionFormReset();
      this.suggestionForm.taskId = row.id;
      listSuggestion(this.suggestionForm).then((response) => {
        this.suggestList = response.rows;
        // 如果不存在，返回还没有建议
        if (this.suggestList.length == 0) {
          this.msgInfo("教师还没有给出建议呢~");
        } else {
          // 如果存在，查看建议
          this.suggestionForm = this.suggestList[0];
          this.suggestionOpen = true;
          this.title = "查看建议";
        }
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加所有任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getStudenttask(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改所有任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateStudenttask(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudenttask(this.form).then((response) => {
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
      this.$confirm(
        '是否确认删除所有任务编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delStudenttask(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "graduation/studenttask/export",
        {
          ...this.queryParams,
        },
        `graduation_studenttask.xlsx`
      );
    },
  },
};
</script>