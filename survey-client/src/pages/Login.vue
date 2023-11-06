<template>
    <div class="main-container">
        <div class="input">
            <input v-model="state.form.name" placeholder="name">
        </div>
        <div class="input">
            <input type="password" v-model="state.form.email" placeholder="password" >
        </div>
        <div>
            <button @click="login">login</button>
        </div>
    </div>

</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";

export default {
    name: "Login",
    setup() {
        const router = useRouter()
        const state = reactive({
            form: {
                name: "",
                email: ""
            }
        })

        const login = () => {
            axios.post("/api/login", state.form).then((res) => {
                if (res.data) {
                    router.push({name: 'result'})
                } else {
                    console.log('login fail')
                }
            })
        }
        return {state, login}
    }
}
</script>
<style scoped>
.main-container{
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    flex-direction: column; /* 요소들을 위아래로 배치 */
}
.input{
    margin: 1vh;
}
</style>