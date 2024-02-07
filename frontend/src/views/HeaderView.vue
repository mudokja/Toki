<script setup>
import { ref, watchEffect, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import LoginButton from '@/components/buttons/LoginButton.vue';
import SideVarButton from '@/components/buttons/SideVarButton.vue';
import BroadcastButton from '@/components/buttons/BroadcastButton.vue';
import router from '@/router/index.js';



const isLoggedIn = ref(false)
const search = ref('')

const updateLoginStatus = () => {
  isLoggedIn.value = !!localStorage.getItem('accessToken')
}

watchEffect(() => {
  updateLoginStatus()
})

const logout = () => {
  localStorage.removeItem('accessToken')
  localStorage.removeItem('userId')
  localStorage.removeItem('auth')
  // 로그아웃 후 홈으로 리디렉션
  router.go(0)
}


// const tokenCheck = localStorage.accessToken

// const loginCheck = (value) => {
//   if(value) {
//     isLoggedIn.value = true
    
//   } else {
//     isLoggedIn.value = false
    
//   }
// }

// const logout = () => {
//   localStorage.removeItem('accessToken')
//   localStorage.removeItem('userId')
//   localStorage.removeItem('auth')
//   isLoggedIn.value = false
//   router.push('/')
  
// }


// onMounted(() => {
//   loginCheck(tokenCheck)
// })


</script>

<template>
  
    <div class="menu">
      <v-col
      cols="1"
      offset="1">
      

        <router-link :to="{ name: 'home'}"><img src="@/assets/profile_assets/토키 로고.png" alt="로고 이미지"></router-link>
      </v-col>
      <v-col
        cols="1"
      >

        <span id="menu_font">토키</span>
      </v-col>
      <v-col
        offset="1"
        cols="3"
      >

      <v-text-field
        style="margin-bottom: 5px;"
        v-model="search"
        prepend-inner-icon="mdi-magnify"
        density="compact"
        label="Search"
        base-color="light-blue-lighten-1"
        single-line
        flat
        hide-details
        variant="plain"
      ></v-text-field>
      </v-col>
      
      <!-- <img src="@/assets/profile_assets/비디오 만들기.png" alt="비디오 만들기"> -->
      
      <v-col
        offset="1"
        cols="1"
      >

        <BroadcastButton/>
      </v-col>
      
      <!-- <router-link :to="{ name: 'profile', params: { userId: userId }}"><img src="@/assets/profile_assets/프로필.png" alt="프로필 사진"></router-link> -->
      <v-col v-if="isLoggedIn">          
        <v-btn style="width: 90px;" @click="logout">logout</v-btn>
      </v-col>
      <v-col v-else>
        <LoginButton />
      </v-col>
        

      <v-col>

        <SideVarButton/>
      </v-col>
    </div>
    <!-- <v-app>

      <FooterComponent/>
    </v-app> -->
    
  
</template>

<style scoped>
.menu {
  white-space: nowrap;
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
  /* box-sizing: border-box; */

/* position: absolute; */
/* left: 0%;
right: 0%;
top: 0%;
bottom: 0%;
width: 500px;
height: 40px;
background: #FFFFFF;
border: 2px solid #36BEF1;
border-radius: 10px; */
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