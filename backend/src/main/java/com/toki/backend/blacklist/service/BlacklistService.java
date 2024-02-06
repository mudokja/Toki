package com.toki.backend.blacklist.service;


import com.toki.backend.member.entity.User;
import com.toki.backend.auth.repository.UserRepository;
import com.toki.backend.blacklist.dto.request.BlacklistRequestDto;
import com.toki.backend.blacklist.dto.response.BlacklistResponseDto;
import com.toki.backend.blacklist.entity.Blacklist;
import com.toki.backend.blacklist.repository.BlacklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final UserRepository userRepository;

    public List<BlacklistResponseDto> getBlacklistByFromUserPk(BlacklistRequestDto requestDto) {

        User fromUser = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();

        return blacklistRepository.findAllToUserByFromUser(fromUser);
    }

    public void saveBlacklist(BlacklistRequestDto requestDto) {
        User fromUser = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();
        User toUser = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();
        blacklistRepository.save(
                Blacklist.builder()
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build()
        );
    }

    public void deleteBlacklist(BlacklistRequestDto requestDto) {
        User fromUser = User.builder()
                .userPk(requestDto.getFromUserPk())
                .build();
        User toUser = User.builder()
                .userPk(requestDto.getToUserPk())
                .build();
        blacklistRepository.save(
                Blacklist.builder()
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build()
        );
    }
}
