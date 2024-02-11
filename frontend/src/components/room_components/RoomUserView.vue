<script setup>
import { ref, onMounted } from 'vue';
const props = defineProps({
  user: Object,
  
})

</script>

<template>
  <div id="paripant-container" width="200px" height="200px">
    <slot name="video"></slot>
    <span></span>
  </div>
</template>

<style scoped>

</style>