package com.toki.backend.blacklist.api;

import com.toki.backend.blacklist.dto.BlacklistRequestDto;
import com.toki.backend.blacklist.entity.Blacklist;
import com.toki.backend.blacklist.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/blacklist")
@RequiredArgsConstructor
public class BlacklistController {


    private final BlacklistService blacklistService;

    // 블랙리스트 목록 조회
    @GetMapping
    public ResponseEntity<List<Blacklist>> findBlacklist() {

        // 토큰에서 유저의 pk 값을 찾는다.
        String userPk = "ABC";

        return ResponseEntity.ok().body(blacklistService.findBlacklist(userPk));

    }

    // 블랙리스트에 유저 추가
    @PostMapping
    public ResponseEntity<?> addBlacklist(BlacklistRequestDto blacklistRequestDto) {
        try {
            blacklistService.addBlacklist(blacklistRequestDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }


    // 블랙리스트에서 유저 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteBlacklist() {

        String userPk = "ABC";

        return ResponseEntity.ok().build();
    }
}
