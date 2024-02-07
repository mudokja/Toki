<script setup>
import { inject } from 'vue';

const drawerState = inject('drawerState')

const category = ['게임', '공부', '스포츠', '잡담', '음악', '자유(기타)', ]







</script>

<template>
    <v-navigation-drawer
        v-model="drawerState"
        location="right"

        permanent
    >

    <div style="display: flex; align-items: center; margin-top: 10px; margin-bottom: 10px;">
      <v-btn
      variant="text"
      icon="mdi-chevron-right"
      @click.stop="drawerState = !drawerState"
      
      >
      </v-btn>
        <img src="@/assets/profile_assets/프로필.png" style="width: 40px; height: 40px; border-radius: 50%; margin-left: 1px;">
        <div>
            <h4 style="margin-left: 10px;">5반 유저</h4>
        </div>


    </div>

    <v-divider></v-divider>

    <v-list density="compact" nav>
      <v-list-item value="home">
        <div style="display: flex; align-items: center;">
          <img src="@/assets/profile_assets/토키 로고.png" alt="토키 로고" style="width: 45px; margin-right: 12px; margin-left: -8px;">
          <div>
            <h5>home</h5>
          </div>

        </div>
      </v-list-item>
      <v-list-item value="fav">
        <div style="display: flex; align-items: center;">
          <img src="@/assets/profile_assets/즐겨찾기.png" alt="즐겨찾기" style="width: 30px; height: 30px; margin-right: 18px;">
          <div>
            <h5>추천 동영상</h5>
          </div>

        </div>
      </v-list-item>
      <v-list-item value="all">
        <div style="display: flex; align-items: center;">
          <img src="@/assets/profile_assets/비디오.png" alt="전체" style="width: 50px; margin-right: 8px; margin-left: -10px;">
          <div>
            <h5>전체</h5>
          </div>

        </div>
        
      </v-list-item>
      <v-list-item value="category" style="height: 10px;">
        <div style="display: flex; align-items: center;">
          <img src="@/assets/profile_assets/카테고리.png" alt="카테고리" style="width: 35px; margin-right: 15px; margin-left: 0px;">
          <v-select
          class="mt-3"
          clearable
          density="compact"
          :items="category"
          variant="plain"
          
          ></v-select>

        </div>
        
      </v-list-item>
      <!-- <v-list-item prepend-icon="mdi-forum" title="About" value="about"></v-list-item> -->
    </v-list>
    </v-navigation-drawer>


</template>

<style scoped>
.v-navigation-drawer {
  z-index: 2400; 
}
</style>
