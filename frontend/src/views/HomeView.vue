<script setup>
import { ref, onMounted } from 'vue';
import HeaderView from '@/views/HeaderView.vue';
import FooterView from '@/views/FooterView.vue';
import MyCard from '@/components/MyCard.vue';

const items = ([]) // 초기 아이템
const loading = ref(false);

const loadMoreItems = () => {
  if (loading.value) return;
  loading.value = true;

  // 더 많은 아이템을 가져와서 'items'에 추가
  // ...

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
          v-for="n in 40"
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
</template>
<style>
.content {
  margin-top: 80px;
  margin-bottom: auto;
}
</style>