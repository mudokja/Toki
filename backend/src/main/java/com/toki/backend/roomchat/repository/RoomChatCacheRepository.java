package com.toki.backend.roomchat.repository;

import com.toki.backend.roomchat.entity.RoomChatCache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomChatCacheRepository extends JpaRepository<RoomChatCache, Long> {
}
