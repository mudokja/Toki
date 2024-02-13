package com.toki.backend.friend.dto.request;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestDto {
    private String toUserTag;
}
