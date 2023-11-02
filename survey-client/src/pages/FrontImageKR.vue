<template>
    <div class="logo-container" @click="moveMain">
        <img class="res_img" src="../assets/loading_img_kr.png" alt="Splash Image">
    </div>
</template>

<script>


export default {
    name: "FrontImageKR",
    methods: {
        moveMain() {
            this.$router.push('main?branchId=1');
        }
    },
    mounted() {
        setTimeout(() => {
            this.$router.push('main?branchId=1');
        }, 3000);
    }
}
</script>

<style scoped>
.logo-container{
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100vw;
    height: 100vh; /* 전체 화면 높이 */
    background-color: #fff; /* 원하는 배경색으로 설정 */
}
.res_img{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #fff; /* 로딩 이미지가 표시되는 동안 화면을 덮을 색상 */
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
