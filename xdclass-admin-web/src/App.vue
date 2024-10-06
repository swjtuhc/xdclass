<template>
  <div>
    <router-view/>

  </div>
  <!--实际上就是引用在其他界面写好的东西组件，然后放在这里，组件化开发-->
</template>
<style>


</style>
<script>


export default {
  name: "App",
  methods: {
    checkLogin() {
      let tokenValue = localStorage.getItem("token-value");
      let userInfoValue = localStorage.getItem("user-info");
      if (tokenValue && userInfoValue) {
        this.$store.commit("login", JSON.parse(userInfoValue));
        console.log("login success");
      }
    },
  },
  created() {
    this.checkLogin();
  },

}
</script>
