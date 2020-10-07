<template>
  <div id="app">
    <el-container>
      <el-container>
        <el-aside width="200px">
          <el-menu default-active="$route.path"
                   class="el-menu-vertical-demo"
                   background-color="#364152"
                   text-color="#fff">
            <el-menu-item index="/student" @click="toStudent">
              <i class="el-icon-user"></i>
              <span slot="title">学生管理</span>
            </el-menu-item>
            <el-menu-item index="/teacher" @click="toTeacher">
              <i class="el-icon-s-custom"></i>
              <span slot="title">教师管理</span>
            </el-menu-item>
            <el-menu-item index="/course" @click="toCourse">
              <i class="el-icon-notebook-1"></i>
              <span slot="title">课程管理</span>
            </el-menu-item>
            <el-menu-item index="/class" @click="toClass">
              <i class="el-icon-s-grid"></i>
              <span slot="title">班级管理</span>
            </el-menu-item>
            <el-menu-item index="/sc" @click="toSc">
              <i class="el-icon-s-order"></i>
              <span slot="title">选课查询</span>
            </el-menu-item>
            <el-menu-item index="/teaching" @click="toTeaching">
              <i class="el-icon-s-release"></i>
              <span slot="title">课程安排</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {

    }
  },
  methods: {
    toStudent() {
      if(this.$route.path !== '/student') {
        this.$router.push('/student')
      }
    },
    toTeacher() {
      if(this.$route.path !== '/teacher') {
        this.$router.push('/teacher')
      }
    },
    toCourse() {
      if(this.$route.path !== '/course') {
        this.$router.push('/course')
      }
    },
    toClass() {
      if(this.$route.path !== '/class') {
        this.$router.push('/class')
      }
    },
    toSc() {
      if(this.$route.path !== '/sc') {
        this.$router.push('/sc')
      }
    },
    toTeaching() {
      if(this.$route.path !== '/teaching') {
        this.$router.push('/teaching')
      }
    }
  }
}
</script>

<style>

html, body, #app, .el-container {
  margin: 0px;
  padding: 0px;
  height: 100%;
}

#map {
  width: 100%;
  height: 600px;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: left;
  line-height: 80px;
  font-size: 32px;
  font-weight: bold;
}

.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 30px;
}

.el-aside {
  background-color: #334154;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-menu{
  border-right-width: 0;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  /* text-align: center; */
  /* line-height: 160px; */
}

</style>
