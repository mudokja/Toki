package com.toki.backend.roomchat.repository;


import com.toki.backend.roomchat.entity.RoomChatMessage;
import org.springframework.data.repository.CrudRepository;


public interface RoomChatMessageRepository extends CrudRepository<RoomChatMessage, String> {

}
