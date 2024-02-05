<script setup>
import { commonaxios,commonpostaxios } from '@/js/CommonAxios';
import adapter from 'webrtc-adapter';
import kurentoUtils from 'kurento-utils';
import {register ,leaveRoom,onExistingParticipants,onNewParticipant,onParticipantLeft,receiveVideoResponse} from './WebRTC'
import { Participant } from './Participant';
import { ref,watch,onMounted } from 'vue';
const { VITE_VUE_API_URL, VITE_ELECTRIC_CHARGING_STATION_URL } = import.meta.env;
const ws = new WebSocket('wss://192.168.31.237:8443/groupcall');
const data=ref({
name:"안녕",
room:"하하",
commit:"가냐",
})
function sendText(message){
	var jsonMessage = JSON.stringify(message);
	console.log('텍스트: ' + jsonMessage);
	ws.send(jsonMessage);
	console.log('텍스트: ' + jsonMessage);
}
const message=ref({
	name:"안녕",
	room:"하하",
	message:"안녕하세요",
})
const click1=()=>{
// commonaxios(({data})=>{
// console.dir(data)
// },(error)=>{
// 	console.dir(error)
// })//
sendText(message.value.message);

}
ws.onmessage=(message)=>{
	let parsedMessage = JSON.parse(message.data);
	console.dir("온건바로",parsedMessage);
}
const click2=()=>{
	
// commonpostaxios(data.value,(response)=>{
// console.dir(response)
// },(error)=>{
// 	console.dir(error)
// })
}
const click3=()=>{
	//let ws = new WebSocket('wss://localhost:8443/groupcall');
	register(data.value);
	
}
const click4=()=>{
	leaveRoom();
}
onMounted(()=>{
	
	console.dir("지금");
	//register(data.value);
	//click3();
})
// watch((message)=>{
// 	ws.onmessage = function(message) {
// 	var parsedMessage = JSON.parse(message.data);
// 	console.info('Received message: ' + message.data);
// 	console.dir("여기온 메세지"+message);
// 	switch (parsedMessage.id) {
// 	case 'existingParticipants':
// 		onExistingParticipants(parsedMessage);
// 		break;
// 	case 'newParticipantArrived':
// 		onNewParticipant(parsedMessage);
// 		break;
// 	case 'participantLeft':
// 		onParticipantLeft(parsedMessage);
// 		break;
// 	case 'receiveVideoAnswer':
// 		receiveVideoResponse(parsedMessage);
// 		break;
// 	case 'iceCandidate':
// 		participants[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
// 	        if (error) {
// 		      console.error("Error adding candidate: " + error);
// 		      return;
// 	        }
// 	    });
// 	    break;
// 	default:
// 		console.error('Unrecognized message', parsedMessage);
// 	}
// }
// })
</script>

<template>
	
    <div >
        <video src="" width="200" height="200" id="display"></video>
    </div>
    안녕하세여<button @click="click1">get버튼</button><button @click="click2">post버튼</button><button @click="click3">보내기 버튼</button><button @click="click4">떠나기 버튼</button>
<body>
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