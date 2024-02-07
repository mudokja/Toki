package com.toki.backend.friend.dto.request;

import com.toki.backend.common.utils.ConvertUserTag;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendRequestProcessDto {
    private String toUserTag;
    private Boolean acceptFriend;

    public Integer getToUserTag(){
        return ConvertUserTag.convertUserTag(toUserTag);
    }
}
