/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 
 * Modified Work © Copyright 2024 Toki
 * 
 * Modifications:
 * - Updated the data format to match the requirements of this project.
 * - Added some methods for project functionality.
 */

package com.toki.backend.webrtc.dto;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kurento.client.*;
import org.kurento.jsonrpc.JsonUtils;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * @author Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 * @author Jung Jihoon (modifier, mudokja@gmail.com)
 * @version 1.0
 */
@Slf4j
@Getter
@NoArgsConstructor
public class UserRoomSession implements Closeable {

  private String name;
  private WebSocketSession session;
  private KurentoClient kurento;
  private MediaPipeline pipeline;

  private String roomName;
  private WebRtcEndpoint outgoingMedia;
  private ConcurrentMap<String, WebRtcEndpoint> incomingMedia = new ConcurrentHashMap<>();

  public UserRoomSession(final String name, String roomName, final WebSocketSession session,
                         MediaPipeline pipeline) {

    this.pipeline = pipeline;
    this.name = name;
    this.session = session;
    this.roomName = roomName;
    this.outgoingMedia = new WebRtcEndpoint.Builder(pipeline).build();

    this.outgoingMedia.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

      @Override
      public void onEvent(IceCandidateFoundEvent event) {
        JsonObject response = new JsonObject();
        response.addProperty("id", "iceCandidate");
        response.addProperty("name", name);
        response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
        try {
          synchronized (session) {
            session.sendMessage(new TextMessage(response.toString()));
          }
        } catch (IOException e) {
          log.debug(e.getMessage());
        }
      }
    });
  }

  public WebRtcEndpoint getOutgoingWebRtcPeer() {
    return outgoingMedia;
  }

  public void pong(UserRoomSession user) throws IOException {
    JsonObject response = new JsonObject();
    response.addProperty("id","pong");
    response.addProperty("user",user.getName());
    sendMessage(response);
  }
  public void receiveVideoFrom(UserRoomSession sender, String sdpOffer) throws IOException {
    log.info("USER {}: connecting with {} in room {}", this.name, sender.getName(), this.roomName);

    log.trace("USER {}: SdpOffer for {} is {}", this.name, sender.getName(), sdpOffer);

    final String ipSdpAnswer = this.getEndpointForUser(sender).processOffer(sdpOffer);
    final JsonObject scParams = new JsonObject();
    scParams.addProperty("id", "receiveVideoAnswer");
    scParams.addProperty("name", sender.getName());
    scParams.addProperty("sdpAnswer", ipSdpAnswer);

    log.trace("USER {}: SdpAnswer for {} is {}", this.name, sender.getName(), ipSdpAnswer);
    this.sendMessage(scParams);
    log.debug("gather candidates");
    this.getEndpointForUser(sender).gatherCandidates();
  }

  private WebRtcEndpoint getEndpointForUser(final UserRoomSession sender) {
    if (sender.getName().equals(name)) {
      log.debug("PARTICIPANT {}: configuring loopback", this.name);
      return outgoingMedia;
    }

    log.debug("PARTICIPANT {}: receiving video from {}", this.name, sender.getName());

    WebRtcEndpoint incoming = incomingMedia.get(sender.getName());
    if (incoming == null) {
      log.debug("PARTICIPANT {}: creating new endpoint for {}", this.name, sender.getName());
      incoming = new WebRtcEndpoint.Builder(pipeline).build();

      incoming.addIceCandidateFoundListener(new EventListener<IceCandidateFoundEvent>() {

        @Override
        public void onEvent(IceCandidateFoundEvent event) {
          JsonObject response = new JsonObject();
          response.addProperty("id", "iceCandidate");
          response.addProperty("name", sender.getName());
          response.add("candidate", JsonUtils.toJsonObject(event.getCandidate()));
          try {
            synchronized (session) {
              session.sendMessage(new TextMessage(response.toString()));
            }
          } catch (IOException e) {
            log.debug(e.getMessage());
          }
        }
      });

      incomingMedia.put(sender.getName(), incoming);
    }

    log.debug("PARTICIPANT {}: obtained endpoint for {}", this.name, sender.getName());
    sender.getOutgoingWebRtcPeer().connect(incoming);

    return incoming;
  }

  public void cancelVideoFrom(final UserRoomSession sender) {
    this.cancelVideoFrom(sender.getName());
  }

  public void cancelVideoFrom(final String senderName) {
    log.debug("PARTICIPANT {}: canceling video reception from {}", this.name, senderName);
    final WebRtcEndpoint incoming = incomingMedia.remove(senderName);

    log.debug("PARTICIPANT {}: removing endpoint for {}", this.name, senderName);
    incoming.release(new Continuation<Void>() {
      @Override
      public void onSuccess(Void result) throws Exception {
        log.trace("PARTICIPANT {}: Released successfully incoming EP for {}",
            UserRoomSession.this.name, senderName);
      }

      @Override
      public void onError(Throwable cause) throws Exception {
        log.warn("PARTICIPANT {}: Could not release incoming EP for {}", UserRoomSession.this.name,
            senderName);
      }
    });
  }

  @Override
  public void close() throws IOException {
    log.debug("PARTICIPANT {}: Releasing resources", this.name);
    for (final String remoteParticipantName : incomingMedia.keySet()) {

      log.trace("PARTICIPANT {}: Released incoming EP for {}", this.name, remoteParticipantName);

      final WebRtcEndpoint ep = this.incomingMedia.get(remoteParticipantName);

      ep.release(new Continuation<Void>() {

        @Override
        public void onSuccess(Void result) throws Exception {
          log.trace("PARTICIPANT {}: Released successfully incoming EP for {}",
              UserRoomSession.this.name, remoteParticipantName);
        }

        @Override
        public void onError(Throwable cause) throws Exception {
          log.warn("PARTICIPANT {}: Could not release incoming EP for {}", UserRoomSession.this.name,
              remoteParticipantName);
        }
      });
    }

    outgoingMedia.release(new Continuation<Void>() {

      @Override
      public void onSuccess(Void result) throws Exception {
        log.trace("PARTICIPANT {}: Released outgoing EP", UserRoomSession.this.name);
      }

      @Override
      public void onError(Throwable cause) throws Exception {
        log.warn("USER {}: Could not release outgoing EP", UserRoomSession.this.name);
      }
    });
  }

  public void sendMessage(JsonObject message) throws IOException {
    log.debug("USER {}: Sending message {}", name, message);
    synchronized (session) {
      session.sendMessage(new TextMessage(message.toString()));
    }
  }

  public void addCandidate(IceCandidate candidate, String name) {
    if (this.name.compareTo(name) == 0) {
      outgoingMedia.addIceCandidate(candidate);
    } else {
      WebRtcEndpoint webRtc = incomingMedia.get(name);
      if (webRtc != null) {
        webRtc.addIceCandidate(candidate);
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof UserRoomSession)) {
      return false;
    }
    UserRoomSession other = (UserRoomSession) obj;
    boolean eq = name.equals(other.name);
    eq &= roomName.equals(other.roomName);
    return eq;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + name.hashCode();
    result = 31 * result + roomName.hashCode();
    return result;
  }
}
