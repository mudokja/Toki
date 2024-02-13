package com.toki.backend.blacklist.service;


import com.toki.backend.member.entity.User;
import com.toki.backend.blacklist.dto.request.BlacklistRequestDto;
import com.toki.backend.blacklist.dto.response.BlacklistResponseDto;
import com.toki.backend.blacklist.entity.Blacklist;
import com.toki.backend.blacklist.repository.BlacklistRepository;
import com.toki.backend.member.repository.UserRepository;
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

    public void saveBlacklist(String fromUserPk, Integer toUserTag) {
        User fromUser = userRepository.findById(fromUserPk).get();
        User toUser = userRepository.findByUserTag(toUserTag).get();
        blacklistRepository.save(
                Blacklist.builder()
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build()
        );
    }

    public void deleteBlacklist(String fromUserPk, Integer toUserTag) {
        User fromUser = userRepository.findById(fromUserPk).get();
        User toUser = userRepository.findByUserTag(toUserTag).get();
        blacklistRepository.delete(
                Blacklist.builder()
                        .fromUser(fromUser)
                        .toUser(toUser)
                        .build()
        );
    }
}
