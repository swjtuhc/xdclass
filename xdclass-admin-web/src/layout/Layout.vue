<template>
  <div>
    <!-- 头部 -->
    <Header />
    <!-- 主体 -->
    <div style="display: flex">
      <!-- 侧边栏 -->
      <Aside />
      <!-- 内容 -->
      <HomeView />
    </div>
  </div>
</template>

<script>
import Header from "@/components/Header.vue";
import Aside from "@/components/Aside.vue";
import HomeView from "@/views/HomeView.vue";

export default {
  name: "layoutComponent",
  components: {
    Header,
    Aside,
    HomeView
  }
};
</script>

<style scoped>
/* 你可以在这里添加样式 */
</style>
