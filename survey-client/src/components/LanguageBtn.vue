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
    font-size: 5vw; /*화면 폭의 5%크기로 버튼 텍스트 조절*/
    padding: 2% 5%; /*//화면 폭 2%높이 및 5%너비로 버튼 조절*/
}
</style>