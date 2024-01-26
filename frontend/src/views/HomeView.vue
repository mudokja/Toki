<script setup>
import { ref, onMounted } from 'vue';
import HeaderView from '@/views/HeaderView.vue';
import FooterView from '@/views/FooterView.vue';
import MyCard from '@/components/MyCard.vue';

const items = ([])
const loading = ref(false);

const loadMoreItems = () => {
  if (loading.value) return;
  loading.value = true;

  loading.value = false;
};

onMounted(() => {
  window.addEventListener('scroll', () => {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
      loadMoreItems();
    }
  });
});

</script>

<template>
  <v-app>
    <HeaderView/>

    <v-container fluid style="margin-top: 80px; padding-top: 60px; margin-bottom: 200px;">
      <v-row>
        <v-col 
          v-for="n in 12"
          :key="n" 
          cols="12" sm="6" md="4" lg="3">
          <my-card></my-card>
        </v-col>
      </v-row>
    </v-container>



    <FooterView/>
  </v-app>
  <main>
  </main>
  <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>
<style>
.content {
  margin-top: 80px;
  margin-bottom: auto;
}

</style>