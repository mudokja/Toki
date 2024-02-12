package com.toki.backend.roomchat.dto.response;

import com.toki.backend.roomchat.dto.RoomChatType;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

@ToString
@Getter
@Builder
public class RoomChatResponseLogMessageDto {

    private String roomChatPk;

    private String content;
    private String fromUser;
    private String sendTo;

    private String crateAt;

}
