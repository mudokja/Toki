<script setup>
import { commonaxios,commonpostaxios } from '@/js/CommonAxios';
import adapter from 'webrtc-adapter';
import kurentoUtils from 'kurento-utils';
import {register ,leaveRoom,onExistingParticipants,onNewParticipant,onParticipantLeft,receiveVideoResponse,sendMessage} from './WebRTC'
import { Participant } from './Participant';
import { ref,watch,onMounted } from 'vue';
import {Client} from '@stomp/stompjs';
import {sendName,disconnect,connect,showGreeting} from './WebChat'
import { commonaxios1 } from '@/js/Click';
//const ws = new WebSocket('wss://192.168.31.237:8443/groupcall');
// const ws = new WebSocket('wss://localhost:8443/groupcall');
//let ws = new WebSocket('ws://localhost:8080/gs-guide-websocket');
const ws = new WebSocket('wss://i10b205.p.ssafy.io/ws/room');
//  const stompClient = new Client({

// 	brokerURL:'ws://localhost:8080/gs-guide-websocket',
//  })
 const text4=ref("");
 const data=ref({
name:'',
room:"하하",
commit:"가냐",
})
const soclick=()=>{
connect();
}
const soclick1=()=>{
	disconnect();
}
const soclick2=(te)=>{
	text4.value=te;
	console.dir(te);
	sendName(text4.value);
}
const soclick3=(te)=>{
	data.value.name=te;
	console.dir(te);
	console.dir(data);
}

// function sendText(message){
// 	var jsonMessage = JSON.stringify(message);
// 	console.log('텍스트: ' + jsonMessage);
// 	ws.onopen=()=>ws.send(jsonMessage);
// 	console.log('텍스트: ' + jsonMessage);
// }
const click1=()=>{
	//채팅
getMessage();

}
const click2=()=>{
	//채팅 확인
	console.dir(text4.value);
}
const click3=()=>{
	
	register(data.value);
	
}
const click4=()=>{
	leaveRoom(); 
	
}
onMounted(()=>{
	

	console.dir("지금");
	//ws.onopen=()=>register(data.value);
	//click3();
})

</script>

<template>
<button @click="soclick">connect</button>
<button @click="soclick1">disconnect</button>
<div>메세지<input type="text" v-model="text" v-on:keyup.enter="soclick2(text)">
</div>
	<button @click="click1">메세지 가져오기 버튼</button>
	<button @click="click2">메세지 확인 버튼</button>
	<div>이름<input type="text" v-model="text1" v-on:keyup.enter="soclick3(text1)">
	</div><button @click="click3">보내기 버튼</button>
	<button @click="click4">떠나기 버튼</button>
<body>
	<div>
		<input type="text" name="userName" id="userName" v-model="data.name">
		<div><input type="text" name="tokiroom" id="tokiroom" v-model="data.room"></div>
	</div>
	<div id="container">
		<div id="wrapper">
			<div id="join" class="animate join">
				<h1>Join a Room</h1>
				
			</div>
			<div id="room" style="display: none;">
				<h2 id="room-header"></h2>
				<div id="participants"></div>
				<input type="button" id="button-leave" @click="click4"
					value="Leave room">
			</div>
		</div>
	</div>
</body>
</template>

<style scoped>
#display{
    background-color: aqua;
}
</style>