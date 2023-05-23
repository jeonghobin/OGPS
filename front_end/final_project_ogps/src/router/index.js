import Vue from 'vue'
import VueRouter from 'vue-router'
import AppMain from '@/views/AppMain'
import store from "@/store";
import AppAttraction from '@/views/AppAttraction'
import AppGroup from '@/views/AppGroup'
import GroupList from '@/components/group/GroupList'
import GroupWrite from '@/components/group/GroupWrite'
import AppReview from '@/views/AppReview'
import ReviewList from '@/components/review/ReviewList'
import ReviewWrite from '@/components/review/ReviewWrite'
import ReviewView from '@/components/review/ReviewView'
import ReviewUpdate from '@/components/review/ReviewUpdate'
import GroupView from '@/components/group/GroupView'
import GroupModify from '@/components/group/GroupModify'
import GroupPlanView from '@/components/group/GroupPlanView'
import GroupPlanWrite from '@/components/group/GroupPlanWrite'
import AppWeather from '@/views/AppWeather'
import GroupPlanModify from '@/components/group/GroupPlanModify'

Vue.use(VueRouter)

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path:"/",
    name:"AppMain",
    component: AppMain
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
      {
        path: "modify",
        name: "modify",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserModify"),
      },
    ],
  },
  {
    path:"/attraction",
    name:"attraction",
    component: AppAttraction,
  },
  {
    path:"/review",
    name:"review",
    component: AppReview,
    redirect:"/review/list",
    children:[
      {
        path:"list",
        name:"reviewlist",
        component: ReviewList
      },
      {
        path:"write",
        name:"reviewwrite",
        component: ReviewWrite
      },
      {
        path:"view/:articleNo",
        name:"reviewview",
        component: ReviewView
      },
      {
        path:"update/:articleNo",
        name:"reviewupdate",
        component: ReviewUpdate
      },
    ]
  },
  {
    path:"/group",
    name:"group",
    component: AppGroup,
    redirect:"/group/list",
    children:[
      {
        path:"list",
        name:"grouplist",
        component: GroupList
      },
      {
        path:"write",
        name:"groupwrite",
        component: GroupWrite
      },
      {
        path:"view/:groupNo",
        name:"groupview",
        component: GroupView
      },
      {
        path:"modify/:groupNo",
        name:"groupmodify",
        component: GroupModify
      },
      {
        path:"planview/:groupNo?/:planNo?",
        name:"groupplanview",
        component: GroupPlanView
      },
      {
        path:"planwrite/:groupNo",
        name:"groupplanwrite",
        component:GroupPlanWrite
      },
      {
        path:"planmodify/:groupNo?/:planNo?",
        name:"groupplanmodify",
        component:GroupPlanModify
      }
    ]
  },
  {
    path:"/weather",
    name:"weather",
    component:AppWeather
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
