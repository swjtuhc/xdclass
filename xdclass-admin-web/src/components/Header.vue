<script>
export default {
  name: "HeaderComponent",
  methods: {
    logout() {
      this.$store.commit("logout");
      this.$router.push("/");
    },
  },
};
</script>

<template>
  <div
    style="
      height: 50px;
      line-height: 50px;
      border-bottom: 1px solid #ccc;
      display: flex;
      justify-content: flex-end;
      width: 100%;
    ">
    <div
      style="
        flex: 200px;
        padding-left: 30px;
        font-weight: bold;
        color: dodgerblue;
      ">
      后台管理
    </div>
    <div style="flex: 1"></div>
    <div style="flex: 100px; text-align: right; padding-right: 20px">
      <el-dropdown>
        <span class="el-dropdown-link">
          管理员<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <!-- <el-dropdown-item>系统管理</el-dropdown-item> -->
          <!-- <el-dropdown-item>各种管理</el-dropdown-item> -->
          <el-dropdown-item>
            <span @click="logout"> 退出系统 </span>
          </el-dropdown-item>
          <!--          native 在这段代码中的意思是 点击事件用来 跳转到 login 页面-->
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
