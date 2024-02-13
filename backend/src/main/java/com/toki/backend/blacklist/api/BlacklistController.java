package com.toki.backend.blacklist.api;

import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.blacklist.dto.request.BlacklistRequestDto;
import com.toki.backend.blacklist.dto.request.BlacklistSaveRequestDto;
import com.toki.backend.blacklist.service.BlacklistService;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.common.utils.ConvertUserTag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/blacklist")
@RequiredArgsConstructor
public class BlacklistController {


    private final BlacklistService blacklistService;

    // 블랙리스트 목록 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<Object>> findBlacklist(@AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        BlacklistRequestDto requestDto = BlacklistRequestDto.builder()
                .fromUserPk(userPrincipal.getName())
                .build();

        CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("블랙리스트 조회에 성공했습니다.")
                .data(blacklistService.getBlacklistByFromUserPk(requestDto))
                .build();

        return ResponseEntity.ok(responseDto);
    }

    // 블랙리스트에 유저 추가
    @PostMapping
    public ResponseEntity<CommonResponseDto<Object>> addBlacklist(@RequestBody BlacklistSaveRequestDto saveRequestDto,
                                                                  @AuthenticationPrincipal CustomOAuth2User userPrincipal) {

        String fromUserPk = userPrincipal.getName();
        Integer toUserTag = ConvertUserTag.convertUserTag(saveRequestDto.getToUserTag());

        blacklistService.saveBlacklist(fromUserPk, toUserTag);

        CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("유저를 블랙리스트에 추가하는 데에 성공했습니다.")
                .build();

        return ResponseEntity.ok(responseDto);
    }


    // 블랙리스트에서 유저 삭제
    @DeleteMapping("/{userTag}")
    public ResponseEntity<?> deleteBlacklist(@PathVariable String userTag,
                                             @AuthenticationPrincipal CustomOAuth2User userPrincipal) {


        blacklistService.deleteBlacklist(userPrincipal.getName(),
                ConvertUserTag.convertUserTag(userTag));

        CommonResponseDto<Object> responseDto = CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("유저를 블랙리스트에서 삭제하는 데에 성공했습니다.")
                .build();

        return ResponseEntity.ok(responseDto);
    }
}
