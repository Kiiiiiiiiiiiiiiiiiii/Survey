<template>
    <div class="login-container">
        <div class="background-image"></div>
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
.login-container{
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100vw;
    height: 100vh;
    flex-direction: column; /* 요소들을 위아래로 배치 */
    overflow: hidden; /* 이미지 넘침을 숨깁니다. */
}
body {
    margin: 0;
}
.background-image {
    position: absolute;
    width: 100%;
    height: 100%;
    display: flex;
    background-image: url('../../public/img/login_img.png'); /* 이미지 경로를 설정하세요 */
    background-size: contain; /* 이미지를 비율을 유지하면서 화면에 맞게 크기 조절 */
    background-repeat: no-repeat; /* 이미지 반복 없음 */
    background-position: center center; /* 이미지를 가운데 정렬 */
    z-index: -1; /* 입력 폼 위에 배경 이미지가 나타나도록 설정 */
    align-items: center;
    justify-content: center;
}
.input{
    margin: 1vh;
}
</style>