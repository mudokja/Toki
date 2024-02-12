package com.toki.backend.roomchat.repository;


import com.toki.backend.roomchat.entity.RoomChatLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;


public interface RoomChatLogRepository extends CrudRepository<RoomChatLog, Long> {
    Integer countByRoomChatPk(String roomPk);

    Optional<RoomChatLog> findTopByRoomChatPkOrderByRoomChatSessionNumber(String RoomPk);
}
