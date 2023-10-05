import {createApp} from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/pages/Home.vue";
import Success from "@/pages/Success.vue";
import Duplicate from "@/pages/Duplicate.vue";

const routes = [
    {
        path: '/', component: Home
    },
    {
        path: '/success', component: Success
    },
    {
        path: '/duplicate', component: Duplicate
    }
]

const router = createRouter(({
    history: createWebHistory(),
    routes
}))

createApp(App).use(router).mount('#app')
