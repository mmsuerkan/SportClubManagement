import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

import ClubPanel from "@/views/club/ClubPanel";
import BranchPanel from "@/views/branch/BranchPanel";
import TrainerPanel from "@/views/trainer/TrainerPanel";
import GroupPanel from "@/views/group/GroupPanel";
import StudentPanel from "@/views/student/StudentPanel";
import Details from "@/views/student/Details"

import StoragePage from "@/components/StoragePage";

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
    path: '/clubs/:clubid/branches',
    name: 'Branches Page',
    component: BranchPanel
  },
  {
    path: '/clubs/:clubid/trainers',
    name: 'Coordinators Page',
    component: TrainerPanel
  },
  {
    path: '/branches/:branchKey/groups',
    name: 'Groups Page',
    component: GroupPanel
  },
  {
    path: '/groups/:groupKey/students',
    name: 'Students Page',
    component: StudentPanel
  },
  {
    path: '/storage',
    name: 'Storage',
    component: StoragePage
  },
  {
    path: '/deneme',
    name: 'Details Page',
    component: Details
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
