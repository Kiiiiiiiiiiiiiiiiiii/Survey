<template>
    <div class="logo-container">
        <img src="../assets/front_img_kr.jpg" alt="Splash Image">
    </div>
</template>

<script>

export default {
    name: "FrontImageKR",
    mounted() {
        setTimeout(() => {
            this.$router.push('main?branchId=1');
        }, 3000);
    }
}
</script>

<style scoped>
</style>
