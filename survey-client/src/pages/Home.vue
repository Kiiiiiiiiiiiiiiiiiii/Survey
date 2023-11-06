<template>
    <br>
    <div class="main-title-container">
        <div class="title-img">
            <img class="img" src="../../public/img/main-title-kr.png" v-if="state.form.branchId == 1">
            <img class="img" src="../../public/img/main-title-vn.png" v-if="state.form.branchId == 2">
        </div>
        <br>
        <br>
        <br>
        <br>
    </div>
    <div class="home-main-container">
        <div>
            <div class="grid-container" :style="fontStyleBold">
                <div class="inner-grid">
                    <span class="ptag" style="color: red">⦁</span> {{ $t('name') }} : <input
                        class="input-element"
                        v-model="state.form.name"
                        required
                        :style="fontStyle"/>
                </div>
                <div class="inner-grid">
                    <span class="ptag" style="color: red">⦁</span> {{ $t('company') }} : <input class="input-element"
                                                                                                v-model="state.form.company"
                                                                                                :style="fontStyle"/>
                </div>
                <div class="inner-grid">
                    <span class="ptag" style="color: red">⦁</span> {{ $t('email') }} : <input class="input-element"
                                                                                              :class="{'input-danger': emailHasError }"
                                                                                              v-model="state.form.email"
                                                                                              :style="fontStyle"/>
                </div>
                <div class="inner-grid">
                    <span class="ptag" style="color: red">⦁</span> {{ $t('phone') }} : <input class="input-element"
                                                                                              v-model="state.form.phone"
                                                                                              :style="fontStyle"/>
                </div>
                <div class="inner-grid">
                    <span class="ptag" style="color: red">⦁</span> {{ $t('industry') }} :
                    <select class="input-element" v-model="state.form.industry" :style="fontStyle">
                        <option>{{ $t('confectionary') }}</option>
                        <option>{{ $t('dessert') }}</option>
                        <option>{{ $t('hmr') }}</option>
                        <option>{{ $t('alternativeFood') }}</option>
                        <option>{{ $t('etc') }}</option>
                    </select>
                </div>
            </div>

            <div class="container-element-agree" :style="fontStyle">
                <p></p>
                <br>
                <span class="consent-text">{{ $t('consent') }}</span>
                <br>
                <br>
                <div class="flex-container">
                    <div class="flex-item-disagree">
                        <input type="radio" v-bind:value="true" v-model="selected">
                        <label for="one">{{ $t('agree') }}</label>
                    </div>
                    <span></span>
                    <div class="flex-item-agree">
                        <input type="radio" v-bind:value="false" v-model="selected">
                        <label for="one">{{ $t('disagree') }}</label>
                    </div>
                </div>
            </div>
            <br>
            <br>
            <div class="submit-buttons">
                <img src="../../public/img/disable_submit_btn.png" v-if="!selected" class="submit-button">
                <img src="../../public/img/submit_btn.png" v-if="selected" class="submit-button"
                     @click.prevent="submit">
            </div>
        </div>
        <div class="footer-logo">
            <Footer/>
        </div>
    </div>
</template>

<script>

import {reactive} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";
import Footer from "@/components/Footer.vue";

export default {
    components: {Footer},
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
            },
            showErrorMessage: false
        })

        const submit = () => {
            const missingFields = []

            if (state.form.name === "") {
                missingFields.push("Name")
            }
            if (state.form.company === "") {
                missingFields.push("Company")
            }
            if (state.form.email === "") {
                missingFields.push("Email")
            }
            if (state.form.phone === "") {
                missingFields.push("Mobile")
            }
            if (state.form.industry === "") {
                missingFields.push("Industry")
            }
            if (missingFields.length > 0) {
                window.alert("Please fill in the following fields: " + missingFields.join(", "))
            } else {
            axios.post("/api/submit", state.form).then((res) => {
                if (res.data) {
                    router.push({name: 'imageUpload', query: {branchId: state.form.branchId}, state: state.form});
                } else {
                    router.push({name: 'duplicate', query: {branchId: state.form.branchId}});
                }
            })
            }

        }
        return {state, submit}
    },
    data() {
        return {
            selected: false, //radio 버튼의 디폴트 값 설정
            true: true,
            false: false,
            emailHasError: false,
            valid: {
                email: false
            },
            nowlocale: 'en'
        }
    },
    methods: {
        checkemail() {
            const validateEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/
            if (!validateEmail.test(this.state.form.email) || !this.state.form.email) {
                this.valid.email = true
                this.emailHasError = true
                return
            }
            this.valid.email = false
            this.emailHasError = false
        }
    },
    computed: {
        fontStyleBold() {
            if (this.$i18n.locale === 'ko') {
                return {
                    fontFamily: 'NotoSansKR-Bold'
                }
            } else {
                return {
                    fontFamily: 'NotoSans-Bold'
                }
            }
        },
        fontStyle() {
            if (this.$i18n.locale === 'ko') {
                return {
                    fontFamily: 'NotoSansKR-Medium'
                }
            } else {
                return {
                    fontFamily: 'NotoSans-Medium'
                }
            }
        },
    },
    watch: {
        'state.form.email': function () {
            this.checkemail()
        }
    }
}
</script>

<style scoped>
@font-face {
    font-family: NotoSans-Bold;
    src: url("../../public/fonts/NotoSans-Bold.ttf");
}

@font-face {
    font-family: NotoSansKR-Bold;
    src: url("../../public/fonts/NotoSansKR-Bold.ttf");
}

.home-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100vw;
    height: auto;
    /*    아래 3개 추가로 이미지 하단중앙에 고정*/
    justify-content: center;
    align-items: flex-start; /* 세로 정렬은 위쪽에 맞춥니다. */
    display: flex;


}

.grid-container {
    display: grid;
    /* grid-template-columns: 2fr 2fr; !* 2x2 그리드 생성 *!*/
    grid-template-rows: 1fr 1fr; /* 2개의 행을 생성 */
    gap: 20px; /* 그리드 아이템 간의 간격 설정 */
    padding-left: 14vw;
    text-align: left;;
    place-items: self-start; /* 수직 및 수평 가운데 정렬 */
}

.inner-grid {
    display: grid;
    grid-template-columns: 1fr 6fr 12fr;
    text-align: left;
    align-items: center; /* 텍스트를 수직으로 가운데 정렬 */
    /*place-items: center; !* 수직 및 수평 가운데 정렬 *!*/
    /*grid-template-rows: 2fr 2fr 2fr;*/
}

.ptag {
    place-items: center;
    align-items: center; /* 텍스트를 수직으로 가운데 정렬 */
}

.input-element {
    width: 45vw;
    border: 1px solid #ccc; /* 테두리 스타일 및 두께 설정 */
}

.container-element-agree {
    justify-content: center;
    align-items: center;
    text-align: center;
    font-size: 2vh;
    white-space: pre-wrap; /*\n을 줄바꿈으로 치환*/
    width: 80%;
    margin: 0 auto;
}

.submit-buttons {
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

.submit-button {
    width: 40%;
    height: auto;
}

.footer-logo {
    width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
    justify-content: center;
    align-items: center;
    display: flex;
}

.flex-container {
    display: flex;
    justify-content: space-between; /* 두 div를 양쪽 끝에 정렬합니다. */
}

.flex-item-disagree {
    width: 50%; /* 각 div의 너비를 조절하여 간격을 만듭니다. */
    /*border: 1px solid #000; !* 각 div에 테두리 추가 (옵션) *!*/
}

.flex-item-agree {
    width: 50%; /* 각 div의 너비를 조절하여 간격을 만듭니다. */
    /*border: 1px solid #000; !* 각 div에 테두리 추가 (옵션) *!*/
}

.consent-text {
    font-size: 3vw;
    width: 50vw;
}

.main-title-container {
    height: 100%;
    display: flex;
    justify-content: center; /* 가로 가운데 정렬 */
    align-items: flex-start; /* 화면 상단에 정렬 */
}

.title-img {
    height: 100%;
    text-align: center; /* 이미지를 가운데 정렬 */
}

.img {
    width: 70%; /* 이미지를 부모 요소 너비에 맞춤 */
    height: 80%;
}
</style>
