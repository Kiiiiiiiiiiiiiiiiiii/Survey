<template>
  {{ $t('successText') }}<br>
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
.bi-emoji-smile-fill {
    color: yellow;
}
</style>