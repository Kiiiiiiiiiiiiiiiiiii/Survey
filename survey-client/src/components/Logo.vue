<template>
    <header class="header">
        <div class="logo-container">
            <img class="logo" alt="logo" src="../../public/img/logo.png">
        </div>
    </header>
</template>

<script>

export default {
    name: "Logo"
}
</script>

<style scoped>
.header{
    display: flex;
    justify-content: center;
    align-items: center;
    max-width: 100%;
    height: auto;
}
.logo {
    justify-content: center;
    align-items: center;
    max-width: 100%;
    height: auto;
    text-align: center;

  @media only screen and (max-height: 480px) {
    .logo{
      transform: scale(0.1);
    }
  }
  }
</style>
