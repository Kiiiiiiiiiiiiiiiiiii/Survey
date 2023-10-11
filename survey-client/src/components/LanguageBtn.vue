<template>
    <div class="locale">
        <span @click="$i18n.locale='en'"><b>EN | </b></span>
        <span @click="$i18n.locale='vn'"><b>VN | </b></span>
        <span @click="$i18n.locale='ko'"><b>KR</b></span>
    </div>
</template>
<script>

</script>

<style scoped>
.locale{
    text-align: right;
}
</style>