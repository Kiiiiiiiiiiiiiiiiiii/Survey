<template>
    <div class="logo-container">
        <img src="../assets/front_img_vn.jpg" alt="Splash Image">
    </div>
</template>

<script>

export default {
    name: "FrontImageVN",
    mounted() {
        setTimeout(() => {
            this.$router.push('main?branchId=2');
        }, 3000);
    }
}
</script>

<style scoped>

</style>
