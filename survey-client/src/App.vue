<template>
    <LanguageBtn/>
    <RouterView/>
</template>

<script>



import LanguageBtn from "@/components/LanguageBtn.vue";
export default {
    name: "App",
    components:{LanguageBtn}
}
</script>

<style scoped>
</style>
