<template>
    <div class="success-main-container">
        <div class="message">
            <div>
            <img class="img" alt="img" src="../../public/img/success.png">
            </div>
            <div>
            <span class="conetent-text" :style="fontStyle">{{ $t('successText') }}</span>
            </div>
            <br>
            <p class="bold-text" :style="fontStyleBold">{{ $t('thx') }}</p>
        </div>
    </div>
    <div class="footer-logo">
        <Footer/>
    </div>
</template>

<script>
import Footer from "@/components/Footer.vue";

export default {
    name: "Success",
    components: {Footer},
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
        }
    }
}
</script>

<style scoped>
@font-face {
    font-family: NotoSans-Medium;
    src: url("../../public/fonts/NotoSans-Medium.ttf");
}

@font-face {
    font-family: NotoSans-Bold;
    src: url("../../public/fonts/NotoSans-Bold.ttf");
}

.success-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
    height: auto; /*% 말고 vh로 해야 화면이 꽉찬다. */
    max-height: 100%;
    /*    아래 3개 추가로 이미지 하단중앙에 고정*/
    justify-content: center;
    align-items: flex-start;
    display: flex;
    margin-top: 15%;
}

.message {
    text-align: center;
    align-items: center;
    justify-content: center;
    height: auto;
    font-size: 5vw;
    max-width: 100%;
    white-space: pre-wrap; /*\n을 줄바꿈으로 치환*/
}

.img {
    width: 55%;
}

/*.footer-logo {
    width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
}*/
.footer-logo {
    /*width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
    justify-content: center;
    align-items: center;
    display: flex;*/
    width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    justify-content: center;
    align-items: center;
}

.conetent-text {
    color: red;
    font-size: 4vw;
}

.bold-text {
    color: red;
    font-weight: bold;
}
</style>