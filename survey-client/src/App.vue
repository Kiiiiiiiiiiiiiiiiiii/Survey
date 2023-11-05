<template>
    <div class="app-main-container">
        <LanguageBtn v-if="shouldShowLangBtn"/>
        <RouterView/>
    </div>
</template>

<script>


import LanguageBtn from "@/components/LanguageBtn.vue";

export default {
    name: "App",
    components: {LanguageBtn},
    computed: {
        shouldShowLangBtn() {
            if (this.$route.path == '/kr') {
                return this.$route.path !== '/kr';
            } else if (this.$route.path == '/vn') {
                return this.$route.path !== '/vn';
            } else if (this.$route.path == '/result') {
                return this.$route.path !== '/result'
            } else if (this.$route.path == '/login') {
                return this.$route.path !== '/login'
            }
            return true
        }
    }
}
</script>

<style scoped>
.app-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
}

</style>
