<script setup>
import { ref, watch, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute()
const searchQuery = ref('')

onMounted(() => {
  searchQuery.value = route.query.tag
})


watch(() => route.query.tag, (newValue) => {
  searchQuery.value = newValue
})

</script>

<template>
    <div>
        '{{ searchQuery }}' 의 검색 결과 입니다.
    </div>
</template>

<style scoped>

</style>