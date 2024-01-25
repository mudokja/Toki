<script setup>
import { ref } from 'vue';
import SideVarView from '@/views/SideVarView.vue';
import LoginButton from '@/components/buttons/LoginButton.vue';
import SideVarButton from '@/components/buttons/SideVarButton.vue';

const isLoggedIn = ref(true)

</script>

<template>
  <div>
    <div class="menu">
      <router-link :to="{ name: 'home'}"><img src="@/assets/profile_assets/토키 로고.png" alt="로고 이미지"></router-link>
      <span id="menu_font">토키</span>
      <input id="menu_search">
      <img src="@/assets/profile_assets/비디오 만들기.png" alt="비디오 만들기">
      <!-- <router-link :to="{ name: 'profile', params: { userId: userId }}"><img src="@/assets/profile_assets/프로필.png" alt="프로필 사진"></router-link> -->
      <div v-if="isLoggedIn">
        <LoginButton />
      </div>

      <v-btn v-else id="menu_btn_logout" @click="isLoggedIn = !isLoggedIn">logout</v-btn>
      

      
      <SideVarButton/>
    </div>
    <!-- <v-app>

      <FooterComponent/>
    </v-app> -->
    
  </div>
</template>

<style scoped>
.menu {
    /* position: fixed; 
  top: 0; 
  left: 0; 
  width: 100%; 
  background-color: #FFF; 
  z-index: 1000; 
  margin-top: 0; 
  column-gap: 10px; 
  display: flex;
  justify-content: space-evenly;
  padding: 10px 0;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);  */
    /* position: fixed;
    display: flex;
    left: 0;
    width: 100%;
    margin-top: 1px;
    height: 80px;
    background-color:#FFF;
    margin-bottom: 50px;
    justify-content:space-evenly;
    align-items: center;
    margin: auto; */
    position: fixed; /* 고정 위치 */
  top: 0; /* 상단에 위치 */
  left: 0; /* 왼쪽 끝에서 시작 */
  right: 0; /* 오른쪽 끝까지 연장 */
  z-index: 1000; /* 다른 요소들 위에 위치 */
  background-color: #FFF; /* 배경색 설정 */
  box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* 그림자 효과 */
  display: flex;
  justify-content: space-evenly; /* 요소들을 균등하게 배치 */
  align-items: center;
  height: 60px; /* 네비게이션 바의 높이 */
}
img {
  width: 50px;
  height: 50px;
  object-fit: cover;
}
#menu_font {
  color: rgb(159, 220, 225);
  padding-top: 10px;
  font-size: 20px;
  font-weight: 900;
}
#menu_search {
  background-color: rgb(255, 255, 255);
  border: 3px solid rgb(159, 220, 225);
  margin-top: 10px;
  height: 30px;
  border-radius: 5px;
}
#menu_btn_logout {
  background-color: rgb(235, 235, 235);
  border: 3px solid rgb(159, 220, 225);
  margin-top: 10px;
  height: 30px;
  width: 60px;
  border-radius: 5px;
}
</style>