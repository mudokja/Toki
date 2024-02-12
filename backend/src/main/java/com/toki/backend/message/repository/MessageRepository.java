package com.toki.backend.message.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.message.dto.response.MessageResponseDto;
import com.toki.backend.message.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select " +
            "new com.toki.backend.message.dto.response.MessageResponseDto(m.idx, m.fromUser.userNickName, m.fromUser.userTag, m.fromUser.profileImageUrl, m.content)" +
            "from Message m " +
            "where m.toUser=:toUser")
    Page<MessageResponseDto> findAllByToUser(User toUser, PageRequest pageRequest);

    List<Message> findMessageByFromUserAndToUser(User fromUser, User toUser);


}
