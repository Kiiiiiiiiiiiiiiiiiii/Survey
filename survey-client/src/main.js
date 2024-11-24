import {createApp} from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/pages/Home.vue";
import Success from "@/pages/Success.vue";
import Duplicate from "@/pages/Duplicate.vue";
import {i18n} from "@/i18n/i18n";
import Result from "@/pages/Result.vue";
import imageUpload from "@/pages/ImageUpload.vue";
import FrontImageKR from "@/pages/FrontImageKR.vue";
import FrontImageVN from "@/pages/FrontImageVN.vue";
import Login from "@/pages/Login.vue";
import axios from "axios";


const routes = [
    {
        path: '/', redirect: 'main?branchId=1'
    },
    {
        path: '/kr', name: 'FrontImageKR', component: FrontImageKR
    },
    {
        path: '/vn', name: 'FrontImageVN', component: FrontImageVN
    },
    {
        path: '/main', name: 'Home', component: Home
    },
    {
        path: '/imageUpload', name: 'imageUpload', component: imageUpload
    },
    {
        path: '/success', name: 'success', component: Success
    },
    {
        path: '/duplicate', name: 'duplicate', component: Duplicate
    },
    {
        path: '/login', name: 'Login', component: Login
    },
    {
        path: '/result', name: 'result', component: Result
    },
    {
        path: '/:catchAll(.*)', redirect: 'main'
    }
]
 axios.defaults.baseURL = "http://35.216.123.244:8080"
/*axios.defaults.baseURL = "http://34.22.74.137:8080/survey-api"*/
//axios.defaults.baseURL = "http://localhost:8080"
const router = createRouter(({
    history: createWebHistory(process.env.BASE_URL),
    routes
}))

createApp(App).use(router).use(i18n).mount('#app')
