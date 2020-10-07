<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>课程安排</el-breadcrumb-item>
    </el-breadcrumb>

    <!--卡片视图区域-->
    <el-card style="margin-top: 10px">

      <!--用户列表区域-->
      <el-table :data="teachingList" border strip :default-sort = "{prop: 'cno', order: 'ascending'}">
        <el-table-column label="课程号" prop="cno" sortable></el-table-column>
        <el-table-column label="课程名称" prop="cname"
                         :filters="courseOptions"
                         :filter-method="filterHandler"></el-table-column>
        <el-table-column label="教师编号" prop="tno" sortable></el-table-column>
        <el-table-column label="教师姓名" prop="tname"></el-table-column>
        <el-table-column label="授课语言" prop="language"
                         :filters="languageOptions"
                         :filter-method="filterHandler"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Teaching",
  data() {
    return {
      teachingList: [],
      languageOptions: [],
      courseOptions: []
    }
  },
  mounted() {
    this.getTeachingList()
    this.getTeachingLanguageList()
    this.getTeachingCourseList()
  },
  methods: {
    async getTeachingList() {
      const {data: res} = await this.$http.get('teaching/getTeachingList');
      this.teachingList = res.data.list;
    },

    async getTeachingLanguageList() {
      const {data: res} = await this.$http.get('teaching/getTeachingLanguageList');
      for(let i in res.data)
        this.languageOptions.push({text: res.data[i], value: res.data[i]})
    },

    async getTeachingCourseList() {
      const {data: res} = await this.$http.get('teaching/getTeachingCourseList');
      for(let i in res.data)
        this.courseOptions.push({text: res.data[i], value: res.data[i]})
    },

    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
  }
}
</script>

<style scoped>

</style>
