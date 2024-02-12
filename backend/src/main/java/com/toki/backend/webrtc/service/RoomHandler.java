

package com.toki.backend.webrtc.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.toki.backend.webrtc.dto.KurentoRoom;
import com.toki.backend.webrtc.dto.UserRoomSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kurento.client.IceCandidate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class RoomHandler extends TextWebSocketHandler {

  private static final Gson gson = new GsonBuilder().create();


  private final TokiRoomManager tokiRoomManager;

  private final TokiUserRegistry registry;

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    log.debug("세션 : {} 대상 에러 발생 : {}",session,exception.toString());
    super.handleTransportError(session, exception);
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    log.debug("{} 연결 확인", session.getId());
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    final JsonObject jsonMessage = gson.fromJson(message.getPayload(), JsonObject.class);
    System.out.println(jsonMessage+"\n deddddddddddddddddddddddddajgaoejegiaojaeigowjioaegwjaegiowjioaegwjioaegwjjioaegwio");
    final UserRoomSession user = registry.getBySession(session);

    if (user != null) {
      log.debug("Incoming message from user '{}': {}", user.getName(), jsonMessage);
    } else {
      log.debug("Incoming message from new user: {}", jsonMessage);
    }

    switch (jsonMessage.get("id").getAsString()) {
      case "joinRoom":
        joinRoom(jsonMessage, session);
        break;
      case "receiveVideoFrom":
        final String senderName = jsonMessage.get("sender").getAsString();
        final UserRoomSession sender = registry.getByName(senderName);
        final String sdpOffer = jsonMessage.get("sdpOffer").getAsString();
        user.receiveVideoFrom(sender, sdpOffer);
        break;
      case "leaveRoom":
        leaveRoom(user);
        break;
      case "ping":
        pong(user);
        break;
      case "onIceCandidate":
        JsonObject candidate = jsonMessage.get("candidate").getAsJsonObject();

        if (user != null) {
          IceCandidate cand = new IceCandidate(candidate.get("candidate").getAsString(),
              candidate.get("sdpMid").getAsString(), candidate.get("sdpMLineIndex").getAsInt());
          user.addCandidate(cand, jsonMessage.get("name").getAsString());
        }
        break;
      default:
        break;
    }
  }

  public void pong(UserRoomSession user ) throws IOException {
    user.pong(user);
  }
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    UserRoomSession user = registry.removeBySession(session);
    log.debug("연결 닫힘 {}",user.getName());
    tokiRoomManager.getRoom(user.getRoomName()).leave(user);
  }

  private void joinRoom(JsonObject params, WebSocketSession session) throws IOException {
    final String roomName = params.get("room").getAsString();
    final String name = params.get("name").getAsString();
    log.info("PARTICIPANT {}: trying to join room {}", name, roomName);

    KurentoRoom room = tokiRoomManager.getRoom(roomName);
    final UserRoomSession user = room.join(name, session);
    registry.register(user);
  }

  private void leaveRoom(UserRoomSession user) throws IOException {
    final KurentoRoom room = tokiRoomManager.getRoom(user.getRoomName());
    room.leave(user);
    if (room.getParticipants().isEmpty()) {
      tokiRoomManager.removeRoom(room);
    }
  }
}
