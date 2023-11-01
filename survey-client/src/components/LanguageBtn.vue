<template>
    <div class="locale">
        <span class="langStyle" v-show="showLangBtn" @click="changeLocale('ko')" :style="{ color: nowlocale === 'ko' ? 'red' : 'black', 'font-weight': nowlocale === 'ko' ? 'bold' : 'normal' }"> KR </span>
        <span class="langStyle" v-show="!showLangBtn" @click="changeLocale('vn')" :style="{ color: nowlocale === 'vn' ? 'red' : 'black', 'font-weight': nowlocale === 'vn' ? 'bold' : 'normal' }">VN </span>
        <span class="langStyle" @click="changeLocale('en')" :style="{ color: nowlocale === 'en' ? 'red' : 'black', 'font-weight': nowlocale === 'en' ? 'bold' : 'normal' }"> EN</span>
    </div>
</template>
<script>
export default {
    data(){
        return {
            korean: true,
            nowlocale: 'en'
        }
    },
    methods: {
      changeLocale(locale) {
          this.$i18n.locale = locale
          this.nowlocale = locale
      }
    },
    computed: {
        showLangBtn() {
            if (this.$route.query.branchId == 1) {
                return true
            } else {
                return false
            }
        }
    }
}
</script>

<style scoped>
.locale{
    text-align: right;
    font-size: 4vw; /*화면 폭의 5%크기로 버튼 텍스트 조절*/
    padding: 2% 5%; /*//화면 폭 2%높이 및 5%너비로 버튼 조절*/
}
.langStyle{
    font-weight: bold;
}
</style>