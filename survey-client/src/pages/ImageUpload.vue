<template>
    <div class="image-upload-container">
        <input type="file" ref="fileInput" style="display: none" @change="uploadImage">
        <div class="img-con">
            <div class="namecard">
                <img class="namecard-img" src="../assets/namecard.png">
            </div>
            <div class="upload-text" :style="fontStyle">
                <span class="conetent-text">{{ $t('upload') }}</span>
            </div>
            <br>
            <div class="upload-button">
                <img class="upload-button-img" @click="openFileInput" src="../assets/upload_btn.png">
            </div>
            <div class="skip-button">
                <img class="skip-button-img" @click="skip" src="../assets/skip_btn.png">
            </div>
        </div>
        <div class="logo">
            <Footer/>
        </div>
    </div>
</template>
<script>

import {useRouter} from 'vue-router';
import axios from 'axios';
import Footer from "@/components/Footer.vue";

export default {
    components: {Footer},
    setup() {
        const router = useRouter();
        return {router}
    },
    data() {
      return {
          imageUrl: null,
          nowlocale: 'en',
          branchId: this.$route.query.branchId
      }
    },
    methods: {
        openFileInput() {
          this.$refs.fileInput.click();
        },
        uploadImage(event) {
          const file = event.target.files[0];
          const reader = new FileReader();

          reader.onload = (e) => {
              this.imageUrl = e.target.result
          };

          if(file) {
              reader.readAsDataURL(file);
          }
          this.saveImage(file)
        },
        saveImage(event) {
            const file = event;
            const formData = new FormData();
            formData.append('image', file);
            formData.append('email', history.state.email);
            formData.append('branchId', history.state.branchId);


            axios.post("/api/imageUpload", formData).then((res) => {
                if (res.data) {
                    this.router.push({name: 'success', query: {branchId: history.state.branchId}});
                    console.log('upload success')
                } else {
                   console.log('upload fail')
                }
            })
        },
        skip() {
            this.router.push({name: 'success', query: {branchId: this.branchId}});
        }
    },
    computed: {
        fontStyleBold() {
            if (this.$i18n.locale === 'ko') {
                return {
                    fontFamily: 'NotoSansKR-Bold'
                }
            } else {
                return {
                    fontFamily: 'NotoSans-Bold'
                }
            }
        },
        fontStyle() {
            if (this.$i18n.locale === 'ko') {
                return {
                    fontFamily: 'NotoSansKR-Medium'
                }
            } else {
                return {
                    fontFamily: 'NotoSans-Medium'
                }
            }
        }
    },
    mounted() {
        /*되긴함 console.log('imageUpload: ', this.$router.currentRoute.value.query.branchId)*/
        /*console.log('imageUpload3: ', this.$router.currentRoute.query.branchId)*/
        /*this.$route.params.branchId*/
    }
}
</script>

<style scoped>
@font-face {
    font-family: NotoSans-Medium;
    src: url("../assets/fonts/NotoSans-Medium.ttf");
}
@font-face {
    font-family: NotoSans-Bold;
    src: url("../assets/fonts/NotoSans-Bold.ttf");
}

@font-face {
    font-family: NotoSansKR-Bold;
    src: url("../assets/fonts/NotoSansKR-Bold.ttf");
}
.image-upload-container {
    width: 100%;
    height: auto;
    position: fixed;
    justify-content: center;
    align-items: flex-start;
    display: flex;
    margin-top: 15%;
}
.img-con{
    display: grid;
    grid-template-columns: 1fr;
    width: 100vw; /* 원하는 백분율로 이미지 크기를 조절 */
    height: auto; /* 가로 너비를 기반으로 세로 비율을 유지 */
    text-align: center;
    justify-content: center;
    align-items: center;
}
.upload-text{
    white-space: pre-wrap; /*\n을 줄바꿈으로 치환*/
    color: red;
    font-size: 5vw;
    align-items: center;
}
.namecard {
    display: flex;
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 추가 */
    max-width: 100%; /* 원하는 너비로 설정, 예: 50% */
    max-height: 100%; /* 원하는 높이로 설정, 예: 50% */
    /*margin: 3vh 0; !* 이미지 컬럼 사이의 세로 간격을 조절 *!*/
}
.namecard-img {
    width: 50vw;
    height: auto;
    text-align: center;
    max-width: 100%; /* 이미지가 부모 너비에 맞게 크기 조절됨 */
    max-height: 100%; /* 이미지가 부모 높이에 맞게 크기 조절됨 */
}
.upload-button {
    display: flex;
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 추가 */
    max-width: 100%; /* 원하는 너비로 설정, 예: 50% */
    max-height: 100%; /* 원하는 높이로 설정, 예: 50% */
    margin: 3vh 0; /* 이미지 컬럼 사이의 세로 간격을 조절 */
}
.upload-button-img {
    width: 30vw;
    height: auto;
    text-align: center;
    max-width: 100%; /* 이미지가 부모 너비에 맞게 크기 조절됨 */
    max-height: 100%; /* 이미지가 부모 높이에 맞게 크기 조절됨 */

}
.skip-button {
    display: flex;
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 추가 */
    max-width: 100%; /* 원하는 너비로 설정, 예: 50% */
    max-height: 100%; /* 원하는 높이로 설정, 예: 50% */
}
.skip-button-img {
    width: 30vw;
    height: auto;
    text-align: center;
    max-width: 100%; /* 이미지가 부모 너비에 맞게 크기 조절됨 */
    max-height: 100%; /* 이미지가 부모 높이에 맞게 크기 조절됨 */
}
.logo {
    width: 55vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
}
.conetent-text {
    font-size: 4vw;
}
</style>
