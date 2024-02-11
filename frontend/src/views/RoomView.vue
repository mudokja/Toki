<script setup>
import RoomComponent from '@/components/room_components/RoomComponent.vue'
import TokiRoom from '@/components/room_components/TokiRoom.vue';
import { defineProps, onMounted } from 'vue';

const roomData = JSON.parse(sessionStorage.getItem('roomData'))

const roomInfoData={
  roomPk:"ccc"
}
const userInfo={
  name:"user1"
}

onMounted(() => {
  console.log(roomData)
})

</script>

<template>
    <div class="room-view">
      <TokiRoom :roomInfo="roomInfoData" :userInfo="userInfo"/>
    </div>
</template>

<style scoped>
.room-view {
  height: 100vh;
  width: 100vw;
}
</style>
