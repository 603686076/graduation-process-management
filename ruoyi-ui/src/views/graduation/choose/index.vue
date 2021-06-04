<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="教师名" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入教师名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
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
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['graduation:teacher:add']"
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
          v-hasPermi="['graduation:teacher:edit']"
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
          v-hasPermi="['graduation:teacher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['graduation:teacher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      :data="teacherList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教师名" align="center" prop="nickName" />
      <el-table-column label="指导学生数" align="center" prop="quantity" />
      <!-- <el-table-column label="教师id" align="center" prop="id" /> -->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd(scope.row)"
            v-hasPermi="['graduation:student:add']"
            v-if="buttonFlag == 1"
            >选择</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['graduation:student:edit']"
            v-if="buttonFlag == 2"
            >退选</el-button
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

    <!-- 添加或修改选择导师任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form
        ref="chooseForm"
        :model="chooseForm"
        :rules="rules"
        label-width="80px"
      >
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listTeacher,
  getTeacher,
  listTeacherStudent,
} from "@/api/graduation/choose";
import {
  addStudent,
  delStudent,
  updateStudent,
} from "@/api/graduation/student";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Teacher",
  components: {},
  data() {
    return {
      // 教师学生关联表表数据
      teacherStudentList: [],
      // 查询教师学生关联表参数
      queryTeacherStudentParams: {},
      // 1选择导师按钮显示，2退选导师按钮展示
      buttonFlag: 0,
      // 1选择导师，2退选导师
      flag: 0,
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
      // 选择导师任务表格数据
      teacherList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickName: null,
      },
      // 表单参数
      form: {},
      // 教师学生关联表表单参数
      chooseForm: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.getUser();
    this.getHasChoose();
  },
  methods: {
    /** 查询这个学生是否已经选过导师 */
    getHasChoose() {
      this.queryTeacherStudentParams = {
        pageNum: 1,
        pageSize: 10,
        studentId: this.user.userId,
      };
      // 把这个学生id传给后台，查teacher_student有没有student_id等于这个学生id的数据
      listTeacherStudent(this.queryTeacherStudentParams).then((response) => {
        this.teacherStudentList = response.rows;
        // 查询出来有数据，说明这个学生已经选过导师了，选择按钮不再显示
        if (this.teacherStudentList.length != 0) {
          this.buttonFlag = 2;
        } else {
          this.buttonFlag = 1;
        }
        this.getHasChoose();
      });
    },
    /** 查询选择导师任务列表 */
    getList() {
      this.loading = true;
      listTeacher(this.queryParams).then((response) => {
        this.teacherList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getUser() {
      getUserProfile().then((response) => {
        this.user = response.data;
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
        group: null,
        title: null,
        quantity: null,
        description: null,
        updateTime: null,
      };
      this.resetForm("form");
    },

    // 教师学生关联表表单重置
    chooseReset() {
      this.chooseForm = {
        id: null,
        teacherId: null,
        studentId: null,
      };
      this.resetForm("chooseForm");
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
    /** 选择按钮操作 */
    handleAdd(row) {
      this.flag = 1;
      this.chooseReset();
      const id = row.id || this.ids;
      getTeacher(id).then((response) => {
        this.form = response.data;
        var quantityByQuery = this.form.quantity;
        // 如果导师可选人数大于0，执行选择操作
        if (quantityByQuery > 0) {
          this.chooseForm = {
            id: null,
            teacherId: this.form.id,
            studentId: this.user.userId,
          };
          this.open = true;
          this.title = "确定选择这个老师吗？";
        } else {
          this.msgInfo("来晚一步，已经被别的同学选完了哦")
        }
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();
      // const id = row.id || this.ids;
      // getTeacher(id).then((response) => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改选择导师任务";
      // });

      this.queryTeacherStudentParams = {
        pageNum: 1,
        pageSize: 10,
        studentId: this.user.userId,
      };
      // 退选的时候，判断一下这个学生选择的是不是这个导师
      // 把这个学生id传给后台，查teacher_student有没有student_id等于这个学生id的数据
      listTeacherStudent(this.queryTeacherStudentParams).then((response) => {
        this.teacherStudentList = response.rows;
        // 查询出来有数据，说明这个学生已经选过导师了，选择按钮不再显示
        var teacherIdByQuery = this.teacherStudentList[0].teacherId;
        // 如果是这个导师，执行查询操作
        if (teacherIdByQuery == row.id) {
          this.flag = 2;
          this.chooseReset();
          const id = row.id || this.ids;
          getTeacher(id).then((response) => {
            this.form = response.data;
            this.chooseForm = {
              id: null,
              teacherId: this.form.id,
              studentId: this.user.userId,
            };
            this.open = true;
            this.title = "确定取消选择这个老师吗？";
          });
        } else {
          this.msgInfo("你选择的不是这个导师哟~");
        }
        this.getHasChoose();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["chooseForm"].validate((valid) => {
        if (valid) {
          if (this.flag == 2) {
            updateStudent(this.chooseForm).then((response) => {
              this.msgSuccess("取消选择导师成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudent(this.chooseForm).then((response) => {
              this.msgSuccess("选择导师成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id;
      this.$confirm(
        '是否确认删除教师学生关联编号为"' + ids + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delStudent(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    // handleDelete(row) {
    //   const ids = row.id || this.ids;
    //   this.$confirm(
    //     '是否确认删除选择导师任务编号为"' + ids + '"的数据项?',
    //     "警告",
    //     {
    //       confirmButtonText: "确定",
    //       cancelButtonText: "取消",
    //       type: "warning",
    //     }
    //   )
    //     .then(function () {
    //       return delTeacher(ids);
    //     })
    //     .then(() => {
    //       this.getList();
    //       this.msgSuccess("删除成功");
    //     });
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "graduation/teacher/export",
        {
          ...this.queryParams,
        },
        `graduation_teacher.xlsx`
      );
    },
  },
};
</script>