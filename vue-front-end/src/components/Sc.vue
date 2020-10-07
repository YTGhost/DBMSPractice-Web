<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>选课管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">

      <!--用户列表区域-->
      <el-table :data="scList" border strip :default-sort = "{prop: 'sno', order: 'ascending'}">
        <el-table-column label="学号" prop="sno" sortable></el-table-column>
        <el-table-column label="学生姓名" prop="sname"></el-table-column>
        <el-table-column label="课程号" prop="cno" sortable></el-table-column>
        <el-table-column label="课程名称" prop="cname"></el-table-column>
        <el-table-column label="成绩" prop="grade" sortable></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Sc",
  data() {
    return {
      scList: [],
    }
  },
  mounted() {
    this.getClassList()
  },
  methods: {
    async getClassList() {
      const {data: res} = await this.$http.get('sc/getStudentScList');
      this.scList = res.data.list;
    },
  }
}
</script>

<style scoped>

</style>
