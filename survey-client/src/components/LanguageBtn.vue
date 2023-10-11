<template>
    <div class="locale">
        <span @click="$i18n.locale='en'">EN/</span>
        <span @click="$i18n.locale='vn'">VN/</span>
        <span @click="$i18n.locale='ko'">KR</span>
    </div>
</template>
<script>

</script>

<style scoped>
.locale{
    text-align: right;
}
</style>