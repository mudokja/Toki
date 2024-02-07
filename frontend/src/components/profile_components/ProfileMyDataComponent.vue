<script setup>
import { memberUpdate } from '@/js/Member.js'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth.js'

// // 부모 ProfileComponent.vue 에서 보낸 props
// const memberDetailDataProps = defineProps({
//   //유저 정보 데이터
//   memberDetailData: Object,
// })

// pinia 유저 id 가져오기
// const userId = useAuthStore().user.userId

// router push 를 위한 인스턴스 생성
const routerProfile = useRouter()

// // PUT 유저 정보 수정
//   // 요청 데이터
//   const memberDetailValue = ref({
//     userNickname : memberDetailDataProps.memberDetailData.userNickname,
//     profileImageUrl: '',  /// xxxxx 파일 CRUD 아직 안됨 xxxxx
//     email: memberDetailDataProps.memberDetailData.email
//   })
// const memberUpdateAxios = function() {
//   memberUpdate(
//     userId,
//     memberDetailValue.value,
//     ({data}) => {console.log(data, '[Success] ProfileComponent.vue : memberUpdateAxios');},
//     (fail) => {console.log(fail, '[Error] ProfileComponent.vue : memberUpdateAxios');}
//     ),
//     // 프로필 페이지로 리다이렉트
//     routerProfile.push({ name: 'profile' })
//   }

</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12">
        <v-container id="profile_my_data" class="pl-7 pt-8 pb-10 font-weight-black text-h6 d-flex flex-column">
          <div>
              <p class="ml-4 mb-4">닉네임</p>
              <v-text-field label="닉네임 변경" placeholder="memberDetailData.userNickname"></v-text-field>
          </div>
          <div>
              <p class="ml-4 mb-4">자기 소개</p>
              <v-textarea model-value="(기존에 써져있는 자기소개)" counter maxlength="120" single-line></v-textarea>
          </div>
          <div>
              <p class="ml-4 mb-4">가입 날짜</p>
              <v-text-field label="가입 날짜" placeholder="memberDetailData.joinDate"></v-text-field>
          </div>
          
          <!-- /////////////////////////////////////////////////////////////////////////// -->
          <!-- 유저가 정하는 선호 태그 / 차단 태그 인데, 쓰일지 안 쓰일지 몰라서 일단 주석함. -->
          <!-- /////////////////////////////////////////////////////////////////////////// -->
          <!-- /////////////////////////////////////////////////////////////////////////// -->
          <!-- <div>
              <p>선호 태그</p>
              <v-autocomplete 
                :items="['게임', '공부', '스포츠', '잡담', '음악', '정치', '자유(기타)']"
                chips
                hide-no-data
                hide-selected
                multiple
                single-line>
              </v-autocomplete>
          </div>
          <div>
              <p>차단 태그</p>
              <v-autocomplete 
                :items="['게임', '공부', '스포츠', '잡담', '음악', '정치', '자유(기타)']"
                chips
                hide-no-data
                hide-selected
                multiple
                single-line>
              </v-autocomplete>
          </div> -->
          <!-- /////////////////////////////////////////////////////////////////////////// -->
          <!-- /////////////////////////////////////////////////////////////////////////// -->

          <div class="mt-10 d-flex flex-row-reverse ga-5">
            <v-btn class="rounded-lg" variant="tonal">회원 탈퇴</v-btn>
            <v-btn class="font-weight-black rounded-lg" color="blue-lighten-1">프로필 변경</v-btn>
          </div>
        </v-container>
      </v-col>
    </v-row>
  </v-container>
</template>



<style scoped>
#profile_my_data {
  background-color: rgb(238, 238, 238);
}
</style>