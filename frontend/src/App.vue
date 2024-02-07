<script setup>
import { useRoute } from 'vue-router';
import HeaderView from './views/HeaderView.vue';
import FooterView from './views/FooterView.vue';
import { computed } from 'vue';


const route = useRoute()
const isRoomView = computed(() => route.name === 'room')

</script>

<template>
  <v-app>
    <HeaderView v-if="!isRoomView" />
    <RouterView />
    <v-app>
      <FooterView v-if="!isRoomView" />
    </v-app>
  </v-app>
  <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>

<style scoped>
.content {
  margin-top: 80px;
}
</style>