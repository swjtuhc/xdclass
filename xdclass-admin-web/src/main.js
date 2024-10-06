import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/css/global.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import 'element-ui/lib/theme-chalk/icon.css';
import VueRouter from "vue-router";
Vue.use(VueRouter)

Vue.config.productionTip = false

// 使用 ElementUI 并设置默认语言为中文
Vue.use(ElementUI, {
  locale,
  size: 'small' // 设置默认大小为 small
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
