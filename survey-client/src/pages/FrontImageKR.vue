<template>
    <div class="logo-container" @click="moveMain">
        <img class="res_img" src="../../public/img/loading_img_kr.png" alt="Splash Image">
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
    overflow: hidden; /* 이미지 넘침을 숨깁니다. */
    /*position: relative;*/

}
.res_img{
    position: absolute;
 /*   object-fit: contain;*/
    /*top: 0;
    left: 0;*/
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
