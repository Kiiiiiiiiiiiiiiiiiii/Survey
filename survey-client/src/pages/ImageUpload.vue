<template>
    <div class="black-bg">
<!--        <div class="white-bg ">
            <p>모달</p>
        </div>-->
        <div class="upload-button">
        <input type="file" ref="fileInput" style="display: none" @change="uploadImage">
<!--            <input type="file" ref="fileInput" style="display: none" @change="imageUpload()">-->
<!--        <input multiple @change="imageUpload()" ref="images" type="file">-->
        </div>
<!--        <button class="modal-exit-btn" @click="saveImage()">-->
        <button class="modal-exit-btn" @click="openFileInput">
            저장보튼
        </button>
        <div class="upload-image">
        <img :src="imageUrl" v-if="imageUrl" alt="Uploaded Image">
        </div>
    </div>
</template>
<script>

/*import Logo from "@/components/Logo.vue";*/
import {useRouter} from 'vue-router';
import axios from 'axios';
/*import {reactive, onBeforeMount} from "vue";*/

export default {
    components: {},
    setup() {
        console.log('setup props: ', )
        const router = useRouter();
        console.log('setup', history.state);
        console.log('setup', history.state.name);
        /*const formData = JSON.parse(router)
        console.log('setup formData : ', formData)*/
        /*const form = reactive({

        })
        console.log('child params: ', router.param)
          onBeforeMount(() => {
          const formData = route.params.data;
          });
        return {router};*/
        return {router}
    },
    data() {
      return {
          imageUrl: null
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
          console.log('uploadimage : ', file )
          this.saveImage(file)
        },
        imageUpload() {
            console.log('image start')
        },
        saveImage(event) {
            const file = event;
            const formData = new FormData();
            formData.append('image', file);
            formData.append('email', history.state.email);
            formData.append('branchId', history.state.branchId);


            axios.post("/api/imageUpload", formData).then((res) => {
                if (res.data) {
                    this.router.push({name: 'success'});
                    console.log('upload success')
                } else {
                   console.log('upload fail')
                }
            })
        }
    },
    mounted() {
        /*이곳은 화면에 처음 진입했을떄 바로 실행되는 부분*/
        /*console.log('imagepage: ')*/
    }
}
</script>

<style scoped>
.black-bg {
    display: flex;
    align-items: center;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.432);
    position: fixed;
    padding: 20px;
    background-color: green;
}

.white-bg {
    width: 100%;
    align-content: center;
    background-color: white;
    padding: 20px;
    border-radius: 8px;
}
.upload-button {
    background-color: coral;
}
</style>
