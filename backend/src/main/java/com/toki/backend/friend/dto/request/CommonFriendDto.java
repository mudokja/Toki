package com.toki.backend.friend.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonFriendDto {
    private String fromUserPk;
    private String toUserPk;
}
