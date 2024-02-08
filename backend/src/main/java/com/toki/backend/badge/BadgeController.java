package com.toki.backend.badge;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.service.BadgeService;
import com.toki.backend.common.dto.response.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BadgeController {



    private final BadgeService badgeService;



    //1, 모든 배지를 조회한다.
    @GetMapping("/api/v1/badges")
    public ResponseEntity<CommonResponseDto<List<BadgeDTO>>> getAllBadges() {
        List<BadgeDTO> badges = badgeService.getAllBadges();
        return ResponseEntity.ok(CommonResponseDto.<List<BadgeDTO>>builder()
                .resultCode(200)
                .resultMessage("모든배지 조회 성공")
                .data(badges)
                .build());
    }

    ///2 . 배지 등록

    @PostMapping("/api/v1/admin/badges")
    public ResponseEntity<CommonResponseDto<BadgeDTO>> saveBadge(@RequestBody BadgeDTO badgeDTO) {
        BadgeDTO newBadge = badgeService.saveBadge(badgeDTO);
        return ResponseEntity.ok(CommonResponseDto.<BadgeDTO>builder()
                .resultCode(200)
                .resultMessage("배지 등록 성공")
                .data(newBadge)
                .build());
    }

    //3. 회원에게 배지 추가.
    @PostMapping("/api/v1/admin/badges/{userTag}")
    public ResponseEntity<CommonResponseDto<BadgeDTO>> saveToMember(@PathVariable String userTag, @RequestBody BadgeDTO badgeDTO){
        BadgeDTO  addBadge = badgeService.saveBadge(badgeDTO);
        return ResponseEntity.ok(CommonResponseDto.<BadgeDTO>builder()
                .resultCode(200)
                .resultMessage(userTag + "회원에게 배지 추가 성공")
                .data(addBadge)
                .build());

    }

    // 4. 특정 회원의 배지 조회하기
    @GetMapping("/api/v1/badges/{userTag}")
    public ResponseEntity<CommonResponseDto<BadgeDTO>> getBadgeByIdx(@PathVariable String userTag) {
        List<BadgeDTO> badges = badgeService.findByUserTag(userTag);
        return ResponseEntity.ok(CommonResponseDto.<BadgeDTO>builder()
                .resultCode(200)
                .resultMessage("배지 조회 성공")
                .data((BadgeDTO) badges)
                .build());
    }
}
