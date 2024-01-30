<script setup>
import { ref } from 'vue'
import SvgIcon from '@jamescoyle/vue-icon'
import { mdiMicrophone, mdiVideo, mdiMonitorShare, mdiRadioboxMarked, mdiEmoticonOutline, mdiCog, mdiChatProcessingOutline } from '@mdi/js'

import RoomChatComponent from '@/components/room_components/RoomChatComponent.vue'
// 아이콘 변수
const pathMicrophone = ref(mdiMicrophone )
const pathVideo = ref(mdiVideo)
const pathMonitorShare = ref(mdiMonitorShare)
const pathRadioboxMarked = ref(mdiRadioboxMarked)
const pathEmoticonOutline = ref(mdiEmoticonOutline)
const pathCog = ref(mdiCog)
const pathChatProcessingOutline  = ref(mdiChatProcessingOutline)

// 채팅 확장
const chatBox = ref(false)
</script>
<template>
  <v-container id="enter" class="h-100">
    <v-row class="h-100">
      <!-- 화면 -->
      <v-col id="screen">
        <!-- 위 서브 화면 -->
        <v-row class="mt-1" style="height: 20%;">
          <v-col id="biggBG1" class="ml-2 mr-2 mb-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG2" class="ml-2 mr-2 mb-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG3" class="ml-2 mr-2 mb-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG4" class="ml-2 mr-2 mb-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG5" class="ml-2 mr-2 mb-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
        </v-row>
        <!-- 메인 화면 -->
        <v-row id="main-screen" class="h-50">
          <v-col cols="12">
            메인 화면
          </v-col>
        </v-row>
        <!-- 아래 서브 화면 -->
        <v-row class="mb-1" style="height: 20%;">
          <v-col id="biggBG1" class="ml-2 mr-2 mt-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG2" class="ml-2 mr-2 mt-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG3" class="ml-2 mr-2 mt-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG4" class="ml-2 mr-2 mt-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
          <v-col id="biggBG5" class="ml-2 mr-2 mt-2 d-flex align-end">
            <v-sheet>아이디 or 닉네임</v-sheet>
          </v-col>
        </v-row>


        <!-- 영상 옵션 바 -->
        <v-row class="mt-1 ml-1 mr-1" style="height: 10%;">
          
          <!-- 마이크 설정-->
          <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 30px; border: 1px solid white;">
                <v-expansion-panel-title expand-icon="mdi-menu-down">
                  <svg-icon type="mdi" :path="pathMicrophone"></svg-icon>
                  <p class="ml-5">마이크</p>
                </v-expansion-panel-title>
                <v-expansion-panel-text>
                  <v-list bg-color="black">
                    <v-list-item value="마이크-on-off">마이크 on/off</v-list-item>
                    <v-list-item value="음성변조">음성 변조</v-list-item>
                  </v-list>
                </v-expansion-panel-text>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
          <!-- 캠 설정 -->
          <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 30px; border: 1px solid white;">
                <v-expansion-panel-title expand-icon="mdi-menu-down">
                  <svg-icon type="mdi" :path="pathVideo"></svg-icon>
                  <p class="ml-5">캠</p>
                </v-expansion-panel-title>
                <v-expansion-panel-text>
                  <v-list bg-color="black">
                    <v-list-item value="캠-on-off">캠 on/off</v-list-item>
                    <v-list-item value="가상배경">가상 배경</v-list-item>
                    <v-list-item value="버츄얼">버츄얼 적용</v-list-item>
                  </v-list>
                </v-expansion-panel-text>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
          <!-- 화면 공유 -->
          <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 30px; border: 1px solid white;">
                <v-expansion-panel-title expand-icon="mdi-menu-down">
                  <svg-icon type="mdi" :path="pathMonitorShare"></svg-icon>
                  <p class="ml-5">공유</p>
                </v-expansion-panel-title>
                <v-expansion-panel-text>
                  <v-list bg-color="black">
                    <v-list-item value="화면공유">화면 공유하기</v-list-item>
                  </v-list>
                </v-expansion-panel-text>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
          <!-- 녹화 -->
          <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 30px; border: 1px solid white;">
                <v-expansion-panel-title expand-icon="mdi-menu-down">
                  <svg-icon type="mdi" :path="pathRadioboxMarked"></svg-icon>
                  <p class="ml-5">녹화</p>
                </v-expansion-panel-title>
                <v-expansion-panel-text>
                  <v-list bg-color="black">
                    <v-list-item value="화면녹화">화면 녹화</v-list-item>
                  </v-list>
                </v-expansion-panel-text>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
          <!-- 이모티콘 -->
          <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 30px; border: 1px solid white;">
                <v-expansion-panel-title expand-icon="mdi-menu-down">
                  <svg-icon type="mdi" :path="pathEmoticonOutline"></svg-icon>
                  <p class="ml-5">이모티콘</p>
                </v-expansion-panel-title>
                <v-expansion-panel-text>
                  <v-chip-group>
                    <v-chip value="화면녹화">😀</v-chip>
                    <v-chip value="화면녹화">😀</v-chip>
                    <v-chip value="화면녹화">😀</v-chip>
                    <v-chip value="화면녹화">😀</v-chip>
                    <v-chip value="화면녹화">😀</v-chip>
                  </v-chip-group>
                </v-expansion-panel-text>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
          <!-- 환경 설정 -->
          <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 10px; border: 1px solid white;">
                <v-expansion-panel-title hide-actions>
                  <svg-icon type="mdi" :path="pathCog"></svg-icon>
                  <p class="ml-5">설정</p>
                </v-expansion-panel-title>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
          <!-- 채팅 -->
          <v-col>
            <v-expansion-panels @click="chatBox = !chatBox">
              <v-expansion-panel bg-color="black" style="border-radius: 10px; border: 1px solid white;">
                <v-expansion-panel-title collapse-icon="mdi-rabbit-variant-outline" expand-icon="mdi-menu-right">
                  <svg-icon type="mdi" :path="pathChatProcessingOutline"></svg-icon>
                  <p>채팅</p>
                </v-expansion-panel-title>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col>
        </v-row>

        <!-- 영상 타이틀 -->
        <v-row>
          <v-col cols="2">
            <v-sheet>닉네임</v-sheet>
          </v-col>
          <v-col cols="2">
            <v-sheet>화상 채팅 방 제목</v-sheet>
          </v-col>
        </v-row>
      </v-col>

      
      <!-- 채팅 화면 -->
      <v-col v-if="chatBox" id="chatt" cols="3">
        <v-row class="h-100">
          <v-col><RoomChatComponent/></v-col>
        </v-row>
      </v-col>
    </v-row>
  </v-container>
  <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>



<style scoped>
#main-screen {
  background-color: rgb(167, 111, 169);
}

#screen {
  background-color: rgb(0, 0, 0);
}
#chatt {
  background-color: aqua;
}

/* 작은 화면 */
#biggBG1 {
  background-color: antiquewhite;
}
#biggBG2 {
  background-color: aquamarine;
}
#biggBG3 {
  background-color: rgb(186, 222, 176);
}
#biggBG4 {
  background-color: rgb(232, 255, 127);
}
#biggBG5 {
  background-color: rgb(255, 163, 127);
}

</style>