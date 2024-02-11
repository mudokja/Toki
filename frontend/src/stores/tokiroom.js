import { defineStore, acceptHMRUpdate } from 'pinia'
import { reactive, ref } from 'vue';
import { Participant } from '@/components/web_rtc/Participant';
import kurentoUtils from 'kurento-utils'


export const useTokiRoomStore = defineStore('tokiroom', () => {
  let options = {
    localVideo: null,
    mediaConstraints: null,
    onicecandidate: null
}
  const rtcPeer =ref(new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
		function (error) {
            if(error) {
                return console.error(error);
            }
    }))
  console.log(rtcPeer)
  const roomParticipantList = reactive([]);
  const tokiRoomInfo=ref()
  console.log("저장소 설정 시작")
  console.log("this :", this)
  const ws = new WebSocket("ws://localhost:8443/groupcall")
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
      roomParticipantList[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
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
    // name = document.getElementById('name').value;
    // var room = document.getElementById('roomName').value;
  
    var message = {
      id : 'joinRoom',
      name : tokiRoomInfo.value.name,
      room : tokiRoomInfo.value.room,
    }
    sendMessage(message);
  }
  
  function onNewParticipant(request) {
    receiveVideo(request.name);
  }
  
  function receiveVideoResponse(result) {
    roomParticipantList[result.name].rtcPeer.processAnswer (result.sdpAnswer, function (error) {
      if (error) return console.error (error);
    });
  }
  
  // function callResponse(message) {
  //   if (message.response != 'accepted') {
  //     console.info('Call not accepted by peer. Closing call');
  //     stop();
  //   } else {
  //     webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
  //       if (error) return console.error (error);
  //     });
  //   }
  // }
  
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
    console.log(tokiRoomInfo.value.name, + " registered in room " + tokiRoomInfo.value.room);
    var participant = new Participant(name);
    roomParticipantList[name] = participant;
    var video = participant.getVideoElement();
    console.dir(video)
    console.log(participant)
    console.dir(participant.offerToReceiveVideo.bind(participant))
    console.dir(this)
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
        console.log("전역this")
        console.dir(this)
        this.generateOffer (participant.offerToReceiveVideo.bind(participant));
    });
  
    msg.data.forEach(receiveVideo);
  }
  
  function leaveRoom() {
    sendMessage({
      id : 'leaveRoom'
    });
  
    for ( var key in roomParticipantList) {
      roomParticipantList[key].dispose();
    }
  
    ws.close();
  }
  
  function receiveVideo(sender) {
    var participant = new Participant(sender);
    roomParticipantList[sender] = participant;
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
    });
  }
  
  function onParticipantLeft(request) {
    console.log('Participant ' + request.name + ' left');
    var participant = roomParticipantList[request.name];
    participant.dispose();
    delete roomParticipantList[request.name];
  }

  function setRoomName(roomName){
    tokiRoomInfo.value.room=roomName
  }
  function setUserName(userName){
    tokiRoomInfo.value.name=userName
  }
  
  function sendMessage(message) {
    var jsonMessage = JSON.stringify(message);
    console.log('Sending message: ' + jsonMessage);
    ws.send(jsonMessage);
  }
  return {
   roomParticipantList,
   tokiRoomInfo,
   leaveRoom,
   receiveVideo,
   onParticipantLeft,
   setRoomName,
   setUserName,
   sendMessage
 }
})

if (import.meta.hot) {
 import.meta.hot.accept(acceptHMRUpdate(useTokiRoomStore, import.meta.hot))
}
