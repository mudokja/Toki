<script setup>
import { ref,onMounted } from 'vue'
import {Client} from '@stomp/stompjs';
const props = defineProps({user:String});
// const stompClient = new Client({
// brokerURL:'ws://192.168.31.190:8081/ws/chat',
// onConnect:()=>{
//   console.dir("연결시도");
//   stompClient.subscribe('/subChat/room',message=>console.dir("왔음",message.body));
//   stompClient.publish({destination:'/pubChat/room',body:'Frist Messsage'});
// }

// })
// const stompClient =ref(new Client());
// //stompClient.brokerURL='ws://localhost:8080/gs-guide-websocket';
const items = ref([
    { 'name': '토키1', 'content': 'good day' },
    { 'name': '토키2', 'content': 'bed day' },
])
// const chat = {
//     chatType: "COMMONCHAT",
//     sendTo: "ALL",
//     fromUser: "bb",
//     content: "내용입니다"
//   }
// //유저 테스트
// const user1 = ref('');
// const user2 = ref('');
// const changeUser=(value)=>{
// user1.value=value;
// }

// //채팅 열자마자 연결
// onMounted(() => {
  
// stompClient.value.brokerURL='wss://i10b205.p.ssafy.io/ws/chat';
// //const ws = new WebSocket('ws://192.168.31.190:8081/ws/chat');
//  connect();
// })

// const message=ref('');
// // 보내는 이미지 클릭
// const sendMessage =(msg)=>{
//   console.dir(msg)
//     if (msg.length < 100) {
//       sendName(msg);
//         clearMessage()
//     } else {
//         // 메세지 길이 100자 이상
//         clearMessage()
//     }
// }

// // x 아이콘 이미지 클릭

// //구독
// stompClient.value.onConnect = (frame) => {
//     setConnected(true);
//     console.dir('Connected: ' + frame);
//      //stompClient.subscribe('/topic/greetings', (data) => {
//         stompClient.value.subscribe('/subChat/room/1', (greeting) => {
//           console.dir("메세지왔다")
//         receiveMessage(JSON.parse(chat.name).content,JSON.parse(chat.content).content);
//         console.dir("메세지뭔데");
//     });
// };
// //에러 
// stompClient.value.onWebSocketError = (error) => {
//     console.error('Error with websocket', error);
// };

// stompClient.value.onStompError = (frame) => {
//     console.error('Broker reported error: ' + frame.headers['message']);
//     console.error('Additional details: ' + frame.body);
// };

// //통신 연결
// const connect=()=> {
//     stompClient.value.activate();
//     console.dir("연결");
// }
// //연결 해제
// const disconnect=()=> {
//     stompClient.value.deactivate();
//   //  setConnected(false);
//     console.log("Disconnected");
// }
// //메세지 보내기
// const sendName=(value1)=> {
//     // console.dir(value1);
//     // console.dir(data.value)
//     // console.dir(JSON.stringify(data.value));
//     stompClient.value.publish({
//         //destination: "/app/hello",
//         destination: "/pubChat/room/1",
//         //name: JSON.stringify(data.value.name),
//         body: JSON.stringify(value1)
//     },onMessage);
//     console.dir("보내기");
// }
// //받은 메세지 처리
// const receiveMessage=(name,msg)=> {
//     console.dir("받는 메세지");
//     items.value.push({ 'name': name, 'content': msg})
       
//     console.dir(msg);
// }
const stompClient = ref(new Client());

const chat = {
    chatType: "COMMONCHAT",
    sendTo: "ALL",
    fromUser: "bb",
    content: " "
  }
  const chatM=ref("")
  const chatU=ref("")
//채팅 열자마자 연결
const clearMessage =()=>{
    chatM.value = ''
}
onMounted(() => {
  console.log("로딩") 
  

  stompClient.value.brokerURL='wss://i10b205.p.ssafy.io/ws/chat',
  stompClient.value.onConnect=()=>{
  console.dir("연결시도");
  stompClient.value.subscribe('/subChat/room/ccc',
   message => items.value.push({ 'name': JSON.parse( message.body).fromUser, 'content': JSON.parse( message.body).content})
  //console.log(JSON.parse( message.body).content)
  );
  stompClient.value.debug((debug) => {
      console.log(debug)
    })

}
    stompClient.value.activate();
  })
//connect();
const onMessage = (message) => {
  console.log(message)
}
const test = ()=>{
  console.log("Ddd")
  chat.content=chatM.value;
  chat.fromUser=chatU.value;
  stompClient.value.publish({ destination: '/pubChat/room/ccc', body: JSON.stringify(chat) }, onMessage);
  stompClient.value.debug((debug) => {
      console.log(debug)
    })
    clearMessage();
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
              v-model="chatM"
              append-icon="mdi-send"
              prepend-icon="mdi-rabbit"
              variant="filled"
              clear-icon="mdi-close-circle"
              clearable
              label="채팅 입력"
              type="text"
              @click:append="test()"
              @click:clear="clearMessage"
              @keyup.enter="test()"
            ></v-text-field>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
    <!-- {{ items }} -->
    <input type="text" v-model="chatU" >
  </v-card>
</template>
  


<style scoped>
</style>