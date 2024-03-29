/*import Home from "@/components/Header.vue";*/
import Home from "@/pages/Home.vue";
import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path: '/main', component: Home} //루트경로로 들어올때는 컴포넌트를 홈으로 연결
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;