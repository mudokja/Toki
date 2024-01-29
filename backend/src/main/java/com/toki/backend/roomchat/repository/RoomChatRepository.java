package com.toki.backend.roomchat.repository;

import com.toki.backend.roomchat.entity.RoomChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {

}
