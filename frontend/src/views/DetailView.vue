<script setup>
import { ref } from 'vue';
import HeaderView from '@/views/HeaderView.vue';
import FooterView from '@/views/FooterView.vue';
import MyCard from '@/components/MyCard.vue';



// 데이터 상태 변수
const items = ref(Array.from({ length: 48 }, (_, v) => v + 1));




// 비동기 API 호출 함수
const api = async () => {
  return new Promise(resolve => {
    setTimeout(() => {
      // 마지막 항목 값 계산
      const lastItem = items.value.length ? items.value[items.value.length - 1] : 0;
      // 새로운 데이터 생성 및 반환
      resolve(Array.from({ length: 12 }, (_, v) => v + lastItem + 1));
    }, 1000);
  });
};

// 데이터 로드 함수
const load = async ({ done }) => {
  // API 호출하여 데이터 가져오기
  const res = await api();
  // 가져온 데이터를 items 배열에 추가
  items.value.push(...res);
  // 로드 완료 신호 전달
  done('ok');
};

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
            cols="12" sm="6" md="4" lg="3"
          >

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