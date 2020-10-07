<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加课程</el-button>
        </el-col>
      </el-row>

      <!--用户列表区域-->
      <el-table :data="courseList" border stripe :default-sort = "{prop: 'cno', order: 'ascending'}">
        <el-table-column label="课程号" prop="cno" sortable></el-table-column>
        <el-table-column label="课程名" prop="cname"></el-table-column>
        <el-table-column label="学分" prop="ccredit" sortable></el-table-column>
        <el-table-column label="操作" width="120px">
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="修改课程信息" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                         @click="showEditDialog(scope.row.cno)"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="删除课程信息" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"
                         @click="deleteCourseById(scope.row.cno)"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

<!--      <el-pagination-->
<!--          @size-change="sizeChange"-->
<!--          @current-change="currentChange"-->
<!--          :current-page="currentPage"-->
<!--          :page-sizes="[5, 10, 20, 50]"-->
<!--          :page-size="pageSize"-->
<!--          layout="total, sizes, prev, pager, next, jumper"-->
<!--          :total="totalLength"-->
<!--      >-->
<!--      </el-pagination>-->

    </el-card>

    <!--添加学生信息的对话框-->
    <el-dialog
        title="添加课程信息"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="addDialogClosed">
      <!--内容主体区-->
      <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="课程编号" prop="cno">
          <el-input v-model="addForm.cno" class="item"></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="cname">
          <el-input v-model="addForm.cname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="课程学分" prop="ccredit">
          <el-input v-model="addForm.ccredit" class="item"></el-input>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="createCourse">确 定</el-button>
            </span>
    </el-dialog>

    <!--修改学生信息的对话框-->
    <el-dialog
        title="修改课程信息"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="editDialogClosed">
      <!--内容主体区-->
      <el-form :model="editForm" :rules="editRules" ref="editFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="课程编号" prop="cno" disabled>
          <el-input v-model="editForm.cno" class="item" disabled></el-input>
        </el-form-item>
        <el-form-item label="课程名称" prop="cname">
          <el-input v-model="editForm.cname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="课程学分" prop="ccredit">
          <el-input v-model="editForm.ccredit" class="item"></el-input>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="modifyCourse()">确 定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Course",
  data() {
    // 校验课程编号是否存在
    const cnoRules = (rule, value, callback) => {
      // 使用Axios进行校验
      this.$http.get('course/checkCno/' + value)
          .then((res => {
            // 请求成功
            if (res.data.code === 0) {
              callback()
            } else {
              callback(new Error("课程编号已存在"))
            }
          }))
          .catch((err) => {
            // 如果请求失败则在控制台打印
            console.log(err)
          })
    }

    const validateInteger = (rule, value, callback) => {
      if (value && !(/^[1-9]\d*$/).test(value)) {
        callback(new Error('只能填写正整数'))
      } else {
        callback()
      }
    }

    return {
      addRules: {
        cno: [{
          required: true,
          message: '课程编号不能为空',
          trigger: 'blur'
        },
          {
            validator: cnoRules,
            trigger: 'blur'
          },
          {
            type: 'string',
            min: 6,
            max: 6,
            message: '班级编号不符合规范',
            trigger: 'blur',
          }
        ],
        cname: [{
          required: true,
          message: '课程名不能为空',
          trigger: 'blur'
        }],
        ccredit: [{
          required: true,
          message: '学分不能为空',
          trigger: 'blur',
        },
          {
            validator: validateInteger,
            trigger: 'blur'
          }],
      },
      editRules: {
        cno: [{
          required: true,
          message: '课程编号不能为空',
          trigger: 'blur'
        },
          {
            type: 'string',
            min: 6,
            max: 6,
            message: '班级编号不符合规范',
            trigger: 'blur',
          }
        ],
        cname: [{
          required: true,
          message: '课程名不能为空',
          trigger: 'blur'
        }],
        ccredit: [{
          required: true,
          message: '学分不能为空',
          trigger: 'blur',
        },
          {
            validator: validateInteger,
            trigger: 'blur'
          }],
      },
      addForm: {
        cno: '',
        cname: '',
        ccredit: 2,
      },
      editForm: {
        cno: '',
        cname: '',
        ccredit: ''
      },
      addDialogVisible: false,
      editDialogVisible: false,
      courseList: [],
      totalLength: 0,
      currentPage: 1,
      pageSize: 5,
    }
  },
  mounted() {
    // this.getCourseListByPage()
    this.getCourseList()
  },

  methods: {
    modifyCourse() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('course/modify', {
          cno: this.editForm.cno,
          cname: this.editForm.cname,
          ccredit: this.editForm.ccredit
        })
        this.$message.success('修改课程信息成功')
        // 隐藏添加用户的对话框
        this.editDialogVisible = false
        // 重新获取用户列表数据
        this.getCourseList()
      })
    },

    showEditDialog(cno) {
      this.editForm = this.courseList.filter(obj => obj.cno === cno)[0];
      this.editDialogVisible = true;
    },

    deleteCourseById(cno) {
      this.$confirm('此操作将删除该课程信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.$http.get('course/delete/' + cno);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getCourseList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    createCourse() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('course/create', {
          cno: this.addForm.cno,
          cname: this.addForm.cname,
          ccredit: this.addForm.ccredit,
        })
        this.$message.success('添加课程信息成功')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 重新获取用户列表数据
        this.getCourseList()
      })
    },

    sizeChange(val) {
      this.pageSize = val
      this.getCourseListByPage();
    },

    currentChange(val) {
      this.currentPage = val;
      this.getCourseListByPage();
    },

    async getCourseListByPage() {
      const {data: res} = await this.$http.post('course/getCourseListByPage', {
        currentPage: this.currentPage,
        pageSize: this.pageSize
      });
      this.courseList = res.data.list;
      this.totalLength = res.data.length;
    },

    async getCourseList() {
      const {data: res} = await this.$http.get('course/getCourseList');
      this.courseList = res.data.list;
      this.totalLength = res.data.length;
    },

    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },

    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },
  }
}
</script>

<style scoped>
.item {
  width: 300px;
}
</style>
