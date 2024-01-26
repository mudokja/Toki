<template>
  <v-container class="profile_page">
    
    <div id="profile_title">
      <p class="ml-14 mt-5 font-weight-black text-h5">프로필</p>
    </div>
    
    <v-row>
      <v-col cols="12">
        <v-container class="mt-10">
          <v-row>
            <v-col cols="3" align-self="center"><img src="@/assets/profile_assets/프로필.png" style="width: 200px; height: 200px;" alt="프로필 사진"></v-col>
              <v-col cols="9" align-self="center" class="profile_info_content">
                <p class="pb-5 font-weight-black text-h4">싸피토키  <img src="@/assets/profile_assets/보석.png" style="width: 30px; height: 30px;" alt="뱃지"></p>
                <p class="pb-10 font-weight-black text-h4">LEVEL 99</p>
                  <div class="pb-2 pr-5 font-weight-black d-flex flex-row-reverse">80.17%</div>
                <div class="pb-10 bg-indigo-lighten-5 rounded-pill" style="position: relative; z-index: 1;">
                  <div v-bind:class="`pb-10 bg-light-green-accent-3 rounded-pill w-${gaze}`" style="position: absolute;"></div>
                </div>
                <v-btn class="mt-5 font-weight-black rounded-lg" color="blue-lighten-1">사진 변경</v-btn>
                <p class="mt-3">10mb 이내의 JPEG, PNG, GIF 형식이어야 합니다.</p>
              </v-col>
           </v-row>
        </v-container>
      </v-col>
    </v-row>
  </v-container>

  <v-tabs v-model="tab" color="deep-purple-accent-4" align-tabs="center">
    <v-tab :value="1">나의 정보</v-tab>
    <v-tab :value="2">나의 친구</v-tab>
    <v-tab :value="3">나의 블랙리스트</v-tab>
  </v-tabs>
  
  <v-window v-model="tab">
    <v-window-item v-for="n in 3" :key="n" :value="n">
      <template v-if="n === 1"><ProfileMyDataComponent/></template>
      <template v-else-if="n === 2"><ProfileMyFriendsComponent/></template>
      <template v-else><ProfileMyBlacklistComponent/></template>
    </v-window-item>
  </v-window>

  
  
</template>
    
<script setup>
import { ref } from 'vue'
import ProfileMyDataComponent from '@/components/profile_components/ProfileMyDataComponent.vue'
import ProfileMyFriendsComponent from '@/components/profile_components/ProfileMyFriendsComponent.vue'
import ProfileMyBlacklistComponent from '@/components/profile_components/ProfileMyBlacklistComponent.vue'

const tab = ref(null);

const gaze = ref(25);
</script>

<style scoped>

</style>