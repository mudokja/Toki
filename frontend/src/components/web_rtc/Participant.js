const PARTICIPANT_MAIN_CLASS = 'participant main';
const PARTICIPANT_CLASS = 'participant';
import { sendMessage } from './WebRTC';
import adapter from 'webrtc-adapter';
adapter
function Participant(name) {

	
	this.name = name;
	var video = document.createElement('video');
	Object.defineProperty(this, 'rtcPeer', { writable: true});
	
	video.id = 'video-' + name;
	video.autoplay = true;
	video.controls = false;
	

	
	this.getVideoElement = function() {
		return video;
	}
	
	function isPresentMainParticipant() {
		return ""
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
		  console.dir("이거안되는듯?")
		  var message = {
		    id: 'onIceCandidate',
		    candidate: candidate,
		    name: name
		  };
		  sendMessage(message);
	}



	this.dispose = function() {
		console.log('Disposing participant ' + this.name);
		this.rtcPeer.dispose();
	};
}
export{
    Participant,
}
