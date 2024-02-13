package com.toki.backend.badge;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.service.BadgeService;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.member.dto.UserDTO;
import com.toki.backend.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BadgeController {



    private final BadgeService badgeService;
    private final UserRepository userRepository;



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

    // 3. 배지 삭제
    @DeleteMapping("/api/v1/admin/badges/{badgeIdx}")
    public ResponseEntity<CommonResponseDto<Void>> deleteBadge(@PathVariable int badgeIdx) {
        badgeService.deleteBadge(badgeIdx);
        return ResponseEntity.ok(CommonResponseDto.<Void>builder()
                .resultCode(200)
                .resultMessage("배지 삭제 성공")
                .data(null)
                .build());
    }

    // 4. 회원에게 배지 추가.
    @PostMapping("/api/v1/admin/badges/{userTag}")
    public ResponseEntity<CommonResponseDto<BadgeDTO>> saveToMember(@PathVariable int userTag, @RequestBody BadgeDTO badgeDTO){
        BadgeDTO  addBadge = badgeService.saveBadge(badgeDTO);
        return ResponseEntity.ok(CommonResponseDto.<BadgeDTO>builder()
                .resultCode(200)
                .resultMessage(userTag + "회원에게 배지 추가 성공")
                .data(addBadge)
                .build());

    }

    // 5. 특정 회원의 배지 조회하기
    @GetMapping("/api/v1/badges/{userTag}")
    public ResponseEntity<CommonResponseDto<BadgeDTO>> getBadgeByIdx(@PathVariable int userTag) {
        // 특정 회원의 배지 조회 메서드 호출
        BadgeDTO badgeDTO = badgeService.getBadgeByIdx(userTag);
        return ResponseEntity.ok(CommonResponseDto.<BadgeDTO>builder()
                .resultCode(200)
                .resultMessage("배지 조회 성공")
                .data(badgeDTO)
                .build());
    }

    // 6. 사용자로부터 배지 제거
    @DeleteMapping("/api/v1/admin/badges/{badgeIdx}/users/{userTag}")
    public ResponseEntity<CommonResponseDto<Void>> deleteBadgeFromUser(@PathVariable int badgeIdx, @PathVariable int userTag) {
        badgeService.deleteBadgeFromUser(badgeIdx, userTag);
        return ResponseEntity.ok(CommonResponseDto.<Void>builder()
                .resultCode(200)
                .resultMessage("사용자로부터 배지 제거 성공")
                .data(null)
                .build());
    }

    // 7. 특정 배지를 가진 모든 사용자 조회
    @GetMapping("/api/v1/admin/badges/{badgeIdx}/users")
    public ResponseEntity<CommonResponseDto<List<BadgeDTO>>> getUsersByBadge(@PathVariable int badgeIdx) {
        // 특정 배지를 가진 모든 사용자 조회
        List<UserDTO> usersByBadge = badgeService.getUsersByBadge(badgeIdx);


        // UserDTO를 BadgeDTO로 변 환
        List<BadgeDTO> badgeDTOs = usersByBadge.stream()
                .map(userDTO -> new BadgeDTO(userDTO.getUserTag(), userDTO.getUserName(), userDTO.getUserEmail()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CommonResponseDto.<List<BadgeDTO>>builder()
                .resultCode(200)
                .resultMessage("조회 성공")
                .data(badgeDTOs)
                .build());
    }
}
