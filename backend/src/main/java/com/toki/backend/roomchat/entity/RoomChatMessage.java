package com.toki.backend.roomchat.entity;


import com.toki.backend.roomchat.dto.RoomChatType;
import com.toki.backend.roomchat.dto.response.RoomChatResponseLogMessageDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.ArrayList;
import java.util.List;


@RedisHash(value = "roomchatmessage")
@Getter
@NoArgsConstructor
@ToString
public class RoomChatMessage {

    @Indexed
    private String roomChatPk;

    @Id
    private Long roomChatId;

    private Long roomChatIdx;

    private RoomChatType chatType;
    private String content;
    @Column
    private String fromUserPk;
    @Column
    private String fromUserNickName;
    @Column
    private String sendTo;

    @CreatedDate
    private String crateAt;

    @Builder
    public RoomChatMessage(String roomChatPk, Long roomChatIdx, RoomChatType chatType,String content, String fromUserPk,String fromUserNickName, String sendTo, String crateAt) {
        this.roomChatPk = roomChatPk;
        this.roomChatIdx = roomChatIdx;
        this.chatType = chatType;
        this.content=content;
        this.fromUserPk = fromUserPk;
        this.fromUserNickName=fromUserNickName;
        this.sendTo = sendTo;
        this.crateAt = crateAt;
    }
    public static List<RoomChatResponseLogMessageDto> toResponseLogMessageDto(List<RoomChatMessage> list){
        List<RoomChatResponseLogMessageDto> logMessageDtoList=new ArrayList<>();
            for(RoomChatMessage roomChatMessage : list){
                logMessageDtoList.add(roomChatMessage.toResponseLogMessageDto());
            }
            return logMessageDtoList;
    }
    public RoomChatResponseLogMessageDto toResponseLogMessageDto(){
        return RoomChatResponseLogMessageDto.builder()
                .crateAt(getCrateAt())
                .sendTo(getSendTo())
                .fromUserPk(getFromUserPk())
                .fromUserNickName(getFromUserNickName())
                .roomChatPk(getRoomChatPk())
                .content(getContent())
                .build();
    }

}
