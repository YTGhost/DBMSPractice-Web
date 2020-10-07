<template>
  <div>
    <!--面包屑导航区域-->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>学生管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="6">
          <el-input placeholder="模糊搜索学号或姓名" v-model="queryInfo" clearable @input="queryChange">
            <el-button slot="append" icon="el-icon-search" @click="queryStudentList"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加学生</el-button>
        </el-col>
      </el-row>

      <!--用户列表区域-->
      <el-table :data="studentList" border stripe :default-sort = "{prop: 'sno', order: 'ascending'}">
        <el-table-column label="学号" prop="sno" sortable></el-table-column>
        <el-table-column label="姓名" prop="sname"></el-table-column>
        <el-table-column label="性别" prop="ssex"
                         :filters="[{text: '男', value: '男'}, {text: '女', value: '女'}]"
                         :filter-method="filterSexHandler"></el-table-column>
        <el-table-column label="出生日期" prop="sbirthday" sortable></el-table-column>
        <el-table-column label="班级" prop="classno" sortable
                         :filters="classnoFilters"
                         :filter-method="filterClassnoHandler"></el-table-column>
        <el-table-column label="操作" width="120px">
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="修改学生信息" placement="top" :enterable="false">
              <el-button type="primary" icon="el-icon-edit" size="mini"
                         @click="showEditDialog(scope.row.sno)"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="删除学生信息" placement="top" :enterable="false">
              <el-button type="danger" icon="el-icon-delete" size="mini"
                         @click="deleteStudentById(scope.row.sno)"></el-button>
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
        <el-form-item label="学号" prop="sno">
          <el-input v-model="addForm.sno" class="item"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="sname">
          <el-input v-model="addForm.sname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="ssex">
          <el-select v-model="addForm.ssex" placeholder="请选择" class="item">
            <el-option
                v-for="item in sexOptions"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="sbirthday">
          <el-date-picker
              v-model="addForm.sbirthday"
              type="date"
              :clearable="false"
              :editable="false"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              class="item">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="所属班级" prop="classno">
          <el-select v-model="addForm.classno" placeholder="请选择" class="item">
            <el-option
                v-for="item in classOptions"
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
                <el-button type="primary" @click="createStudent">确 定</el-button>
            </span>
    </el-dialog>

    <!--修改学生信息的对话框-->
    <el-dialog
        title="修改学生信息"
        :visible.sync="editDialogVisible"
        width="40%"
        @close="editDialogClosed">
      <!--内容主体区-->
      <el-form :model="editForm" :rules="editRules" ref="editFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="学号" prop="sno">
          <el-input v-model="editForm.sno" class="item" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="sname">
          <el-input v-model="editForm.sname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="ssex">
          <el-select v-model="editForm.ssex" placeholder="请选择" class="item">
            <el-option
                v-for="item in sexOptions"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生日期" prop="sbirthday">
          <el-date-picker
              v-model="editForm.sbirthday"
              type="date"
              :clearable="false"
              :editable="false"
              value-format="yyyy-MM-dd"
              placeholder="选择日期"
              class="item">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="所属班级" prop="classno">
          <el-select v-model="editForm.classno" placeholder="请选择" class="item">
            <el-option
                v-for="item in classOptions"
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
                <el-button type="primary" @click="modifyStudent()">确 定</el-button>
            </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Student",
  data() {
    // 校验学号是否存在
    const rulesName = (rule, value, callback) => {
      // 使用Axios进行校验
      this.$http.get('student/checkSno/' + value)
          .then((res => {
            // 请求成功
            if (res.data.code === 0) {
              callback()
            } else {
              callback(new Error("学号已存在"))
            }
          }))
          .catch((err) => {
            // 如果请求失败则在控制台打印
            console.log(err)
          })
    }
    return {
      // 学生信息规则
      addRules: {
        sno: [{
          required: true,
          message: '学号不能为空',
          trigger: 'blur'
        },
          {
            validator: rulesName,
            trigger: 'blur'
          },
          {
            type: 'string',
            min: 8,
            max: 8,
            message: '学号不符合规范',
            trigger: 'blur',
          }
        ],
        sname: [{
          required: true,
          message: '姓名不能为空',
          trigger: 'blur'
        }],
        ssex: [{
          required: true,
          message: '性别不能为空',
          trigger: 'blur'
        }],
        sbirthday: [{
          required: true,
          message: '出生日期不能为空',
          trigger: 'blur'
        }],
        classno: [{
          required: true,
          message: '所属班级不能为空'
        }]
      },
      editRules: {
        sno: [{
          required: true,
          message: '学号不能为空',
          trigger: 'blur'
        },
          {
            type: 'string',
            min: 8,
            max: 8,
            message: '学号不符合规范',
            trigger: 'blur',
          }
        ],
        sname: [{
          required: true,
          message: '姓名不能为空',
          trigger: 'blur'
        }],
        ssex: [{
          required: true,
          message: '性别不能为空',
          trigger: 'blur'
        }],
        sbirthday: [{
          required: true,
          message: '出生日期不能为空',
          trigger: 'blur'
        }],
        classno: [{
          required: true,
          message: '所属班级不能为空'
        }]
      },
      queryInfo: '',
      addDialogVisible: false,
      editDialogVisible: false,
      studentList: [],
      totalLength: 0,
      currentPage: 1,
      pageSize: 5,
      addForm: {
        sno: '',
        sname: '',
        ssex: '男',
        sbirthday: '2001-01-23',
        classno: '',
      },
      editForm: {
        sno: '',
        sname: '',
        ssex: '',
        sbirthday: '',
        classno: ''
      },
      sexOptions: ['男', '女'],
      classOptions: [],
      classnoFilters:[],
    }
  },
  mounted() {
    // this.getStudentListByPage()
    this.getStudentList()
    this.getClassnoList()
  },
  methods: {
    async queryStudentList() {
      const {data: res} = await this.$http.get('student/query/' + this.queryInfo)
      this.studentList = res.data
    },

    modifyStudent() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('student/modify', {
          sno: this.editForm.sno,
          sname: this.editForm.sname,
          ssex: this.editForm.ssex,
          sbirthday: this.editForm.sbirthday,
          classno: this.editForm.classno
        })
        this.$message.success('修改学生信息成功')
        // 隐藏添加用户的对话框
        this.editDialogVisible = false
        // 重新获取用户列表数据
        this.getStudentList()
      })
    },
    showEditDialog(sno) {
      this.editForm = this.studentList.filter(obj => obj.sno === sno)[0];
      this.editDialogVisible = true;
    },
    deleteStudentById(sno) {
      this.$confirm('此操作将删除该学生信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.$http.get('student/delete/' + sno);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getStudentList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    createStudent() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('student/create', {
          sno: this.addForm.sno,
          sname: this.addForm.sname,
          ssex: this.addForm.ssex,
          sbirthday: this.addForm.sbirthday,
          classno: this.addForm.classno
        })
        this.$message.success('添加学生信息成功')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 重新获取用户列表数据
        this.getStudentList()
      })
    },
    sizeChange(val) {
      this.pageSize = val
      this.getStudentListByPage();
    },

    currentChange(val) {
      this.currentPage = val;
      this.getStudentListByPage();
    },

    async getStudentListByPage() {
      const {data: res} = await this.$http.post('student/getStudentListByPage', {
        currentPage: this.currentPage,
        pageSize: this.pageSize
      });
      this.studentList = res.data.list;
      this.totalLength = res.data.length;
    },

    async getStudentList() {
      const {data: res} = await this.$http.get('student/getStudentList');
      this.studentList = res.data.list;
      this.totalLength = res.data.length;
    },

    async getClassnoList() {
      const {data: res} = await this.$http.get('class/getClassnoList');
      this.classOptions = res.data;
      for(let i in res.data)
        this.classnoFilters.push({text: res.data[i], value: res.data[i]});
    },

    // 监听添加学生对话框的关闭事件
    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },

    editDialogClosed() {
      this.$refs.editFormRef.resetFields()
    },

    filterSexHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },

    filterClassnoHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },

    queryChange() {
      if(this.queryInfo === '') {
        this.getStudentList()
      }
    }
  }
}
</script>

<style scoped>
  .item {
    width: 300px;
  }
</style>
