<script setup>
import RoomComponent from '@/components/room_components/RoomComponent.vue'
import { onMounted } from 'vue';

const roomData = JSON.parse(sessionStorage.getItem('roomData'))


onMounted(() => {
  console.log(roomData)
})

</script>

<template>
    <div class="room-view">
      <RoomComponent/>
    </div>
</template>

<style scoped>
.room-view {
  height: 100vh;
  width: 100vw;
}
</style>
