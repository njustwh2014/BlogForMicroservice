// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
// import VueStar from 'vue-star'//点赞组件
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/theme/index.css'

import '@/assets/icon/iconfont.css'
import '@/assets/myicon/iconfont.css'
import 'font-awesome/css/font-awesome.css'
// import 'element-ui/lib/theme-chalk/iconfont.css'
import router from './router'
import store  from './store'
import {formatTime} from '@/util/time'
Vue.config.productionTip = false
Vue.use(ElementUI)
// Vue.component('VueStar', VueStar)
/* eslint-disable no-new */

Vue.directive('title',  function (el, binding) {
  document.title = el.dataset.title
})

// 格式话时间
Vue.filter('format', formatTime)

new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  render: h => h(App)
})
