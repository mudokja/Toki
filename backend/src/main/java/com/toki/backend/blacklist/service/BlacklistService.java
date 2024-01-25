package com.toki.backend.blacklist.service;


import com.toki.backend.auth.entity.User;
import com.toki.backend.auth.repository.UserRepository;
import com.toki.backend.blacklist.dto.BlacklistRequestDto;
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

    // 블랙리스트 목록 조회
    public List<Blacklist> findBlacklist(String fromUserId) {

        return blacklistRepository.findAllByFromUserPk(fromUserId);
    }

    // 블랙리스트에 유저 추가
    public void addBlacklist(BlacklistRequestDto blacklistRequestDto) throws Exception {
        User fromUser = userRepository.findByUserId(blacklistRequestDto.getFromUserId()).orElseThrow(Exception::new);
        User toUser = userRepository.findByUserId(blacklistRequestDto.getToUserId()).orElseThrow(Exception::new);

        blacklistRepository.save(
                Blacklist.builder()
                        .fromUserId(fromUser.getUserPk())
                        .toUserId(toUser.getUserPk())
                        .build()
        );
    }

    // 블랙리스트에서 유저 삭제
    public void deleteBlacklist(BlacklistRequestDto blacklistRequestDto) throws Exception {
        User fromUser = userRepository.findByUserId(blacklistRequestDto.getFromUserId()).orElseThrow(Exception::new);
        User toUser = userRepository.findByUserId(blacklistRequestDto.getToUserId()).orElseThrow(Exception::new);

        String fromUserId = fromUser.getUserPk();
        String toUserId = toUser.getUserPk();

        Blacklist blacklist = blacklistRepository.findByFromUserIdAndToUserId(fromUserId, toUserId);

        blacklistRepository.delete(blacklist);
    }
}
