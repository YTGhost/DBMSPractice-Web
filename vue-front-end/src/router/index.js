import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login'
import Home from '../components/Home'
import Welcome from "@/components/Welcome";
import Student from "@/components/Student";
import Teacher from "@/components/Teacher";
import Course from "@/components/Course";
import Class from "@/components/Class";
import Sc from "@/components/Sc";
import Teaching from "@/components/Teaching";

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        {path: '/', redirect: '/login'},
        {path: '/login', component: Login},
        {
            path: '/home',
            component: Home,
            redirect: '/welcome',
            children: [
                {path: '/welcome', component: Welcome},
                {path: '/student', component: Student},
                {path: '/teacher', component: Teacher},
                {path: '/course', component: Course},
                {path: '/class', component: Class},
                {path: '/sc', component: Sc},
                {path: '/teaching', component: Teaching}
            ]
        }
    ]
})

export default router
