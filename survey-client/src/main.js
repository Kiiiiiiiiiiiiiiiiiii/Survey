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


const routes = [
    {
        path: '/', redirect: 'main?branchId=1'
    },
    {
        path: '/kr', name: 'FrontImageKR', component: FrontImageKR
        /*path: '/kr', redirect: 'main?branchId=1'*/
    },
    {
        path: '/vn', name: 'FrontImageVN', component: FrontImageVN
        /*path: '/vn', redirect: 'main?branchId=2'*/
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
        path: '/result', name: 'result', component: Result
    },
    {
        path: '/:catchAll(.*)', redirect: 'main'
    }
]

const router = createRouter(({
    history: createWebHistory(),
    routes
}))

createApp(App).use(router).use(i18n).mount('#app')
