<template>
  <div class="app-container">
    <div>{{ fileForm.fileName }}</div>
    <div>{{ fileForm.filePath }}</div>
    <div>{{ resultFileName }}</div>
    <!-- <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务描述" prop="description">
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
      </el-form-item>
      <el-form-item label="学生ID" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
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
      <el-table-column label="学生任务ID" align="center" prop="id" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="任务描述" align="center" prop="description" />
      <el-table-column label="文件名称" align="center" prop="filename" />
      <el-table-column label="学生ID" align="center" prop="studentId" />
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
            icon="el-icon-upload2"
            @click="handleUpload(scope.row)"
            >上传文件</el-button
          >
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateFile(scope.row)"
            >修改文件</el-button
          > -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-download"
            @click="handleDownload(scope.row)"
            >下载</el-button
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

    <!-- 上传文件对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".docx, .doc"
        :action="upload.url"
        :headers="upload.headers"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        :file-list="upload.fileList"
        :before-upload="handleFileBeforeUpload"
      >
        <el-button slot="trigger" size="small" type="primary"
          >选取文件</el-button
        >
        <el-button
          style="margin-left: 10px"
          size="small"
          type="success"
          :loading="upload.isUploading"
          @click="submitUpload"
          >上传到服务器</el-button
        >
        <el-button
          style="margin-left: 100px"
          size="small"
          type="submit"
          @click="submitFileForm"
          v-hasPermi="['graduation:studentfileinfo:add']"
          >提交</el-button
        >
        <div slot="tip" class="el-upload__tip">
          只能上传jpg/png文件，且不超过500kb
        </div>
      </el-upload>
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
  getStudentfileinfoByFileName,
} from "@/api/graduation/studenttask";
import { getToken } from "@/utils/auth";
import {
  addStudentfileinfo,
  listStudentfileinfo,
} from "@/api/graduation/studentfileinfo";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Studenttask",
  components: {},
  data() {
    return {
      // 下载时通过包装文件名去后台查找返回的数据
      studentfileinfoList: [],
      // 没用了
      copyFile: null,
      // 拼接后的文件名
      resultFileName: "",
      // 用户
      user: {},
      // 1上传文件，2修改文件
      flag: 0,
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
      // 上传文件参数
      upload: {
        // 是否显示弹出层（上传文件）
        open: false,
        // 弹出层标题（上传文件）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/file/upload",
        // 上传的文件列表
        fileList: [],
      },
      // 文件表单参数
      fileForm: {},
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
    this.getList();
    this.getUser();
  },
  methods: {
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
      });
    },
    /** 查询所有任务列表 */
    getList() {
      this.loading = true;
      listStudenttask(this.queryParams).then((response) => {
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
    // 文件表单重置
    fileFormReset() {
      this.fileForm = {
        fileId: null,
        fileName: null,
        filePath: null,
      };
      this.resetForm("fileForm");
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加所有任务";
    },
    /** 上传文件按钮操作 */
    handleUpload(row) {
      this.flag = 1;
      this.reset();
      this.upload.title = "上传文件";
      this.upload.open = true;
      // 将前端的行数据传入form
      this.form.taskId = row.taskId;
      this.form.description = row.description;
      this.form.studentId = this.user.userId;

      // 通过前端传入行数据获取fileName
      var list = row.filename.split(",");
      var result = "";
      list.forEach((item, index, array) => {
        if (item.charAt(0) == "@") {
          if (item == "@学生姓名") {
            result += this.user.nickName + "-";
          }
          if (item == "@学号") {
            result += this.user.userName + "-";
          }
        } else {
          index == array.length - 1 ? (result += item) : (result += item + "-");
        }
      });
      this.resultFileName = result;
    },
    handleUpdateFile() {
      this.flag = 2;
      this.upload.title = "修改文件";
      this.upload.open = true;
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
    /** 提交文件表单按钮 */
    submitFileForm() {
      // // 通过fileName获取student_file_info表数据
      // getStudentfileinfoByFileName (this.fileForm.fileName).then((response) => {
      //   this.form = response.data;
      //   // 进行
      // });
      // 当文件已经上传到服务器后才能提交
      if (this.fileForm.fileName != null && this.fileForm.filePath != null) {
        if (this.flag == 1) {
          this.form.filename = this.resultFileName;
          this.fileForm.fileName = this.resultFileName;
          // this.form.filename = this.fileForm.fileName;
          // 向student_task插入新数据
          addStudenttask(this.form).then((response) => {
            this.msgSuccess("数据库表student_task新增成功");
            this.upload.open = false;
          });
          // 向student_file_info插入新数据
          addStudentfileinfo(this.fileForm).then((response) => {
            this.msgSuccess("数据库表student_file_info新增成功");
            this.upload.open = false;
          });
        }
      }
      // addStudenttask(this.form).then((response) => {
      //   this.msgSuccess("新增成功");
      //   this.open = false;
      //   this.getList();
      // });
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
    // 文件上传前处理
    handleFileBeforeUpload(file) {
      // Cannot assign to read only property 'name'
      // file.name = this.resultFileName;
      // console.log(file);
      // this.copyFile = new File([file], this.resultFileName);
    },
    // 文件提交处理
    submitUpload() {
      // this.upload.fileList = [{ name: this.resultFileName, url: this.upload.fileList[0].url }];
      // console.log(this.upload.fileList);
      // this.upload.fileList = [{name: this.resultFileName, url: 'D:/ruoyi/uploadPath/2021/06/02/上传文件测试.png'}];
      this.$refs.upload.submit();
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      // file.name = this.resultFileName;
      // console.log("文件上传中处理");
      // console.log(file);
      // console.log(fileList);
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.isUploading = false;
      this.fileFormReset();
      this.fileForm.fileName = response.data.name;
      this.fileForm.filePath = response.data.url;
      this.msgSuccess(response.msg);
      // console.log("文件上传成功处理");
      // console.log(file);
      // console.log(fileList);
    },
    // 文件下载处理
    handleDownload(row) {
      // 通过前端传入行数据获取fileName
      var list = row.filename.split(",");
      var result = "";
      list.forEach((item, index, array) => {
        if (item.charAt(0) == "@") {
          if (item == "@学生姓名") {
            result += this.user.nickName + "-";
          }
          if (item == "@学号") {
            result += this.user.userName + "-";
          }
        } else {
          index == array.length - 1 ? (result += item) : (result += item + "-");
        }
      });
      console.log(result);

      this.fileFormReset();
      this.fileForm.fileName = result;
      // console.log(this.fileForm);
      listStudentfileinfo(this.fileForm).then((response) => {
        this.studentfileinfoList = response.rows;
        if (this.studentfileinfoList.length != 0) {
          var url = this.studentfileinfoList[0].filePath;
          var name = this.fileForm.fileName;
          var suffix = url.substring(url.lastIndexOf("."), url.length);
          const a = document.createElement("a");
          a.setAttribute("download", name + suffix);
          a.setAttribute("target", "_blank");
          a.setAttribute("href", url);
          a.click();
        } else {
          this.$message.error("文件不存在");
        } 
      });
      // console.log(this.fileForm);
    },
  },
};
</script>