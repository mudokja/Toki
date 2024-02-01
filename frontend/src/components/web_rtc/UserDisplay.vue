<script setup>
import kurentoUtils from 'kurento-utils'
import adapter from 'webrtc-adapter';
adapter
var ws = new WebSocket('wss://192.168.31.190:8443/groupcall');
var participants = {};
var name;

window.onbeforeunload = function() {
   ws.close();
};

ws.onmessage = function(message) {
   var parsedMessage = JSON.parse(message.data);
   console.info('Received message: ' + message.data);

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

function register() {
   name = document.getElementById('name').value;
   var room = document.getElementById('roomName').value;

   document.getElementById('room-header').innerText = 'ROOM ' + room;
   document.getElementById('join').style.display = 'none';
   document.getElementById('room').style.display = 'block';

   var message = {
       id : 'joinRoom',
       name : name,
       room : room,
   }
   sendMessage(message);
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
   var constraints = {
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
   var participant = new Participant(name);
   participants[name] = participant;
   var video = participant.getVideoElement();

   var options = {
         localVideo: video,
         mediaConstraints: constraints,
         onicecandidate: participant.onIceCandidate.bind(participant)
       }
   participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
       function (error) {
         if(error) {
             return console.error(error);
         }
         this.generateOffer (participant.offerToReceiveVideo.bind(participant));
   });

   msg.data.forEach(receiveVideo);
}

function leaveRoom() {
   sendMessage({
       id : 'leaveRoom'
   });

   for ( var key in participants) {
       participants[key].dispose();
   }

   document.getElementById('join').style.display = 'block';
   document.getElementById('room').style.display = 'none';

   ws.close();
}

function receiveVideo(sender) {
   var participant = new Participant(sender);
   participants[sender] = participant;
   var video = participant.getVideoElement();

   var options = {
     remoteVideo: video,
     onicecandidate: participant.onIceCandidate.bind(participant)
   }

   participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
           function (error) {
             if(error) {
                 return console.error(error);
             }
             this.generateOffer (participant.offerToReceiveVideo.bind(participant));
   });;
}

function onParticipantLeft(request) {
   console.log('Participant ' + request.name + ' left');
   var participant = participants[request.name];
   participant.dispose();
   delete participants[request.name];
}

function sendMessage(message) {
   var jsonMessage = JSON.stringify(message);
   console.log('Sending message: ' + jsonMessage);
   ws.send(jsonMessage);
}

</script>

<template>
    <div >
        <video src="" width="200" height="200" id="display"></video>
    </div>
    안녕하세여
<body>
	<div id="container">
		<div id="wrapper">
			<div id="join" class="animate join">
				<h1>Join a Room</h1>
				<form onsubmit="register(); return false;" accept-charset="UTF-8">
					<p>
						<input type="text" name="name" value="" id="name"
							placeholder="Username" required>
					</p>
					<p>
						<input type="text" name="room" value="" id="roomName"
							placeholder="Room" required>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Join!">
					</p>
				</form>
			</div>
			<div id="room" style="display: none;">
				<h2 id="room-header"></h2>
				<div id="participants"></div>
				<input type="button" id="button-leave" onmouseup="leaveRoom();"
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