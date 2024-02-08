<script setup>
import { ref,onMounted } from 'vue'
import {Client} from '@stomp/stompjs';
const stompClient = new Client;
// stompClient.brokerURL='ws://localhost:8080/gs-guide-websocket';
stompClient.brokerURL='ws://ib205.p.ssafy.io/ws/chat';
const items = ref([
    { 'name': '토키1', 'content': 'good day' },
    { 'name': '토키2', 'content': 'bed day' },
])
const data = ref({
  name:"",
  body:""
})
//유저 테스트
const user1 = ref('');
const user2 = ref('');
const changeUser=(value)=>{
user1.value=value;
}

//채팅 열자마자 연결
onMounted(() => {
 connect();
})

const message=ref('');
// 보내는 이미지 클릭
const sendMessage = function(msg){
    if (msg.length < 100) {
      sendName(msg);
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
//메세지 받으면
stompClient.onConnect = (frame) => {
    //setConnected(true);
    console.log('Connected: ' + frame);
     //stompClient.subscribe('/topic/greetings', (data) => {
        stompClient.subscribe('/pubChat', (greeting) => {
        receiveMessage(JSON.parse(data.name).content,JSON.parse(data.body).content);
        console.dir("메세지뭔데");
    });
};
//에러 
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

//통신 연결
const connect=()=> {
    stompClient.activate();
    console.dir("연결");
}
//연결 해제
const disconnect=()=> {
    stompClient.deactivate();
  //  setConnected(false);
    console.log("Disconnected");
}
//메세지 보내기
const sendName=(value1)=> {
    console.dir(value1);
    console.dir(data.value)
    console.dir(JSON.stringify(data.value));
    stompClient.publish({
        //destination: "/app/hello",
        destination: "/subChat",
        //name: JSON.stringify(data.value.name),
        body: JSON.stringify(data.value)
    });
    console.dir("보내기");
}
//받은 메세지 처리
const receiveMessage=(name,msg)=> {
    console.dir("받는 메세지");
    items.value.push({ 'name': name, 'content': msg})
       
    console.dir(msg);
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
              v-model="data.body"
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
    <input type="text" v-model="data.name" >
  </v-card>
</template>
  


<style scoped>
</style>