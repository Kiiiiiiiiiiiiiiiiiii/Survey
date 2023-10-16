<template>
    <div class="app-main-container">
        <LanguageBtn/>
        <RouterView/>
    </div>
</template>

<script>


import LanguageBtn from "@/components/LanguageBtn.vue";

export default {
    name: "App",
    components: {LanguageBtn}
}
</script>

<style scoped>
.app-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
}

</style>
