<template>
    <div class="dupli-main-container">
        <div class="message">
            <img class="img" alt="img" src="../assets/fail.png">
            <p class="font" :style="fontStyle"> {{ $t('duplicateText') }}</p>
            <br>
            <br>
        </div>
        <div class="footer-logo">
            <Footer/>
        </div>
    </div>
</template>

<script>
import Footer from "@/components/Footer.vue";


export default {
    name: "Duplicate",
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

.dupli-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
    height: 100%; /*% 말고 vh로 해야 화면이 꽉찬다.*/
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
    width: 60%;
}

.footer-logo {
    width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
}

.font {
    color: red;
}
</style>