package com.toki.backend.hashTag;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.hashTag.dto.HashTagStatDTO;
import com.toki.backend.hashTag.service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //240126
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class HashTagController {


    private final HashTagService hashTagService;
    // 태그 생성 또는 업데이트
    @PostMapping("/{roomId}/tags")
    public ResponseEntity<CommonResponseDto> createOrUpdateHashtag(@RequestParam String tagName) {
        try {
            HashTagStatDTO result = hashTagService.createOrUpdateHashTag(tagName);
            return ResponseEntity.ok(CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("해시태그 생성 또는 업데이트 성공")
                    .data(result)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.builder()
                            .resultCode(400)
                            .resultMessage("해시태그 생성 또는 업데이트에 실패하였습니다.")
                            .build());
        }
    }




}
