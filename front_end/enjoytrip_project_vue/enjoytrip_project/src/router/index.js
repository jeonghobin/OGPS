import Vue from "vue";
import VueRouter from "vue-router";

// views
import AppMain from "@/views/AppMain.vue";
import AppUser from "@/views/AppUser.vue";
import AppBoard from "@/views/AppBoard.vue";
import AppAttraction from "@/views/AppAttraction.vue"

// components
import BoardList from "@/components/board/BoardList";
import BoardWrite from "@/components/board/BoardWrite";
import BoardView from "@/components/board/BoardView";
import BoardModify from "@/components/board/BoardModify";

import FreeBoardList from "@/components/freeboard/BoardList";
import FreeBoardWrite from "@/components/freeboard/BoardWrite";
import FreeBoardView from "@/components/freeboard/BoardView";
import FreeBoardModify from "@/components/freeboard/BoardModify";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/user/login",
    name: "login",
    component: AppUser,
  },
  {
    path: "/attraction",
    name: "attraction",
    component: AppAttraction,
  },
  {
    path: "/board",
    name: "board",
    component: AppBoard,
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: BoardList,
      },
      {
        path: "write",
        name: "boardwrite",
        component: BoardWrite,
      },
      {
        path: "view/:articleno",
        name: "boardview",
        component: BoardView,
      },
      {
        path: "modify",
        name: "boardmodify",
        component: BoardModify,
      },
    ],
    },
    {
      path: "/freeboard",
      name: "freeboard",
      component: AppBoard,
      redirect: "/freeboard/list",
      children: [
        {
          path: "list",
          name: "freeboardlist",
          component: FreeBoardList,
        },
        {
          path: "write",
          name: "freeboardwrite",
          component: FreeBoardWrite,
        },
        {
          path: "view/:articleno",
          name: "freeboardview",
          component: FreeBoardView,
        },
        {
          path: "modify",
          name: "freeboardmodify",
          component: FreeBoardModify,
        },
      ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
