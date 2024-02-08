package com.toki.backend.roomchat.repository;


import com.toki.backend.roomchat.entity.RoomChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RoomChatMessageRepository extends CrudRepository<RoomChatMessage, String> {
    List<RoomChatMessage>  findAllByRoomChatPk(String roomChatPk);
}
