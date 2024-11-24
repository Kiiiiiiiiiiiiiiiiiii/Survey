<template>
  <br>
  <!-- 메인 타이틀을 표시하는 컨테이너 -->
  <div class="main-title-container">
    <div class="title-img">
      <!-- branchId가 1일 경우 한국 지점 타이틀 이미지 표시 -->
      <img class="img" src="../../public/img/main-title-kr.png" v-if="state.form.branchId == 1">
      <!-- branchId가 2일 경우 베트남 지점 타이틀 이미지 표시 -->
      <img class="img" src="../../public/img/main-title-vn.png" v-if="state.form.branchId == 2">
    </div>
    <br>
    <br>
    <br>
    <br>
  </div>
  <!-- 홈 메인 컨테이너 -->
  <div class="home-main-container" style="overflow-y: auto">
    <div class="home-child-container">
      <!-- 입력 폼 컨테이너 -->
      <div class="grid-container" :style="fontStyleBold">
        <!-- 이름 입력 필드 -->
        <div class="inner-grid">
          <span class="ptag" style="color: red">⦁</span> {{ $t('name') }} : <input
            class="input-element"
            v-model="state.form.name"
            required
            :style="fontStyle"/>
        </div>
        <!-- 회사명 입력 필드 -->
        <div class="inner-grid">
          <span class="ptag" style="color: red">⦁</span> {{ $t('company') }} : <input class="input-element"
                                                                                      v-model="state.form.company"
                                                                                      :style="fontStyle"/>
        </div>
        <!-- 이메일 입력 필드 -->
        <div class="inner-grid">
          <span class="ptag" style="color: red">⦁</span> {{ $t('email') }} : <input class="input-element"
                                                                                    v-model="state.form.email"
                                                                                    :style="fontStyle"/>
        </div>
        <!-- 전화번호 입력 필드 -->
        <div class="inner-grid">
          <span class="ptag" style="color: red">⦁</span> {{ $t('phone') }} : <input class="input-element"
                                                                                    v-model="state.form.phone"
                                                                                    :style="fontStyle"/>
        </div>
        <!-- 업종 선택 필드 -->
        <div class="inner-grid">
          <span class="ptag" style="color: red">⦁</span> {{ $t('industry') }} :
          <select class="input-element" v-model="state.form.industry" :style="fontStyle">
            <option>{{ $t('confectionary') }}</option>
            <option>{{ $t('iceDessert') }}</option>
            <option>{{ $t('hmr') }}</option>
            <option>{{ $t('alternativeFood') }}</option>
            <option>{{ $t('babyMeal') }}</option>
            <option>{{ $t('etc') }}</option>
          </select>
        </div>
      </div>

      <!-- 사용자 동의 섹션 -->
      <div class="container-element-agree" :style="fontStyle">
        <p></p>
        <span class="consent-text">{{ $t('consent') }}</span>
        <p></p>
        <div class="flex-container">
          <!-- '동의' 라디오 버튼 -->
          <div class="flex-item-agree">
            <input type="radio" v-bind:value="true" v-model="selected">
            <label for="one">{{ $t('agree') }}</label>
          </div>
          <span></span>
          <!-- '비동의' 라디오 버튼 -->
          <div class="flex-item-disagree">
            <input type="radio" v-bind:value="false" v-model="selected">
            <label for="one">{{ $t('disagree') }}</label>
          </div>
        </div>
      </div>

      <!-- 제출 버튼 섹션 -->
      <div class="submit-buttons">
        <!-- 동의하지 않았을 때 비활성화된 제출 버튼 -->
        <img src="../../public/img/disable_submit_btn.png" v-if="!selected" class="submit-button">
        <!-- 동의했을 때 활성화된 제출 버튼 -->
        <img src="../../public/img/submit_btn.png" v-if="selected" class="submit-button"
             @click.prevent="submit">
      </div>
      <!-- 푸터 로고 섹션 -->
      <div class="footer-logo">
        <Footer/>
      </div>
    </div>
  </div>
</template>

<script>

import {reactive} from "vue";
import axios from "axios";
import {useRouter} from "vue-router";
import Footer from "@/components/Footer.vue";

export default {
  components: {Footer},
  setup() {
    const router = useRouter()
    const state = reactive({
      form: {
        name: "", // 이름 필드
        company: "", // 회사명 필드
        email: "", // 이메일 필드
        phone: "", // 전화번호 필드
        industry: "", // 업종 필드
        branchId: router.currentRoute.value.query.branchId // 라우트 쿼리에서 branchId를 가져옴
      }
    })

    const submit = () => {
      // 누락된 필드를 저장하는 배열
      const missingFields = []
      if (state.form.name === "") {
        missingFields.push("Name")
      }
      if (state.form.company === "") {
        missingFields.push("Company")
      }
      if (state.form.email === "") {
        missingFields.push("Email")
      }
      if (state.form.phone === "") {
        missingFields.push("Mobile")
      }
      if (state.form.industry === "") {
        missingFields.push("Industry")
      }
      // 누락된 필드가 있을 경우 알림 메시지 표시
      if (missingFields.length > 0) {
        window.alert("Please fill in the following fields: " + missingFields.join(", "))
        return false
      } else if (checkemail()) {
        // 이메일이 유효한 경우 서버에 데이터 제출
        axios.post("/api/submit", state.form).then((res) => {
          if (res.data) {
            // 데이터 제출 후 성공 시 이미지 업로드 페이지로 이동
            router.push({name: 'imageUpload', query: {branchId: state.form.branchId}, state: state.form});
          } else {
            // 중복 데이터가 있을 경우 중복 페이지로 이동
            router.push({name: 'duplicate', query: {branchId: state.form.branchId}});
          }
        })
      }
    }
    // 이메일 유효성 검사 함수
    function checkemail() {
      const validateEmail = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
      if (!validateEmail.test(state.form.email) || !state.form.email) {
        window.alert("Please enter a valid email address.");
        return false;
      }
      return true;
    }

    return {state, submit}
  },
  data() {
    return {
      selected: false, // 라디오 버튼의 기본 값 설정 (동의 여부)
      true: true,
      false: false,
      nowlocale: 'en' // 현재 로케일 설정
    }
  },
  computed: {
    // 현재 로케일에 따라 폰트 스타일을 동적으로 설정
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
    },
  }
}
</script>

<style scoped>
@font-face {
  font-family: NotoSans-Bold;
  src: url("../../public/fonts/NotoSans-Bold.ttf");
}

@font-face {
  font-family: NotoSansKR-Bold;
  src: url("../../public/fonts/NotoSansKR-Bold.ttf");
}

/* 홈 메인 컨테이너 스타일 */
/* 홈 메인 컨테이너 스타일 */
.home-main-container {
  max-height: 100vh; /* 최대 높이를 화면의 높이로 제한 */
  position: fixed; /* 화면의 고정된 위치에 유지 */
  width: 100vw; /* 전체 화면 너비를 사용 */
  height: 75vh; /* 화면 높이의 75%를 사용 */
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: flex-start; /* 위쪽 정렬 */
  overflow-y: auto; /* 세로 스크롤 허용 */
}

/* 폼 입력 필드를 배치하는 그리드 컨테이너 스타일 */
.grid-container {
  display: grid; /* 그리드 레이아웃 사용 */
  grid-template-rows: 1fr 1fr; /* 두 개의 행을 동일한 비율로 설정 */
  gap: 20px; /* 그리드 아이템 사이의 간격 */
  padding-left: 14vw; /* 왼쪽 패딩을 화면 너비의 14%로 설정 */
  text-align: left; /* 텍스트를 왼쪽 정렬 */
  place-items: self-start; /* 아이템을 상단 및 왼쪽에 정렬 */
}

/* 작은 화면에 대한 반응형 스타일 */
@media only screen and (max-width: 280px){
  .grid-container {
    font-size: 4vw; /* 글꼴 크기를 화면 너비의 4%로 설정 */
  }
}

/* 각 폼 필드에 대한 내부 그리드 컨테이너 스타일 */
.inner-grid {
  font-size: 3vw; /* 글꼴 크기를 화면 너비의 3%로 설정 */
  display: grid; /* 그리드 레이아웃 사용 */
  grid-template-columns: 1fr 8fr 14fr; /* 세 개의 열을 다양한 비율로 설정 */
  text-align: left; /* 텍스트를 왼쪽 정렬 */
  align-items: center; /* 텍스트를 수직으로 가운데 정렬 */
}

/* 빨간색 점 스타일 */
.ptag {
  place-items: center; /* 아이템을 중앙에 위치 */
  align-items: center; /* 텍스트를 수직으로 가운데 정렬 */
}

/* 입력 필드 스타일 */
.input-element {
  width: 45vw; /* 너비를 화면 너비의 45%로 설정 */
  border: 1px solid #ccc; /* 연한 회색 테두리 적용 */
}

/* 동의 섹션 스타일 */
.container-element-agree {
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  text-align: center; /* 텍스트를 중앙에 정렬 */
  font-size: 2vh; /* 글꼴 크기를 화면 높이의 2%로 설정 */
  white-space: pre-wrap; /* 줄 바꿈을 허용하고 텍스트 줄을 감쌈 */
  width: 80%; /* 너비를 부모 요소의 80%로 설정 */
  margin: 0 auto; /* 상하 0, 좌우 중앙 정렬 */
}

/* 제출 버튼 컨테이너 스타일 */
.submit-buttons {
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  text-align: center; /* 텍스트를 중앙에 정렬 */
  display: flex; /* 플렉스 박스를 사용 */
  z-index: 1; /* 버튼을 위에 표시 */
  position: relative; /* 위치를 부모 요소에 상대적으로 설정 */
  margin-top: 20px; /* 상단 여백 */
  margin-bottom: 20px; /* 하단 여백 */
}

/* 제출 버튼 스타일 */
.submit-button {
  display: flex; /* 플렉스 박스로 배치 */
  width: 35%; /* 너비를 부모 요소의 35%로 설정 */
  height: 25%; /* 높이를 부모 요소의 25%로 설정 */
}

/* 푸터 로고 스타일 */
.footer-logo {
  width: 40vw; /* 너비를 화면 너비의 40%로 설정 */
  height: 7vh; /* 높이를 화면 높이의 7%로 설정 */
  position: fixed; /* 화면에서 고정 위치에 배치 */
  bottom: 0; /* 화면 하단에 배치 */
  left: 50%; /* 가로 중앙 정렬 */
  transform: translateX(-50%); /* X 축 중앙 정렬 조정 */
  display: flex; /* 플렉스 박스를 사용 */
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: center; /* 세로 중앙 정렬 */
  margin-top: 20px; /* 상단 여백 */
}

/* 동의 및 비동의 버튼을 위한 플렉스 컨테이너 */
.flex-container {
  display: flex; /* 플렉스 박스를 사용 */
  justify-content: space-between; /* 두 div를 양쪽 끝에 정렬 */
}

/* 비동의 버튼 스타일 */
.flex-item-disagree {
  width: 80vw; /* 너비를 화면 너비의 80%로 설정 */
}

/* 동의 버튼 스타일 */
.flex-item-agree {
  width: 80%; /* 너비를 부모 요소의 80%로 설정 */
}

/* 동의 텍스트 스타일 */
.consent-text {
  font-size: 3vw; /* 글꼴 크기를 화면 너비의 3%로 설정 */
  width: 50vw; /* 너비를 화면 너비의 50%로 설정 */
}

/* 메인 타이틀 컨테이너 스타일 */
.main-title-container {
  height: 100%; /* 전체 높이를 사용 */
  display: flex; /* 플렉스 박스를 사용 */
  justify-content: center; /* 가로 중앙 정렬 */
  align-items: flex-start; /* 위쪽 정렬 */
}

/* 타이틀 이미지 컨테이너 스타일 */
.title-img {
  height: 100%; /* 전체 높이를 사용 */
  text-align: center; /* 이미지를 가운데 정렬 */
}

/* 타이틀 이미지 스타일 */
.img {
  width: 70%; /* 너비를 부모 요소의 70%로 설정 */
  height: auto; /* 가로 세로 비율을 유지하여 높이 자동 조정 */
}

/* 홈 자식 컨테이너 스타일 */
.home-child-container {
  /* 추가적인 스타일 필요 시 여기에 작성 */
}

@media only screen and (max-height: 620px) {
  .inner-grid {
    font-size: 2vw; /* 글꼴 크기를 화면 너비의 3%로 설정 */
    display: grid; /* 그리드 레이아웃 사용 */
    grid-template-columns: 1fr 8fr 14fr; /* 세 개의 열을 다양한 비율로 설정 */
    text-align: left; /* 텍스트를 왼쪽 정렬 */
    align-items: center; /* 텍스트를 수직으로 가운데 정렬 */
  }
}
/* 높이가 480px 이하인 작은 화면에 대한 반응형 스타일 */
@media only screen and (max-height: 480px) {
  .main-title-container {
    height: 55%; /* 높이를 부모 요소의 55%로 설정 */
    display: flex; /* 플렉스 박스를 사용 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: flex-start; /* 위쪽 정렬 */
  }
  .home-main-container{
    height: 90vh; /* 높이를 화면 높이의 90%로 설정 */
  }

  .title-img {
    height: 70%; /* 높이를 부모 요소의 70%로 설정 */
    text-align: center; /* 이미지를 가운데 정렬 */
  }
  .img {
    width: 60%; /* 너비를 부모 요소의 60%로 설정 */
    height: auto; /* 가로 세로 비율 유지 */
  }
  .home-main-container{
    height: auto; /* 높이 자동 조정 */
  }
  .grid-container {
    font-size: 9px; /* 글꼴 크기 조정 */
    display: grid; /* 그리드 레이아웃 사용 */
    grid-template-rows: 1fr 1fr; /* 두 개의 행 생성 */
    gap: 8px; /* 그리드 아이템 간의 간격 */
    padding-left: 14vw; /* 왼쪽 패딩 */
    text-align: left; /* 텍스트 왼쪽 정렬 */
    place-items: self-start; /* 수직 및 수평 정렬 */
  }
  .container-element-agree{
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
    text-align: center; /* 텍스트 중앙 정렬 */
    font-size: 2vh; /* 글꼴 크기 조정 */
    white-space: pre-wrap; /* 줄바꿈 허용 */
    width: 80%; /* 너비 설정 */
    margin: 0 auto; /* 상하 좌우 여백 */
  }
  .consent-text{
    font-size: 3vw; /* 글꼴 크기 */
    width: 45vw; /* 너비 설정 */
  }
  .submit-button {
    transform: scale(0.9); /* 크기 조정 */
  }
  .footer-logo {
    width: 20vw; /* 너비 설정 */
    height: auto; /* 높이 자동 설정 */
    position: fixed; /* 화면 고정 위치 */
    bottom: 0; /* 하단 정렬 */
    left: 50%; /* 좌우 중앙 정렬 */
    transform: translateX(-50%); /* X 축 중앙 정렬 */
    display: flex; /* 플렉스 박스 사용 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
  }
}

/* 높이가 320px 이하인 작은 화면에 대한 반응형 스타일 */
@media only screen and (max-height: 320px) {
  .main-title-container {
    height: 45%; /* 높이 설정 */
    display: flex; /* 플렉스 박스 사용 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: flex-start; /* 위쪽 정렬 */
  }
  .home-main-container{
    height: 80vh; /* 높이 설정 */
  }

  .title-img {
    height: 50%; /* 높이 설정 */
    text-align: center; /* 텍스트 가운데 정렬 */
  }
  .img {
    width: 50%; /* 너비 설정 */
    height: auto; /* 높이 자동 조정 */
  }
  .home-main-container{
    height: auto; /* 높이 자동 조정 */
  }
  .grid-container {
    font-size: 6px; /* 글꼴 크기 조정 */
    display: grid; /* 그리드 레이아웃 사용 */
    grid-template-rows: 1fr 1fr; /* 두 개의 행 생성 */
    gap: 6px; /* 그리드 아이템 간의 간격 */
    padding-left: 14vw; /* 왼쪽 패딩 설정 */
    text-align: left; /* 텍스트 왼쪽 정렬 */
    place-items: self-start; /* 상단 및 왼쪽 정렬 */
  }
  .container-element-agree{
    transform: scale(0.9); /* 크기 조정 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
    text-align: center; /* 텍스트 중앙 정렬 */
    font-size: 2vh; /* 글꼴 크기 */
    white-space: pre-wrap; /* 줄바꿈 허용 */
    width: 80%; /* 너비 설정 */
    margin: 0 auto; /* 상하 좌우 여백 */
  }
  .consent-text{
    font-size: 3vw; /* 글꼴 크기 */
    width: 20vw; /* 너비 설정 */
  }
  .submit-button {
    display: flex; /* 플렉스 박스 사용 */
    width: 30%; /* 너비 설정 */
    height: auto; /* 높이 자동 설정 */
  }
  .footer-logo {
    width: 16vw; /* 너비 설정 */
    height: auto; /* 높이 자동 설정 */
    position: fixed; /* 화면 고정 위치 */
    bottom: 0; /* 하단 정렬 */
    left: 50%; /* 좌우 중앙 정렬 */
    transform: translateX(-50%); /* X 축 중앙 정렬 */
    display: flex; /* 플렉스 박스 사용 */
    justify-content: center; /* 가로 중앙 정렬 */
    align-items: center; /* 세로 중앙 정렬 */
  }
}

</style>
