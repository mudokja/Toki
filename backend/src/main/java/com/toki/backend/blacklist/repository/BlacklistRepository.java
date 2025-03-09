package com.toki.backend.blacklist.repository;

import com.toki.backend.blacklist.dto.response.BlacklistResponseDto;
import com.toki.backend.blacklist.entity.Blacklist;
import com.toki.backend.member.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {


    @Query("select "
            + "new com.toki.backend.blacklist.dto.response.BlacklistResponseDto(b.toUser)"
            + "from Blacklist b where b.fromUser=:fromUser")
    List<BlacklistResponseDto> findAllToUserByFromUser(User fromUser);

}
