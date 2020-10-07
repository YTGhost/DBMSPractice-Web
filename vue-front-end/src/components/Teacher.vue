<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>教师管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="6">
          <el-input placeholder="模糊搜索教师编号或姓名" v-model="queryInfo" clearable @input="queryChange">
            <el-button slot="append" icon="el-icon-search" @click="queryTeacherList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加教师</el-button>
        </el-col>
      </el-row>

      <!--用户列表区域-->
      <el-table :data="teacherList" border stripe :default-sort = "{prop: 'tno', order: 'ascending'}">
        <el-table-column label="编号" prop="tno" sortable></el-table-column>
        <el-table-column label="姓名" prop="tname"></el-table-column>
        <el-table-column label="性别" prop="tsex"
                         :filters="[{text: '男', value: '男'}, {text: '女', value: '女'}]"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column label="出生日期" prop="tbirthday" sortable></el-table-column>
        <el-table-column label="职称" prop="ttitle"
                         :filters="[{text: '教授', value: '教授'}, {text: '副教授', value: '副教授'}, {text: '讲师', value: '讲师'}, {text: '助教', value: '助教'}]"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column label="操作" width="120px">
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="修改教师信息" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                         @click="showEditDialog(scope.row.tno)"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="删除教师信息" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"
                         @click="deleteTeacherById(scope.row.tno)"></el-button>
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
        title="添加学生信息"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="addDialogClosed">
      <!--内容主体区-->
      <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="教师编号" prop="tno">
          <el-input v-model="addForm.tno" class="item"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="tname">
          <el-input v-model="addForm.tname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="tsex">
          <el-select v-model="addForm.tsex" placeholder="请选择" class="item">
            <el-option
                v-for="item in sexOptions"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="tbirthday">
          <el-date-picker
              v-model="addForm.tbirthday"
              type="date"
              :clearable="false"
              :editable="false"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              class="item">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="职称" prop="ttitle">
          <el-select v-model="addForm.ttitle" placeholder="请选择" class="item">
            <el-option
                v-for="item in titleOptions"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="createTeacher">确 定</el-button>
            </span>
    </el-dialog>

    <!--修改学生信息的对话框-->
    <el-dialog
        title="修改教师信息"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="editDialogClosed">
      <!--内容主体区-->
      <el-form :model="editForm" :rules="editRules" ref="editFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="教师编号" prop="tno" disabled>
          <el-input v-model="editForm.tno" class="item" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="tname">
          <el-input v-model="editForm.tname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="tsex">
          <el-select v-model="editForm.tsex" placeholder="请选择" class="item">
            <el-option
                v-for="item in sexOptions"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="tbirthday">
          <el-date-picker
              v-model="editForm.tbirthday"
              type="date"
              :clearable="false"
              :editable="false"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              class="item">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="职称" prop="ttitle">
          <el-select v-model="editForm.ttitle" placeholder="请选择" class="item">
            <el-option
                v-for="item in titleOptions"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <!--底部区域-->
      <span slot="footer" class="dialog-footer">
                <el-button @click="editDialogVisible= false">取 消</el-button>
                <el-button type="primary" @click="modifyTeacher()">确 定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Teacher",
  data() {
    // 校验编号是否存在
    const rulesSno = (rule, value, callback) => {
      // 使用Axios进行校验
      this.$http.get('teacher/checkTno/' + value)
          .then((res => {
            // 请求成功
            if (res.data.code === 0) {
              callback()
            } else {
              callback(new Error("编号已存在"))
            }
          }))
          .catch((err) => {
            // 如果请求失败则在控制台打印
            console.log(err)
          })
    }
    return {
      addRules: {
        tno: [{
          required: true,
          message: '教师编号不能为空',
          trigger: 'blur'
        },
          {
            validator: rulesSno,
            trigger: 'blur'
          },
          {
            type: 'string',
            min: 6,
            max: 6,
            message: '编号不符合规范',
            trigger: 'blur',
          }
        ],
        tname: [{
          required: true,
          message: '姓名不能为空',
          trigger: 'blur'
        }],
        tsex: [{
          required: true,
          message: '性别不能为空',
          trigger: 'blur'
        }],
        tbirthday: [{
          required: true,
          message: '出生日期不能为空',
          trigger: 'blur'
        }],
        ttitle: [{
          required: true,
          message: '职称不能为空'
        }]
      },
      editRules: {
        tno: [{
          required: true,
          message: '教师编号不能为空',
          trigger: 'blur'
        },
          {
            type: 'string',
            min: 6,
            max: 6,
            message: '编号不符合规范',
            trigger: 'blur',
          }
        ],
        tname: [{
          required: true,
          message: '姓名不能为空',
          trigger: 'blur'
        }],
        tsex: [{
          required: true,
          message: '性别不能为空',
          trigger: 'blur'
        }],
        tbirthday: [{
          required: true,
          message: '出生日期不能为空',
          trigger: 'blur'
        }],
        ttitle: [{
          required: true,
          message: '职称不能为空'
        }]
      },
      queryInfo: '',
      addDialogVisible: false,
      editDialogVisible: false,
      teacherList: [],
      totalLength: 0,
      currentPage: 1,
      pageSize: 5,
      addForm: {
        tno: '',
        tname: '',
        tsex: '男',
        tbirthday: '1970-01-01',
        ttitle: '教授',
      },
      editForm: {
        tno: '',
        tname: '',
        tsex: '',
        tbirthday: '',
        ttitle: ''
      },
      sexOptions: ['男', '女'],
      titleOptions: ['教授', '副教授', '讲师', '助教'],
    }
  },
  mounted() {
    this.getTeacherList()
  },
  methods: {
    modifyTeacher() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('teacher/modify', {
          tno: this.editForm.tno,
          tname: this.editForm.tname,
          tsex: this.editForm.tsex,
          tbirthday: this.editForm.tbirthday,
          ttitle: this.editForm.ttitle
        })
        this.$message.success('修改教师信息成功')
        // 隐藏添加用户的对话框
        this.editDialogVisible = false
        // 重新获取用户列表数据
        this.getTeacherList()
      })
    },

    showEditDialog(tno) {
      this.editForm = this.teacherList.filter(obj => obj.tno === tno)[0];
      this.editDialogVisible = true;
    },

    deleteTeacherById(tno) {
      this.$confirm('此操作将删除该课程信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.$http.get('teacher/delete/' + tno);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getTeacherList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    sizeChange(val) {
      this.pageSize = val
      this.getTeacherListByPage();
    },

    currentChange(val) {
      this.currentPage = val;
      this.getTeacherListByPage();
    },

    createTeacher() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('teacher/create', {
          tno: this.addForm.tno,
          tname: this.addForm.tname,
          tsex: this.addForm.tsex,
          tbirthday: this.addForm.tbirthday,
          ttitle: this.addForm.ttitle
        })
        this.$message.success('添加教师信息成功')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 重新获取用户列表数据
        this.getTeacherList()
      })
    },

    async getTeacherListByPage() {
      const {data: res} = await this.$http.post('teacher/getTeacherListByPage', {
        currentPage: this.currentPage,
        pageSize: this.pageSize
      });
      this.teacherList = res.data.list;
      this.totalLength = res.data.length;
    },

    async getTeacherList() {
      const {data: res} = await this.$http.get('teacher/getTeacherList');
      this.teacherList = res.data.list;
      this.totalLength = res.data.length;
    },

    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },

    // 监听添加学生对话框的关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },

    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },

    queryChange() {
      if(this.queryInfo === '') {
        this.getTeacherList()
      }
    },

    async queryTeacherList() {
      const {data: res} = await this.$http.get('teacher/query/' + this.queryInfo)
      this.teacherList = res.data
    }
  }
}
</script>

<style scoped>
.item {
  width: 300px;
}
</style>
