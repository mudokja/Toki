<script setup>
import { ref, onMounted } from 'vue';
import HeaderView from '@/views/HeaderView.vue';
import FooterView from '@/views/FooterView.vue';
import MyCard from '@/components/MyCard.vue';

const items = ref(Array.from({ length: 48}, (_, v) => v + 1))


const api = async () => {
  return new Promise(resolve => {
    setTimeout(() => {
      const lastItem = items.value.length ? items.value[items.value.length - 1] : 0;
      resolve(Array.from({ length: 12 }, (_, v) => v + lastItem + 1));
    }, 1000);
  });
};

const load = async ({ done }) => {
  const res = await api();
  items.value.push(...res);
  done('ok');
};

onMounted(() => {
  console.log(items)
})

</script>

<template>
  <v-app>
    <HeaderView/>

    <v-container 
      fluid 
      style="margin-top: 80px; padding-top: 60px; margin-bottom: 200px;"
    >
      <v-infinite-scroll
        :items="items"
        :onLoad="load"
      >
        <v-row>

          <v-col 
            v-for="(item, index) in items"
            :key="item" 
            cols="12" sm="6" md="4" lg="3">
            <my-card 
              :item="item"
              :index="index"
              >
            </my-card>
            
          </v-col>
        </v-row>
        
      </v-infinite-scroll>
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