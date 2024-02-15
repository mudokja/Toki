package com.toki.backend.room.repository;

import com.toki.backend.room.entity.RoomTag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomTagRepository extends CrudRepository<RoomTag, String> {
    List<RoomTag> findAllByTags(String tag);
}
