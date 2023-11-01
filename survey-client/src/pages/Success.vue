<template>
    <div class="success-main-container">
        <div class="message">
            <img class="img" alt="img" src="../assets/success.png">
            <br>
            <br>
            <span class="font" :style="fontStyle">{{ $t('successText') }}</span>
            <p class="bold-text" :style="fontStyleBold">{{ $t('thx') }}</p>
        </div>
        <div class="footer-logo">
            <Footer/>
        </div>
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
    src: url("../assets/fonts/NotoSans-Medium.ttf");
}

@font-face {
    font-family: NotoSans-Bold;
    src: url("../assets/fonts/NotoSans-Bold.ttf");
}

.success-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
    height: 100%; /*% 말고 vh로 해야 화면이 꽉찬다. */
    max-height: 100%;
    /*    아래 3개 추가로 이미지 하단중앙에 고정*/
    justify-content: center;
    align-items: center;
    display: flex;
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

.footer-logo {
    width: 60vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
}

.font {
    color: red;
}

.bold-text {
    color: red;
    font-weight: bold;
}
</style>