let ws = new WebSocket('wss://' + location.host + '/groupcall');
let participants = {};
let name;

window.onbeforeunload = function() {
	ws.close();
};

ws.onmessage = function(message) {
	let parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);

	switch (parsedMessage.id) {
	case 'existingParticipants':
		onExistingParticipants(parsedMessage);//들어와 있는 참가자
		break;
	case 'newParticipantArrived':
		onNewParticipant(parsedMessage);//새로운 참가자
		break;
	case 'participantLeft':
		onParticipantLeft(parsedMessage);//다른 참가자 다 내보냄
		break;
	case 'receiveVideoAnswer':
		receiveVideoResponse(parsedMessage);//비디오 응답
		break;
	case 'iceCandidate'://통신 최적 경로 찾기 
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

function register() {//room관련 정보 가져옴
	name = document.getElementById('name').value;
	let room = document.getElementById('roomName').value;

	document.getElementById('room-header').innerText = 'ROOM ' + room;
	document.getElementById('join').style.display = 'none';
	document.getElementById('room').style.display = 'block';

	let message = {
		id : 'joinRoom',
		name : name,
		room : room,
	}
	sendMessage(message);
}

function onNewParticipant(request) {//새로운 참가자 
	receiveVideo(request.name);
}

function receiveVideoResponse(result) {//참가자 비디오 응답 오는지 확인
	participants[result.name].rtcPeer.processAnswer (result.sdpAnswer, function (error) {
		if (error) return console.error (error);
	});
}

function callResponse(message) {//응답 받기
	if (message.response != 'accepted') {//응답 받지 않았으면 정지
		console.info('Call not accepted by peer. Closing call');
		stop();
	} else {
		webRtcPeer.processAnswer(message.sdpAnswer, function (error) {//응답 받으면 연결
			if (error) return console.error (error);
		});
	}
}

function onExistingParticipants(msg) {//들어와있는 참가자
	let constraints = {//비디오 규격 오브젝트
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
	let video = participant.getVideoElement();//참가자 비디오 정보 가져오기

	let options = {
	      localVideo: video,//참가자 비디오 정보
	      mediaConstraints: constraints,//비디오 정보 규격
	      onicecandidate: participant.onIceCandidate.bind(participant)//참가자에게 참가자 경로 바인딩
	    }
	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,//위에 옵션 정보로 연결
		function (error) {
		  if(error) {
			  return console.error(error);
		  }
		  this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});

	msg.data.forEach(receiveVideo);
}

function leaveRoom() {//방을 떠날 때
	sendMessage({
		id : 'leaveRoom'
	});

	for ( var key in participants) {
		participants[key].dispose();//참가자 방 정보 처분
	}

	document.getElementById('join').style.display = 'block';
	document.getElementById('room').style.display = 'none';

	ws.close();
}

function receiveVideo(sender) {//비디오 주고 받기
	var participant = new Participant(sender);
	participants[sender] = participant;//비디오 주는 참가자
	var video = participant.getVideoElement();//비디오 정보

	var options = {
      remoteVideo: video,
      onicecandidate: participant.onIceCandidate.bind(participant)//참가자 통신 경로 바인드
    }

	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,//연결 정보 주고 받기
			function (error) {
			  if(error) {
				  return console.error(error);
			  }
			  this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});;
}
function onParticipantLeft(request) {//나머지 참가자 내보내기
	console.log('Participant ' + request.name + ' left');
	var participant = participants[request.name];//참가자 배열 받기
	participant.dispose();//참가자 처분
	delete participants[request.name];//참가자 배열 삭제
}

function sendMessage(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws.send(jsonMessage);
}


const PARTICIPANT_MAIN_CLASS = 'participant main';
const PARTICIPANT_CLASS = 'participant';

/**
 * Creates a video element for a new participant
 *
 */
// @param {String} name - the name of the new participant, to be used as tag
/*                        name of the video element.
 *                        The tag of the new element will be 'video<name>'
 * @return
 */
function Participant(name) {
	this.name = name;
	var container = document.createElement('div');
	container.className = isPresentMainParticipant() ? PARTICIPANT_CLASS : PARTICIPANT_MAIN_CLASS;
	container.id = name;
	var span = document.createElement('span');
	var video = document.createElement('video');
	var rtcPeer;

	container.appendChild(video);
	container.appendChild(span);
	container.onclick = switchContainerClass;
	document.getElementById('participants').appendChild(container);

	span.appendChild(document.createTextNode(name));

	video.id = 'video-' + name;
	video.autoplay = true;
	video.controls = false;


	this.getElement = function() {
		return container;
	}

	this.getVideoElement = function() {
		return video;
	}

	function switchContainerClass() {
		if (container.className === PARTICIPANT_CLASS) {
			var elements = Array.prototype.slice.call(document.getElementsByClassName(PARTICIPANT_MAIN_CLASS));
			elements.forEach(function(item) {
					item.className = PARTICIPANT_CLASS;
				});

				container.className = PARTICIPANT_MAIN_CLASS;
			} else {
			container.className = PARTICIPANT_CLASS;
		}
	}

	function isPresentMainParticipant() {
		return ((document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)).length != 0);
	}

	this.offerToReceiveVideo = function(error, offerSdp, wp){
		if (error) return console.error ("sdp offer error")
		console.log('Invoking SDP offer callback function');
		var msg =  { id : "receiveVideoFrom",
				sender : name,
				sdpOffer : offerSdp
			};
		sendMessage(msg);
	}


	this.onIceCandidate = function (candidate, wp) {
		  console.log("Local candidate" + JSON.stringify(candidate));

		  var message = {
		    id: 'onIceCandidate',
		    candidate: candidate,
		    name: name
		  };
		  sendMessage(message);
	}

	Object.defineProperty(this, 'rtcPeer', { writable: true});

	this.dispose = function() {
		console.log('Disposing participant ' + this.name);
		this.rtcPeer.dispose();
		container.parentNode.removeChild(container);
	};
}