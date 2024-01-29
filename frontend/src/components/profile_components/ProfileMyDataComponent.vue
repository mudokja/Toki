<script setup>

</script>

<template>
  <v-container fluid>
    <v-row>
      <v-col cols="12">
        <v-container id="profile_my_data" class="pl-7 pt-8 pb-10 font-weight-black text-h6 d-flex flex-column">
          <div>
              <p class="ml-4 mb-4">아이디</p>
              <v-text-field label="아이디 변경" placeholder="(기존 아이디)" class=""></v-text-field>
          </div>
          <div>
              <p class="ml-4 mb-4">닉네임</p>
              <v-text-field label="닉네임 변경" placeholder="(기존 닉네임)" class=""></v-text-field>
          </div>
          <div>
              <p class="ml-4 mb-4">자기 소개</p>
              <v-textarea model-value="(기존에 써져있는 자기소개)" counter maxlength="120" single-line></v-textarea>
          </div>
          <div>
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
          </div>
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