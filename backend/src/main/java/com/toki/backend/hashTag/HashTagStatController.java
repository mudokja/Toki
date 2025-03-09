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

    // 가장 많이 사용된 5개 태그 조회
//    @GetMapping
//    public ResponseEntity<CommonResponseDto> getMostUsedTags() {
//        try {
//            // 가장 많이 사용된 태그 목록을 서비스로부터 가져오기.
////            List<HashTagStatDTO> mostUsedTags = hashTagStatService.getMostUsedTags();
//            // 성공적으로 태그를 조회한 경우, 성공 응답을 반환
//            return ResponseEntity.ok(CommonResponseDto.builder()
//                    .resultCode(200)
//                    .resultMessage("가장 많이 사용된 태그 조회 성공")
//                    .data(mostUsedTags)
//                    .build());
//        } catch (Exception e) {  // 태그 조회 도중 예외가 발생한 경우, 서버 오류 응답을 반환
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(CommonResponseDto.builder()
//                            .resultCode(400)
//                            .resultMessage("가장 많이 사용된 태그 조회에 실패하였습니다.")
//                            .build());
//        }
//    }
}
