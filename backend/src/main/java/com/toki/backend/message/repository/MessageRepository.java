package com.toki.backend.message.repository;

import com.toki.backend.member.entity.User;
import com.toki.backend.message.dto.response.MessageByFromUserDto;
import com.toki.backend.message.dto.response.MessageByToUserDto;
import com.toki.backend.message.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select " +
            "new com.toki.backend.message.dto.MessageByFromUserDto(m.idx, m.toUser.userTag, m.content, m.isRead, m.createAt)" +
            "from Message m " +
            "where m.fromUser=:fromUser")
    Page<MessageByFromUserDto> findAllByFromUser(User fromUser, PageRequest pageRequest);

    @Query("select " +
            "new com.toki.backend.message.dto.MessageByFromUserDto(m.idx, m.fromUser.userTag, m.content, m.isRead, m.createAt)" +
            "from Message m " +
            "where m.toUser=:toUser")
    Page<MessageByToUserDto> findAllByToUser(User toUser, PageRequest pageRequest);

    List<Message> findMessageByFromUserAndToUser(User fromUser, User toUser);


}
