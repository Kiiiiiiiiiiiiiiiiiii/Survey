<template>
    <div class="dupli-main-container">
        <div class="message">
            <img class="img" alt="img" src="../../public/img/fail.png">
            <p class="bold-text" :style="fontStyleBold">{{ $t('sry') }}</p>
            <p class="conetent-text" :style="fontStyle"> {{ $t('duplicateText') }}</p>
            <br>
            <br>
        </div>
    </div>
    <div class="footer-logo">
        <Footer/>
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
    src: url("../../public/fonts/NotoSans-Medium.ttf");
}

.dupli-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
    height: auto; /*% 말고 vh로 해야 화면이 꽉찬다.*/
    max-height: 100%;
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
    width: 60%;
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