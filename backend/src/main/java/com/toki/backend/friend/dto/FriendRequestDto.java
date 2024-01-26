package com.toki.backend.friend.dto;

import com.toki.backend.friend.entity.Friend;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendRequestDto {

    private String toUserId;
    private String fromUserId;

}
