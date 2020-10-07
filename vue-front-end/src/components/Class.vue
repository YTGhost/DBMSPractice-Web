<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>班级管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加班级</el-button>
        </el-col>
      </el-row>

      <!--用户列表区域-->
      <el-table :data="classList" border strip :default-sort = "{prop: 'classno', order: 'ascending'}">
        <el-table-column label="班级编号" prop="classno" sortable></el-table-column>
        <el-table-column label="班级名称" prop="classname" sortable></el-table-column>
        <el-table-column label="所属专业" prop="classmajor"></el-table-column>
        <el-table-column label="所属院系" prop="classdept" sortable
                         :filters="[{text: '软件开发', value: '软件开发'}, {text: '数字媒体', value: '数字媒体'}]"
                         :filter-method="filterDeptHandler"></el-table-column>
        <el-table-column label="学生数目" prop="studentnumber" sortable></el-table-column>
<!--        <el-table-column label="操作" width="120px">-->
<!--          <template slot-scope="scope">-->
<!--&lt;!&ndash;            <el-tooltip effect="dark" content="修改班级信息" placement="top" :enterable="false">&ndash;&gt;-->
<!--&lt;!&ndash;              <el-button type="primary" icon="el-icon-edit" size="mini"&ndash;&gt;-->
<!--&lt;!&ndash;                         @click="showEditDialog(scope.row.classno)"></el-button>&ndash;&gt;-->
<!--&lt;!&ndash;            </el-tooltip>&ndash;&gt;-->
<!--            <el-tooltip effect="dark" content="删除班级信息" placement="top" :enterable="false">-->
<!--              <el-button type="danger" icon="el-icon-delete" size="mini"-->
<!--                         @click="deleteClassById(scope.row.classno)"></el-button>-->
<!--            </el-tooltip>-->
<!--          </template>-->
<!--        </el-table-column>-->
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

    <!--添加班级信息的对话框-->
    <el-dialog
        title="添加班级信息"
        :visible.sync="addDialogVisible"
        width="40%"
        @close="addDialogClosed">
      <!--内容主体区-->
      <el-form :model="addForm" :rules="addRules" ref="addFormRef" label-width="100px" class="demo-ruleForm">
        <el-form-item label="班级编号" prop="classno">
          <el-input v-model="addForm.classno" class="item"></el-input>
        </el-form-item>
        <el-form-item label="班级名称" prop="classname">
          <el-input v-model="addForm.classname" class="item"></el-input>
        </el-form-item>
        <el-form-item label="专业" prop="classmajor">
          <el-input v-model="addForm.classmajor" class="item" disabled></el-input>
        </el-form-item>
        <el-form-item label="专业院系" prop="classdept">
          <el-select v-model="addForm.classdept" placeholder="请选择" class="item">
            <el-option
                v-for="item in deptOptions"
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
                <el-button type="primary" @click="createClass">确 定</el-button>
            </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Class",
  data() {
    // 校验班级编号是否存在
    const classnoRules = (rule, value, callback) => {
      // 使用Axios进行校验
      this.$http.get('class/checkClassno/' + value)
          .then((res => {
            // 请求成功
            if (res.data.code === 0) {
              callback()
            } else {
              callback(new Error("班级编号已存在"))
            }
          }))
          .catch((err) => {
            // 如果请求失败则在控制台打印
            console.log(err)
          })
    }
    return {
      addRules: {
        classno: [{
          required: true,
          message: '班级编号不能为空',
          trigger: 'blur'
        },
          {
            validator: classnoRules,
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
        classname: [{
          required: true,
          message: '班级名称不能为空',
          trigger: 'blur'
        }],
        classmajor: [{
          required: true,
          message: '专业不能为空',
          trigger: 'blur'
        }],
        classdept: [{
          required: true,
          message: '专业院系不能为空',
          trigger: 'blur'
        }]
      },
      addDialogVisible: false,
      classList: [],
      totalLength: 0,
      currentPage: 1,
      pageSize: 5,
      addForm: {
        classno: '',
        classname: '',
        classmajor: '软件工程',
        classdept: '软件开发',
      },
      deptOptions: ['软件开发', '数字媒体'],
    }
  },
  mounted() {
    this.getClassList()
  },
  methods: {
    sizeChange(val) {
      this.pageSize = val
      this.getClassListByPage();
    },

    currentChange(val) {
      this.currentPage = val;
      this.getClassListByPage();
    },

    async getClassListByPage() {
      const {data: res} = await this.$http.post('class/getClassListByPage', {
        currentPage: this.currentPage,
        pageSize: this.pageSize
      });
      this.classList = res.data.list;
      this.totalLength = res.data.length;
    },

    async getClassList() {
      const {data: res} = await this.$http.get('class/getClassList');
      this.classList = res.data.list;
      this.totalLength = res.data.length;
    },

    addDialogClosed() {
      this.$refs.addFormRef.resetFields()
    },

    createClass() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) return
        // 可以发起添加用户的网络请求
        await this.$http.post('class/create', {
          classno: this.addForm.classno,
          classname: this.addForm.classname,
          classmajor: this.addForm.classmajor,
          classdept: this.addForm.classdept,
        })
        this.$message.success('添加班级信息成功')
        // 隐藏添加用户的对话框
        this.addDialogVisible = false
        // 重新获取用户列表数据
        this.getClassList()
      })
    },

    filterDeptHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },

    showEditDialog(classno) {
      this.editForm = this.classList.filter(obj => obj.classno === classno)[0];
      this.editDialogVisible = true;
    },

    deleteClassById(classno) {
      this.$confirm('此操作将删除该班级, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
        await this.$http.get('class/delete/' + classno);
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.getClassList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
  }
}
</script>

<style scoped>
.item {
  width: 300px;
}
</style>
