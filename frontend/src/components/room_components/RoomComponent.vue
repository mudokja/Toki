<script setup>
import { computed, onMounted, onUnmounted, reactive, ref } from 'vue'
import SvgIcon from '@jamescoyle/vue-icon'
import { mdiMicrophone, mdiVideo, mdiMonitorShare, mdiRadioboxMarked, mdiEmoticonOutline, mdiCog, mdiChatProcessingOutline } from '@mdi/js'
import RoomGameModal from '../modal/RoomGameModal.vue'
import RoomChatComponent from '@/components/room_components/RoomChatComponent.vue'
// 아이콘 변수
// const pathMicrophone = ref(mdiMicrophone )
const pathVideo = ref(mdiVideo)
const pathMonitorShare = ref(mdiMonitorShare)
const pathRadioboxMarked = ref(mdiRadioboxMarked)
const pathEmoticonOutline = ref(mdiEmoticonOutline)
const pathCog = ref(mdiCog)
const pathChatProcessingOutline  = ref(mdiChatProcessingOutline)

// 채팅 확장
const chatBox = ref(false)


const menuMicOpen = ref(false)
const menuVideoOpen = ref(false)
const menuShareOpen = ref(false)
const menuRecordOpen = ref(false)
const menuEmoticonOpen = ref(false)
const menuSettingOpen = ref(false)
const menuChatOpen = ref(false)

const roomGameDialog = ref(false)

const openGameDialog = () => {
  roomGameDialog.value = !roomGameDialog.value
}

// const menuState = reactive({
//   mic: false,
//   video: false,
//   share: false,
//   record: false,
//   emoticon: false,
//   setting: false,
//   chat: false,

// })

const screenWidth = ref(window.innerWidth)

function handleResize() {
  screenWidth.value = window.innerWidth
}

onMounted(() => {
  window.addEventListener('resize', handleResize)
})

onUnmounted(() => {
  window.removeEventListener('resize', handleResize)
})

const isLagerScreen = computed(() => screenWidth.value >= 1280)

const colSize = computed(() => isLagerScreen.value ? 2 : 1)
const colOffset = computed(() => isLagerScreen.value ? 0 : 1)

</script>
<template>
  <v-container id="enter" class="h-100" style="min-width: 600px">
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
        <v-row class="mt-1 ml-1 mr-1" >
          
          <!-- 마이크 설정-->
          <!-- <v-col>
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
          </v-col> -->

          <v-col
            justify="center"
            :cols="colSize"
            :offset="colOffset"
          >
            <div class="mx-4 hidden-sm-and-down"></div>

            <v-menu location="top" v-model="menuMicOpen">
              <template v-slot:activator="{ props }">
                <v-btn
                  v-if="isLagerScreen"
                  prepend-icon="mdi-microphone"
                  :append-icon="menuMicOpen ? 'mdi-menu-down' : 'mdi-menu-up'"
                  color="black"
                  class="ma-2 "
                  v-bind="props"
                  size="x-large"
                  style="border-radius: 30px; border: 1px solid white;"
                >
                  마이크
                </v-btn>
                <v-btn
                  v-else
                  class="ma-2"
                  color="black"
                  icon="mdi-microphone"
                  v-bind="props"
                  width="100%"
                  size="large"
                  style="border: 1px solid white;"
                >

                </v-btn>
              </template>
              <v-list bg-color="black" style="text-align: center;">
                <v-list-item 
                  link 
                  value="마이크-on-off"
                >
                  마이크 on/off
                </v-list-item>

                <v-list-item 
                  link 
                  value="음성변조"
                >
                  음성 변조
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>


          <!-- 캠 설정 -->
          <!-- <v-col>
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
          </v-col> -->

          <v-col
            justify="center"
            :cols="colSize"
            :offset="colOffset"
          >
            <div class="mx-4 hidden-sm-and-down"></div>

            <v-menu location="top" v-model="menuVideoOpen">
              <template v-slot:activator="{ props }">
                <v-btn
                  v-if="isLagerScreen"
                  prepend-icon="mdi-video"
                  :append-icon="menuVideoOpen ? 'mdi-menu-down' : 'mdi-menu-up'"
                  color="black"
                  class="ma-2 "
                  v-bind="props"
                  size="x-large"
                  style="border-radius: 30px; border: 1px solid white;"
                >
                  캠
                </v-btn>
                <v-btn
                  v-else
                  class="ma-2"
                  color="black"
                  v-bind="props"
                  icon="mdi-video"
                  size="large"
                  style="border: 1px solid white;"
                >

                </v-btn>
              </template>
              <v-list bg-color="black" style="text-align: center;">
                <v-list-item link value="캠-on-off">
                  캠 on/off
                </v-list-item>

                <v-list-item link value="가상배경">
                  가상 배경
                </v-list-item>

                <v-list-item link value="버츄얼">
                  버츄얼 적용
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>

          <!-- 화면 공유 -->
          <!-- <v-col>
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
          </v-col> -->

          <v-col
            justify="center"
            :cols="colSize"
            :offset="colOffset"
          >
            <div class="mx-4 hidden-sm-and-down"></div>

            <v-menu location="top" v-model="menuShareOpen">
              <template v-slot:activator="{ props }">
                <v-btn
                  v-if="isLagerScreen"
                  prepend-icon="mdi-monitor-share"
                  :append-icon="menuShareOpen ? 'mdi-menu-down' : 'mdi-menu-up'"
                  color="black"
                  class="ma-2 "
                  v-bind="props"
                  size="x-large"
                  style="border-radius: 30px; border: 1px solid white;"
                >
                  공유
                </v-btn>
                <v-btn
                  v-else
                  class="ma-2"
                  color="black"
                  v-bind="props"
                  icon="mdi-monitor-share"
                  size="large"
                  style="border: 1px solid white;"
                >

                </v-btn>
              </template>
              <v-list bg-color="black" style="text-align: center;">                  
                <v-list-item value="화면공유">
                  화면 공유하기
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>


          <!-- 녹화 -->
          <!-- <v-col>
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
          </v-col> -->

          <v-col
            justify="center"
            :cols="colSize"
            :offset="colOffset"
          >
            <div class="mx-4 hidden-sm-and-down"></div>

            <v-menu location="top" v-model="menuRecordOpen">
              <template v-slot:activator="{ props }">
                <v-btn
                  v-if="isLagerScreen"
                  prepend-icon="mdi-radiobox-marked"
                  :append-icon="menuRecordOpen ? 'mdi-menu-down' : 'mdi-menu-up'"
                  color="black"
                  class="ma-2 "
                  v-bind="props"
                  size="x-large"
                  style="border-radius: 30px; border: 1px solid white;"
                >
                  녹화
                </v-btn>
                <v-btn
                  v-else
                  class="ma-2"
                  color="black"
                  v-bind="props"
                  icon="mdi-radiobox-marked"
                  size="large"
                  style="border: 1px solid white;"
                >

                </v-btn>
              </template>
              <v-list bg-color="black" style="text-align: center;">                  
                <v-list-item value="화면녹화">
                  화면 녹화
                </v-list-item>
              </v-list>
            </v-menu>
          </v-col>


          <!-- 이모티콘 -->
          <!-- <v-col cols="1" sm="2">
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
          </v-col> -->

          <v-col
            justify="center"
            cols="1"
          >
            <div class="mx-4 hidden-sm-and-down"></div>

            <v-menu location="top" v-model="menuEmoticonOpen">
              <template v-slot:activator="{ props }">
                <v-btn
                  class="ma-2"
                  color="black"
                  v-bind="props"
                  icon="mdi-emoticon-outline"
                  size="large"
                  style="border: 1px solid white;"
                >

                </v-btn>
              </template>              
              <v-list bg-color="black" style="text-align: center;">                  
                <v-list-item-group multiple>
                  <v-row>
                    <v-col cols="12">
                      <v-chip-group row>
                        <v-chip value="화면녹화">😀</v-chip>
                        <v-chip value="화면녹화">😀</v-chip>
                        <v-chip value="화면녹화">😀</v-chip>
                        <v-chip value="화면녹화">😀</v-chip>
                      </v-chip-group>
                    </v-col>
                  </v-row>
                  <v-row>
                    <v-col cols="12">
                      <v-chip-group row>
                        <v-chip value="화면녹화">😀</v-chip>
                        <v-chip value="화면녹화">😀</v-chip>
                        <v-chip value="화면녹화">😀</v-chip>
                        <v-chip value="화면녹화">😀</v-chip>
                      </v-chip-group>
                    </v-col>
                  </v-row>
                  <!-- <v-chip value="화면녹화">😀</v-chip>
                  <v-chip value="화면녹화">😀</v-chip>
                  <v-chip value="화면녹화">😀</v-chip>
                  <v-chip value="화면녹화">😀</v-chip>
                  <v-chip value="화면녹화">😀</v-chip> -->
                </v-list-item-group>
              </v-list>
            </v-menu>
          </v-col>

          <!-- 환경 설정 -->
          <!-- <v-col>
            <v-expansion-panels>
              <v-expansion-panel bg-color="black" style="border-radius: 10px; border: 1px solid white;">
                <v-expansion-panel-title hide-actions>
                  <svg-icon type="mdi" :path="pathCog"></svg-icon>
                  <p class="ml-5">설정</p>
                </v-expansion-panel-title>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col> -->
          <v-col
            justify="center"
            cols="1"
          >
            <div class="mx-4 hidden-sm-and-down"></div>

            <v-menu location="top" v-model="menuSettingOpen">
              <template v-slot:activator="{ props }">
                <v-btn
                  class="ma-2"
                  color="black"
                  v-bind="props"
                  icon="mdi-cog"
                  size="large"
                  style="border: 1px solid white;"
                >

                </v-btn>
              </template>
              <v-list bg-color="black" style="text-align: center; width: 250px; white-space: nowrap;">   
                <v-list-item 
                  prepend-icon="mdi-gamepad-variant"
                  style="margin-left: 10px;"
                  value="게임"
                  @click="openGameDialog"
                >
                  게임
                </v-list-item>
                
                <v-list-item 
                  prepend-icon="mdi-account-group"
                  style="margin-left: 10px;"
                  value="소회의실"
                >
                  소회의실
                </v-list-item>
                <v-list-item 
                  prepend-icon="mdi-vote"
                  style="margin-left: 10px;"
                  value="투표"
                >
                  투표
                </v-list-item>
                <v-list-item 
                  prepend-icon="mdi-send"
                  style="margin-left: 10px;"
                  value="초대"
                >
                  초대
                </v-list-item>               
                <v-list-item 
                  prepend-icon="mdi-cog"
                  style="margin-left: 10px;"
                  value="환경설정"
                >
                  환경 설정
                </v-list-item>
              </v-list>
              
            </v-menu>
          </v-col>
          
          
          <!-- 채팅 -->
          <v-col
            justify="center"
            :cols="colSize"            
          >
            <!-- <div class="mx-4 hidden-sm-and-down"></div> -->
          
            <v-btn
              v-if="isLagerScreen"
              prepend-icon="mdi-chat-processing-outline"
              color="black"
              class="ma-2 "
              v-bind="props"
              size="x-large"
              style="border-radius: 10px; border: 1px solid white;"
              @click="chatBox = !chatBox"
            >
              채팅
            </v-btn>
              
            <v-btn
              v-else
              class="ma-2"
              color="black"
              v-bind="props"
              icon="mdi-chat-processing-outline"
              size="large"
              style="border-radius: 10px; border: 1px solid white;"
              
            >

            </v-btn>
              

            
          </v-col>


          <!-- <v-col>
            <v-expansion-panels @click="chatBox = !chatBox">
              <v-expansion-panel bg-color="black" style="border-radius: 10px; border: 1px solid white;">
                <v-expansion-panel-title collapse-icon="mdi-rabbit-variant-outline" expand-icon="mdi-menu-right">
                  <svg-icon type="mdi" :path="pathChatProcessingOutline"></svg-icon>
                  <p>채팅</p>
                </v-expansion-panel-title>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-col> -->
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



    <!-- Modal 화면 -->
    <RoomGameModal 
      :roomGameDialog="roomGameDialog"
      @update:roomGameDialog="roomGameDialog = $event"
    />

  </v-container>
  <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>



<style scoped>
#main-screen {
  background-color: rgb(167, 111, 169);
}

#screen {
  background-color: rgb(0, 0, 0);
  white-space: nowrap;
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