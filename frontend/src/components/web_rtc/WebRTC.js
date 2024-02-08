import { Participant } from './Participant';
//const kurentoUtils = require('kurento-utils');
import kurentoUtils from 'kurento-utils';
import adapter from 'webrtc-adapter';
import UserDisplayVue from './UserDisplay.vue';
adapter
let participants = {};
let name;
//const ws = new WebSocket('wss://i10b205.p.ssafy.io/ws/room');
const ws = new WebSocket('wss://localhost:8443/groupcall');
 window.onbeforeunload = function() {
	ws.close();
};
ws.onmessage =(message)=> {
	let parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);
	console.dir("여기온 메세지"+message);
	console.dir(ws.CONNECTING);
	switch (parsedMessage.id) {
	case 'existingParticipants':
		onExistingParticipants(parsedMessage);
		break;
	case 'newParticipantArrived':
		onNewParticipant(parsedMessage);
		break;
	case 'participantLeft':
		onParticipantLeft(parsedMessage);
		break;
	case 'receiveVideoAnswer':
		receiveVideoResponse(parsedMessage);
		break;
	case 'iceCandidate':
		participants[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
	        if (error) {
		      console.error("Error adding candidate: " + error);
		      return;
	        }
	    });
	    break;
	default:
		console.error('Unrecognized message', parsedMessage);
	}
}

const register=(data)=> {///////////////////////////1방 이름이랑 생성함
	 
	name = data.name;
	let room = data.room;
	document.getElementById('room-header').innerText = 'ROOM ' + room;
	document.getElementById('join').style.display = 'none';
	document.getElementById('room').style.display = 'block';
	console.dir("이름이",name);
	let message = {///////////////메세지 만들고
		id : 'joinRoom',
		name : name,
		room : room,
	}
	sendMessage(message);//////////////////보냄
}

function onNewParticipant(request) {
	receiveVideo(request.name);
}

function receiveVideoResponse(result) {
	participants[result.name].rtcPeer.processAnswer (result.sdpAnswer, function (error) {
		if (error) return console.error (error);
	});
}

function callResponse(message) {
	if (message.response != 'accepted') {
		console.info('Call not accepted by peer. Closing call');
		stop();
	} else {
		webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
			if (error) return console.error (error);
		});
	}
}

function onExistingParticipants(msg) {
	let constraints = {
		audio : true,
		video : {
			mandatory : {
				maxWidth : 320,
				maxFrameRate : 15,
				minFrameRate : 15
			}
		}
	};

	console.log(name + " registered in room " + room);
	let participant = new Participant(name);
	participants[name] = participant;
	let video = participant.getVideoElement();
	let options = {
	      localVideo: video,
	      mediaConstraints: constraints,
	      onicecandidate: participant.onIceCandidate.bind(participant)
	    }
		participant.rtcPeer =new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
		function (error) {
		  if(error) {
			  return console.error(error);
		  }
		  this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});
console.dir("비디오값"+options.localVideo);
	msg.data.forEach(receiveVideo);
}

function leaveRoom() {
	sendMessage({
		id : 'leaveRoom'
	});

	for ( let key in participants) {
		participants[key].dispose();
	}

	ws.close();
}

function receiveVideo(sender) {
	let participant = new Participant(sender);
	console.dir("비디오는 되냐")
	participants[sender] = participant;
	let video = participant.getVideoElement();

	let options = {
      remoteVideo: video,
      onicecandidate: participant.onIceCandidate.bind(participant)
    }
	participant.rtcPeer =new kurentoUtils.WebRtcPeer.WebRtcPeerSendrecv(options,
			function (error) {
			  if(error) {
				  return console.error(error);
			  }
			  this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});
}

function onParticipantLeft(request) {
	console.log('Participant ' + request.name + ' left');
	let participant = participants[request.name];
	participant.dispose();
	delete participants[request.name];
}

function sendMessage(message) {
	let jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws.send(jsonMessage);
}




export{
	sendMessage,
	onParticipantLeft,
	receiveVideo,
	receiveVideoResponse,
	register,
	leaveRoom,
	onExistingParticipants,
	callResponse,
	onNewParticipant,
}