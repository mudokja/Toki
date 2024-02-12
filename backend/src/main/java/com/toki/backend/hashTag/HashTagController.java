package com.toki.backend.hashTag;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.hashTag.dto.HashTagStatDTO;
import com.toki.backend.hashTag.service.HashTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //240126 // 컨트롤러임을 나타내는 어노테이션
@RequestMapping("/api/v1/rooms") // URL 매핑을 위한 기본 경로 설정
@RequiredArgsConstructor // Lombok을 사용하여 생성자 주입을 위한 어노테이션
public class HashTagController {


    private final HashTagService hashTagService; // 해시태그 서비스 주입
    // 태그 생성 또는 업데이트
    @PostMapping("/{roomId}/tags") // POST 요청을 처리하기 위한 매핑
    public ResponseEntity<CommonResponseDto> createOrUpdateHashtag(@RequestParam String tagName) {
        try { // 해시태그 서비스를 통해 태그 생성 또는 업데이트 작업 수행
            HashTagStatDTO result = hashTagService.createOrUpdateHashTag(tagName);
            return ResponseEntity.ok(CommonResponseDto.builder() //성공시
                    .resultCode(200)
                    .resultMessage("해시태그 생성 또는 업데이트 성공")
                    .data(result)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //오류 발생시
                    .body(CommonResponseDto.builder()
                            .resultCode(400)
                            .resultMessage("해시태그 생성 또는 업데이트에 실패하였습니다.")
                            .build());
        }
    }




}
