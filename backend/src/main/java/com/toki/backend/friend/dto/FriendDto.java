package com.toki.backend.friend.dto;

import com.toki.backend.member.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FriendDto {
    private User fromUser;
    private User toUser;

    @Builder
    public FriendDto(User fromUser, User toUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
