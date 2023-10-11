import {createApp} from 'vue'
import App from './App.vue'
import {createRouter, createWebHistory} from 'vue-router'
import Home from "@/pages/Home.vue";
import Success from "@/pages/Success.vue";
import Duplicate from "@/pages/Duplicate.vue";
import {i18n} from "@/i18n/i18n";

const routes = [
    {
        path: '/', name: 'Home', component: Home
    },
    {
        path: '/success', name: 'success', component: Success
    },
    {
        path: '/duplicate', name: 'duplicate', component: Duplicate
    }
]

const router = createRouter(({
    history: createWebHistory(),
    routes
}))

createApp(App).use(router).use(i18n).mount('#app')
