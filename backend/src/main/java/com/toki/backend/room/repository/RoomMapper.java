package com.toki.backend.room.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.hashTag.entity.HashTag;

import java.util.List;

public interface RoomMapper {
    String getTitle();
    Integer getCategoryPk();

    Boolean getIsPrivate();

    List<HashTag> getHashTag();
    List<User> getMembers();

}
