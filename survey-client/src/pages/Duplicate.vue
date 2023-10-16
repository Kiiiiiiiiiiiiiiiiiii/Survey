<template>
    <div class="dupli-main-container">
        <div class="message">
            <img class="img" alt="img" src="../assets/fail.png">
            <br>
            {{ $t('duplicateText')}}
        </div>
        <div class="logo">
            <Logo/>
        </div>
    </div>

</template>

<script>
import Logo from "@/components/Logo.vue";

export default {
    name: "Duplicate",
    components: {Logo}
}
</script>

<style scoped>
.dupli-main-container {
    overflow: hidden; /* 스크롤을 숨깁니다. */
    position: fixed;
    width: 100%;
    height: 100%; /*% 말고 vh로 해야 화면이 꽉찬다.*/
    max-height: 100%;
/*    아래 3개 추가로 이미지 하단중앙에 고정*/
    justify-content: center;
    align-items: center;
    display: flex;
}

.message {
    text-align: center;
    align-items: center;
    justify-content: center;
    height: auto;
    font-size: 5vw;
    max-width: 100%;
    white-space: pre-wrap; /*\n을 줄바꿈으로 치환*/
}
.img {
    width: 30vw;
}
.logo {
    width: 80vw;
    position: fixed;
    bottom: 0;
}
</style>