
package com.toki.backend.webrtc.service;

import com.toki.backend.webrtc.dto.KurentoRoom;
import com.toki.backend.webrtc.dto.TokiRoomInstanceMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kurento.client.KurentoClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentMap;


@RequiredArgsConstructor
@Slf4j
@Service
public class TokiRoomManager {

  private final ConcurrentMap<String, KurentoRoom> rooms = TokiRoomInstanceMap.getInstance().getTokiRooms();
  private final TokiRoomService tokiRoomService;


  public KurentoRoom getRoom(String roomName) {
    log.debug("Searching for room {}", roomName);

    KurentoRoom room = rooms.get(roomName);
    if(room==null){

      rooms.put(roomName,tokiRoomService.createTokiRoom(roomName));
      rooms.get(roomName);
    }
    log.debug("Room {} found!", roomName);
    return room;
  }


  public void removeRoom(KurentoRoom kurentoRoom) {
    this.rooms.remove(kurentoRoom.getRoomId());
    kurentoRoom.close();
    log.info("Room {} removed and closed", kurentoRoom.getRoomId());
  }

}
