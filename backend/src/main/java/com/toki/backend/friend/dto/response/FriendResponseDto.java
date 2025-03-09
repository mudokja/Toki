package com.toki.backend.friend.dto.response;


import com.toki.backend.common.utils.ConvertUserTag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FriendResponseDto {
    String userNickname;
    String userTag;
    String profileImageUrl;

    public FriendResponseDto(String userNickname, Integer userTag, String profileImageUrl) {
        this.userNickname = userNickname;
        this.userTag = ConvertUserTag.convertUserTag(userTag);
        this.profileImageUrl = profileImageUrl;
    }
}
