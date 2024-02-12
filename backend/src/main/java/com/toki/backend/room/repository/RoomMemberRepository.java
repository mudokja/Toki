package com.toki.backend.room.repository;

import com.toki.backend.room.entity.RoomMember;
import org.springframework.data.repository.CrudRepository;

public interface RoomMemberRepository extends CrudRepository<RoomMember, Integer> {

}
