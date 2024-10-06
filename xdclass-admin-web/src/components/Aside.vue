<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: calc(100vh - 60px); width: 200px"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ff0d4b"
           :router="true"
           :collapse-transition="false"
           :collapse="isCollapse"
           default-active="user"
  >
    <!--        设置文本选中高亮为黄色-->
    <div style="height: 70px; line-height: 60px;text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 100px; height: 70px; position: relative;top: 5px;margin-right: 5px">
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>
    <el-menu-item index="/Home">
      <i class="el-icon-house"></i>
      <span slot="title">主页</span>
    </el-menu-item>
    <!--    在index里面写上跳转的地方-->
    <el-submenu index="3">
      <template slot="title"><i class="el-icon-user"></i>
        <span slot="title">页面信息管理</span>
      </template>
      <el-menu-item-group>
        <template slot="title">页面文章</template>
        <el-menu-item index="Home">文章管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "AsideComponent",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean,
  },
}
</script>

<style scoped>

</style>