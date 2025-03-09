package com.toki.backend.friend.dto.request;

import com.toki.backend.common.utils.ConvertUserTag;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestProcessDto {
    private String toUserTag;
    private Boolean acceptFriend;

    public Integer getToUserTag(){
        return ConvertUserTag.convertUserTag(toUserTag);
    }
}
