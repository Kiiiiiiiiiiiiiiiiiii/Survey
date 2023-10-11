<template>
    <div class="message">
        {{ $t('duplicateText') }}<br>
        <img class="img" alt="img" src="../assets/fail.png">
    </div>

    <Logo/>

</template>

<script>
import Logo from "@/components/Logo.vue";

export default {
    name: "Duplicate",
    components: {Logo}
}
</script>

<style scoped>
.message {
    text-align: center;
}
</style>