<script setup>
import RoomComponent from '@/components/room_components/RoomComponent.vue'
import TokiRoom from '@/components/room_components/TokiRoom.vue';
import { ref } from 'vue';
import { onMounted } from 'vue';
import { useAuthStore } from '@/stores/auth';

const authStore = useAuthStore()
const roomData = JSON.parse(sessionStorage.getItem('roomData'))

const props = defineProps(
  {
    roomPk:String,
  }
)
const userName = ref(authStore.user)
console.log(props.roomPk)
const roomInfoData = ref({roomPk:props.roomPk})
const userInfo = ref({name:null})
const enterRoom = () => {

  if (userName.value && userName.value !== '') {
    userInfo.value.name=userName.value
    isRoomEnter.value=false
  }

}

const isRoomEnter=ref(true)

onMounted(() => {
  console.log(roomData)
})

</script>

<template>
   <v-dialog
    width="auto"
    :style="{backgroundColor: 'white'}"
    v-model="isRoomEnter">
          <div :style="{width:'10rem'}">
            <v-card-title>
              <span>입장 하기</span>
            </v-card-title>
            <v-form
            @submit.prevent="enterRoom">
            <v-text-field
            
            class="mb-2"
            clearable

            label="userName"
            >{{ userName }}</v-text-field>

            <!-- <v-text-field
              v-model="password"
              :type="'password'"
              clearable
              label="Password"
              ></v-text-field> -->
            </v-form>
            <v-card-actions>
              <v-btn color="primary" block @click="isRoomEnter = false">취소</v-btn>
            </v-card-actions>
          </div>
          </v-dialog>
    <div class="room-view">
      <TokiRoom v-if="userInfo&&userInfo.name!==null" :roomInfo="roomInfoData" :userInfo="userInfo"/>
    </div>
</template>

<style scoped>
.room-view {
  height: 100vh;
  width: 100vw;
}
</style>
