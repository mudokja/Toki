<script setup>
import { ref } from 'vue'

const items = ref([
    { 'name': '토키1', 'content': 'good day' },
    { 'name': '토키2', 'content': 'bed day' },
])

const message = ref('')

// 보내는 이미지 클릭
const sendMessage = function(msg){
    if (msg.length < 100) {
        items.value.push({ 'name': '토키3', 'content': msg})
        clearMessage()
    } else {
        // 메세지 길이 100자 이상
        clearMessage()
    }
}

// x 아이콘 이미지 클릭
const clearMessage = function(){
    message.value = ''
}

</script>

<template>
  <v-card class="mx-auto h-100" max-width="500">
    <v-virtual-scroll :items="items" item-height="48" class="h-75">
      <template v-slot:default="{ item }">
        <v-list-item :title="`채팅 내용 : ${item.content}`" :subtitle="`닉네임 : ${item.name}`">
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
    <!-- {{ items }} -->
  </v-card>
</template>
  


<style scoped>
</style>