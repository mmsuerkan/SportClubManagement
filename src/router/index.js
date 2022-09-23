import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import ClubPanel from "@/views/club/ClubPanel";
import BranchPanel from "@/views/branch/BranchPanel";
import CoordinatorPanel from "@/views/coordinator/CoordinatorPanel";
import GroupPanel from "@/views/group/GroupPanel";
import StudentPanel from "@/views/student/StudentPanel";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/club',
    name: 'Club Page',
    component: ClubPanel
  },
  {
    path: '/club/:clubid/branches',
    name: 'Branches Page',
    component: BranchPanel
  },
  {
    path: '/club/:clubid/coordinators',
    name: 'Coordinators Page',
    component: CoordinatorPanel
  },
  {
    path: '/branches/:branchid/groups',
    name: 'Groups Page',
    component: GroupPanel
  },
  {
    path: '/groups/:groupid/students',
    name: 'Students Page',
    component: StudentPanel
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
