package com.toki.backend.hashTag;

import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.hashTag.dto.HashTagStatDTO;
import com.toki.backend.hashTag.service.HashTagStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController //240126
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor


public class HashTagStatController {

    private final HashTagStatService hashTagStatService;

    // 가장 많이 사용된 태그 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto> getMostUsedTags() {
        try {
            List<HashTagStatDTO> mostUsedTags = hashTagStatService.getMostUsedTags();
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("가장 많이 사용된 태그 조회 성공")
                    .data(mostUsedTags)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.builder()
                            .resultCode(400)
                            .resultMessage("가장 많이 사용된 태그 조회에 실패하였습니다.")
                            .build());
        }
    }
}
