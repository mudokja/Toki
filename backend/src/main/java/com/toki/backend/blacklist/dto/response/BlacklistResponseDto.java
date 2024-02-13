package com.toki.backend.blacklist.dto.response;

import com.toki.backend.common.utils.ConvertUserTag;
import com.toki.backend.member.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlacklistResponseDto {
    private String userTag;
    private String userNickname;

    public  BlacklistResponseDto(User user) {
        this.userTag = ConvertUserTag.convertUserTag(user.getUserTag());
        this.userNickname = user.getUserNickName();
    }
}
