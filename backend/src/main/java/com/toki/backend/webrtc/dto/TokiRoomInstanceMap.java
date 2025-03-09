package com.toki.backend.webrtc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
public class TokiRoomInstanceMap {
    private static TokiRoomInstanceMap tokiRoomInstanceMap = new TokiRoomInstanceMap();
    private ConcurrentMap<String, KurentoRoom> tokiRooms = new ConcurrentHashMap<>();

    private TokiRoomInstanceMap(){}

    public static TokiRoomInstanceMap getInstance(){
        return tokiRoomInstanceMap;
    }
    

}
