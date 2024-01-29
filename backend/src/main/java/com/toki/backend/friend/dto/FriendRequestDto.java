package com.toki.backend.friend.dto;

import com.toki.backend.auth.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendRequestDto {

    private User fromUser;
    private User toUser;

}
