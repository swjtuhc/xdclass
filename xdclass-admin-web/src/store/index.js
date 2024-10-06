import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogin: false,
    userInfo: {}
  },
  getters: {
  },
  mutations: {
    login(state, userInfo) {
      state.userInfo = userInfo
    },
    logout(state) {
      state.isLogin = false
      state.userInfo = {}
      localStorage.clear()
    }
  },
  actions: {
  },
  modules: {
  }
})
