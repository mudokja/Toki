package com.toki.backend.webrtc.service;

import com.toki.backend.webrtc.dto.KurentoRoom;
import com.toki.backend.webrtc.dto.TokiRoomInstanceMap;
import lombok.RequiredArgsConstructor;
import org.kurento.client.KurentoClient;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class TokiRoomService {
    private final KurentoClient kurento;


    public KurentoRoom createTokiRoom(String title) {

        String roomId = UUID.randomUUID().toString();
        KurentoRoom room = new KurentoRoom(roomId);
        room.setTokiRooms(title, title, kurento);

        // 파이프라인 생성
        room.createPipline();


        TokiRoomInstanceMap.getInstance().getTokiRooms().put(room.getRoomId(), room);

        return room;
    }
}
