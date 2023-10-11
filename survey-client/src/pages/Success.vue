<template>
    <div class="message">
        {{ $t('successText') }}<br>
        <img class="img" alt="img" src="../assets/success.png">
    </div>
    <Logo/>
</template>

<script>
import Logo from "@/components/Logo.vue";

export default {
    name: "Success",
    components: {Logo}
}
</script>

<style scoped>
.message {
    text-align: center;
}
</style>