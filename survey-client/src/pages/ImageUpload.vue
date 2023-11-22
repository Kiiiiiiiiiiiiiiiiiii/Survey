<template>
    <div class="image-upload-container">
        <input type="file" ref="fileInput" style="display: none" @change="uploadImage" accept="image/jpeg, image/png">
        <div class="img-con">
            <div class="namecard">
                <img class="namecard-img" src="../../public/img/namecard.png">
            </div>
            <div class="upload-text" :style="fontStyle">
                <span class="conetent-text">{{ $t('upload') }}</span>
            </div>
            <br>
            <div class="upload-button">
                <img class="upload-button-img" @click="openFileInput" src="../../public/img/upload_btn.png">
            </div>
            <div class="skip-button">
                <img class="skip-button-img" @click="skip" src="../../public/img/skip_btn.png">
            </div>
        </div>
    </div>
    <div class="footer-logo">
        <Footer/>
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
          if (file && (file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jpg')) {
              const reader = new FileReader();

              reader.onload = (e) => {
                  this.imageUrl = e.target.result
              };

              if (file) {
                  reader.readAsDataURL(file);
              }
              this.saveImage(file)
          } else {
              window.alert("Please select a valid JPG or JPEG or PNG image file.")
          }
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
    src: url("../../public/fonts/NotoSans-Medium.ttf");
}
@font-face {
    font-family: NotoSans-Bold;
    src: url("../../public/fonts/NotoSans-Bold.ttf");
}

@font-face {
    font-family: NotoSansKR-Bold;
    src: url("../../public/fonts/NotoSansKR-Bold.ttf");
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
    z-index: 1;
    position: relative;
}
.skip-button-img {
    width: 30vw;
    height: auto;
    text-align: center;
    max-width: 100%; /* 이미지가 부모 너비에 맞게 크기 조절됨 */
    max-height: 100%; /* 이미지가 부모 높이에 맞게 크기 조절됨 */
    z-index: 0;
    position: relative;
}
/*.logo {
    width: 55vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
}*/
.footer-logo {
    /*width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
    justify-content: center;
    align-items: center;
    display: flex;*/
    width: 40vw;
    height: 7vh;
    position: fixed;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
    z-index: -1;
}
.conetent-text {
    font-size: 4vw;
}
</style>
