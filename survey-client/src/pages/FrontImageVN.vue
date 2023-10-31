<template>
    <div class="logo-container" @click="moveMain">
        <img class="res_img" src="../assets/loading_img_vn.png" alt="Splash Image">
    </div>
</template>

<script>
export default {
    name: "FrontImageVN",
    methods: {
      moveMain() {
          this.$router.push('main?branchId=2');
      }
    },
    mounted() {
        setTimeout(() => {
            this.$router.push('main?branchId=2');
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
    max-width: 100%;
    max-height: 100%;
}
</style>
