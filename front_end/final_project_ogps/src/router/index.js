import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from '@/views/AppMain'

Vue.use(VueRouter)

const routes = [
  {
    path:"/",
    name:"AppMain",
    component: AppMain
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
