<script setup>
import { ref, watch } from 'vue'

const items = ref([
    { 'name': '토키1', 'content': 'good day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키3', 'content': '1 day' },
    { 'name': '토키4', 'content': 'day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
    { 'name': '토키2', 'content': 'bed day' },
])

const message = ref('')

// 메세지 보내기 클릭
const sendMessage = function(msg){
    if (1 < msg.length < 100) {
      items.value.push({ 'name': '토키3', 'content': msg})
      clearMessage()
    } else if (msg.length === 0) {
       // 메세지 없을 때.
       clearMessage()
    } else {
      // 메세지 100자 이상 일 때.
      clearMessage()
    }
}

// x 아이콘 이미지 클릭
const clearMessage = function(){
    message.value = ''
}

// 채팅 스크롤을 가장 아래로 이동
const virtualScroll = ref(null)
const handleScroll = () => {
  virtualScroll.value.$el.scrollTop = virtualScroll.value.$el.scrollHeight;
}

// 채팅 데이터 변화될 때, 모니터링
watch(items.value, () => {
  handleScroll()
})
</script>

<template>
  <v-card class="mx-auto" max-width="500">
    <v-card-title> 채팅 </v-card-title>

    <v-divider></v-divider>

    <v-virtual-scroll :items="items" height="1200" item-height="48" ref="virtualScroll">
      <template v-slot:default="{ item }">
        <v-list-item >
          <v-list-item-title style="white-space: pre-wrap; word-wrap: break-word;">{{ item.content }}</v-list-item-title>
          <v-list-item-subtitle>닉네임 : {{ item.name }}</v-list-item-subtitle>
          <template v-slot:prepend>
            <v-icon class="bg-primary">mdi-rabbit</v-icon>
          </template>
        </v-list-item>
        
      </template>
    </v-virtual-scroll>

    <v-divider></v-divider>
    
    <v-form @submit.prevent>
      <v-container>
        <v-row>
          <v-col cols="12">
            <v-text-field
              v-model="message"
              append-icon="mdi-send"
              prepend-icon="mdi-rabbit"
              variant="filled"
              clear-icon="mdi-close-circle"
              clearable
              label="채팅 입력"
              type="text"
              @click:append="sendMessage(message)"
              @click:clear="clearMessage"
              @keyup.enter="sendMessage(message)"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-card>
  <v-btn @click="handleScroll"></v-btn>
</template>
  


<style scoped>
</style>