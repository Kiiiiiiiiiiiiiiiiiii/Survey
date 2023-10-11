<template>
  {{ $t('duplicateText') }}<br>
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
</style>