<template>
  <v-row justify="end">
    <v-dialog v-model="dialog" scrollable width="auto">
      <template v-slot:activator="{ props }">
        <v-btn color="primary" v-bind="props">친구 추가</v-btn>
      </template>
      <v-card style="width: 300px;">
        <v-card-title>유저 검색</v-card-title>

        <!-- 검색 창 -->
        <v-card-text>
          <v-text-field
            :loading="loading"
            density="compact"
            variant="solo"
            label="유저 찾기"
            append-inner-icon="mdi-magnify"
            single-line
            hide-details
            @click:append-inner="onSearch"
            @keyup.enter="onSearch"
          ></v-text-field>
        </v-card-text>    

        <v-divider></v-divider>

        <!-- 닉네임 유저 조회 창 -->
        <v-list density="compact">
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            :value="item"
            color="primary"
            @click="dialogData=item.text"
          >
            <template v-slot:prepend>
              <v-icon :icon="item.icon"></v-icon>
            </template>

            <v-list-item-title v-text="item.text"></v-list-item-title>
          </v-list-item>
          <v-divider></v-divider>
          
          <v-card-actions>
            <v-btn color="blue-darken-1" variant="text" @click="dialog = false">
              닫기
            </v-btn>
            <v-btn color="blue-darken-1" variant="text" @click="dialog = false">
              친구 추가
            </v-btn>
          </v-card-actions>
        </v-list>
      </v-card>
    </v-dialog>
  </v-row>
  <link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
</template>

<script setup>
import { ref } from 'vue'

const dialogData = ref('')
const dialog = ref(false)
const items = ref([
  { text: '아이디', icon: 'mdi-account' },
  { text: '아이디 닉네임', icon: 'mdi-account' },
  { text: '아이디 or 닉네임', icon: 'mdi-account' },
])

const loaded = ref(false)
const loading = ref(false)
const onSearch = function() {
    loading.value = true

    setTimeout(() => {
        loading.value = false
        loaded.value = true
    }, 2000)
}


</script>