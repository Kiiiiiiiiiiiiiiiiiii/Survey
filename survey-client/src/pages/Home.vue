<template>
    <div class="home-main-container">
        <Logo/>
        <div class="grid-container">
            {{ $t('name') }} : <input class="input-element" v-model="state.form.name"/>
            {{ $t('company') }} : <input class="input-element" v-model="state.form.company"/>
            {{ $t('email') }} : <input class="input-element" :class="{'input-danger': emailHasError }"
                                       v-model="state.form.email"/>
            <!--            <p v-show="valid.email" class="input-error"> 이메일 오류 </p>-->
            {{ $t('phone') }} : <input class="input-element" :class="{'input-danger': emailHasError }"
                                       v-model="state.form.phone"/>
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
                $t('next')
                }}
            </button>
            <!--            <button v-if="selected" class="btn btn-primary w-300 py-2" style="background-color: red" @click="submit()">-->
            <button v-if="selected" class="btn btn-primary w-300 py-2" style="background-color: red"
                    @click="submit">
                {{ $t('next') }}
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
                phone: "",
                industry: "",
                branchId: router.currentRoute.value.query.branchId
            }
        })

        const submit = () => {
            axios.post("/api/submit", state.form).then((res) => {
                if (res.data) {
                    /*const Params = { state: JSON.stringify(state.form)}
                    console.log('params : ', Params)*/
                    /*router.push({name: 'success'});*/
                    router.push({name: 'imageUpload', state: state.form});
                } else {
                    router.push({name: 'duplicate'});
                }
            })
        }
        return {state, submit}
    },
    data() {
        return {
            modalActive: false,
            selected: false, //radio 버튼의 디폴트 값 설정
            true: true,
            false: false,
            emailHasError: false,
            valid: {
                email: false
            }

        }
    },
    methods: {
        checkemail() {
            console.log('checkEmail start')
            const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/
            console.log('checkEmail 1')
            if (!validateEmail.test(this.email) || !this.email) {
                console.log('checkEmail 2')
                this.valid.email = true
                this.emailHasError = true
                return
            }
            console.log('checkEmail 3')
            this.valid.email = false
            this.emailHasError = false
        }
    },
    watch: {
        'state.form.email': function () {
            console.log('watch start')
            this.checkemail()
            console.log('watch end')
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

.grid-container {
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

.input-danger {
    border-bottom: 1px solid $color-error !important;
}

.input-error {
    line-height: 16px;
    font-size: 11px;
    color: $color-error;
}

.black-bg {
    display: flex;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.432);
    position: fixed;
    padding: 20px;
}

.white-bg {
    width: 100%;
    background-color: white;
    padding: 20px;
    border-radius: 8px;
}

.modal-exit-btn {
    margin-top: 30px;
}

.modal-exit-btn:hover {
    cursor: pointer;
}
</style>
