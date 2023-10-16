<template>
    <div class="home-main-container">
        <Logo/>
        <div class="grid-container">
                {{ $t('name') }} :  <input class="input-element" v-model="state.form.name"/>
                {{ $t('company') }} :  <input class="input-element" v-model="state.form.company"/>
                {{ $t('email') }} :  <input class="input-element" v-model="state.form.email"/>
                {{ $t('industry') }} :
                <select class="input-element" v-model="state.form.industry">
                    <option>{{ $t('confectionary') }}</option>
                    <option>{{ $t('dessert') }}</option>
                    <option>{{ $t('hmr') }}</option>
                    <option>{{ $t('alternativeFood') }}</option>
                    <option>{{ $t('etc') }}</option>
                </select>
        </div>

        <div class="container-element-agree">
            <p></p>
            {{ $t('consent') }}
            <br>
            <br>
            <input type="radio" v-bind:value="true" v-model="selected">
            <label for="one">{{ $t('agree') }}</label>
            <span></span>
            <input type="radio" v-bind:value="false" v-model="selected">
            <label for="one">{{ $t('disagree') }}</label>
        </div>
        <br>
        <br>
        <div class="submit-butten">
            <button v-if="!selected" class="btn btn-primary w-300 py-2" style="background-color: gray">{{
                    $t('submit')
                }}
            </button>
            <button v-if="selected" class="btn btn-primary w-300 py-2" style="background-color: red" @click="submit()">
                {{ $t('submit') }}
            </button>
        </div>
    </div>
</template>

<script>

import {reactive} from "vue";
import axios from "axios";
import Logo from "@/components/Logo.vue";
import {useRouter} from "vue-router";

export default {
    components: {Logo},
    setup() {
        const router = useRouter()

        const state = reactive({
            form: {
                name: "",
                company: "",
                email: "",
                industry: ""
            }
        })

        const submit = () => {
            axios.post("/api/submit", state.form).then((res) => {
                console.log(res.data)
                if (res.data) {
                    router.push({name: 'success'});
                } else {
                    router.push({name: 'duplicate'});
                }
            })
        }
        return {state, submit}
    },
    data() {
        return {
            selected: false, //radio 버튼의 디폴트 값 설정
            true: true,
            false: false

        }
    }
}
</script>

<style scoped>
.home-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    height: 100%;
    max-height: 100%;
}

.grid-container{
    display: grid;
    grid-template-columns: 1fr 1fr; /* 2x2 그리드 생성 */
    gap: 10px; /* 그리드 아이템 간의 간격 설정 */
    text-align: center;
}

.input-element {
    max-width: 80%;
}

.container-element-agree {
    justify-content: center;
    align-items: center;
    text-align: center;
    font-size: 2vh;
    white-space: pre-wrap; /*\n을 줄바꿈으로 치환*/
}

.submit-butten {
    justify-content: center;
    align-items: center;
    text-align: center;
}
</style>
