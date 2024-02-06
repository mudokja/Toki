package com.toki.backend.blacklist.dto.response;

import com.toki.backend.auth.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlacklistResponseDto {
    private String userTag;
    private String userNickname;

    public  BlacklistResponseDto(User user) {
        this.userTag = user.getUserTag();
        this.userNickname = user.getUserNickName();
    }
}
