package com.toki.backend.member;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.member.dto.MemberDTO;
import com.toki.backend.member.dto.UpdateMemberRequestDTO;
import com.toki.backend.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;


  /*  //유저 상세 정보 조회
    @GetMapping("/{userPk}?info_type=detail")
    public CommonResponseDto<MemberDTO> getUserDetailInfo(@PathVariable String userPk) {
        try {
            MemberDTO result = memberService.getUserDetailInfo(userPk);
            return CommonResponseDto.<MemberDTO>builder()
                    .resultCode(200)
                    .resultMessage("유저 정보를 반환합니다.")
                    .data(result)
                    .build();
        } catch (EntityNotFoundException e) {
            return CommonResponseDto.<MemberDTO>builder()
                    .resultCode(404)
                    .resultMessage("유저를 찾을 수 없습니다.")
                    .build();
        }
    }*/
/* 지금까지 계속 오류난 이유? 아마도 GetMapping을 1회만 사용해야 하는데 여러번 사용해서?
상세조회와 간단조회 모두 하나의 GetMapping에서 처리했더니 문제가 해결되었다.
* */


    // 유저 상세 정보 조회 & 간단 정보 조회
    @GetMapping("/{userPk}")
    public ResponseEntity<CommonResponseDto<MemberDTO>> getUserInfo(
            @PathVariable String userPk,
            @RequestParam(name = "info_type", defaultValue = "detail") String infoType
    ) {
        try {
            MemberDTO result;
            if ("simple".equals(infoType)) {
                result = memberService.getUserSimpleInfo(userPk);
            } else {
                result = memberService.getUserDetailInfo(userPk);
            }
            return ResponseEntity.ok(CommonResponseDto.<MemberDTO>builder()
                    .resultCode(200)
                    .resultMessage("유저 정보를 반환합니다.")
                    .data(result)
                    .build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponseDto.<MemberDTO>builder()
                            .resultCode(404)
                            .resultMessage("유저를 찾을 수 없습니다.")
                            .data(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.<MemberDTO>builder()
                            .resultCode(500)
                            .resultMessage("서버 에러: " + e.getMessage())
                            .data(null)
                            .build());
        }
    }


   /* //유저 간단 정보 조회하기
   주석처리한 이유 : 상세정보처리에서 모두 해결하였음.
    @GetMapping("{userPk}?info_type=simple")
    public CommonResponseDto<MemberDTO> getUserSimpleInfo(@PathVariable String userPk) {
        try {
            MemberDTO result = memberService.getUserSimpleInfo(userPk);
            return CommonResponseDto.<MemberDTO>builder()
                    .resultCode(200)
                    .resultMessage("간단정보 조회 성공")
                    .data(result)
                    .build();
        } catch (EntityNotFoundException e) {
            return CommonResponseDto.<MemberDTO>builder()
                    .resultCode(404)
                    .resultMessage("유저를 찾을 수 없습니다.")
                    .build();
        }
    }*/


    // 유저 정보 수정 -> 닉네임, 프로필URL, 이메일
    @PutMapping("/{userPk}")
    public ResponseEntity<CommonResponseDto<MemberDTO>> updateUser(@PathVariable String userPk, @RequestBody UpdateMemberRequestDTO request) {
        try {
            MemberDTO result = memberService.updateUser(userPk, request);
            return ResponseEntity.ok(CommonResponseDto.<MemberDTO>builder()
                    .resultCode(200)
                    .resultMessage("유저 정보 수정 성공") //1
                    .data(result)
                    .build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponseDto.<MemberDTO>builder()
                            .resultCode(404)
                            .resultMessage("유저를 찾을 수 없습니다.")
                            .data(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.<MemberDTO>builder()
                            .resultCode(500)
                            .resultMessage("서버 에러: " + e.getMessage())
                            .data(null)
                            .build());
        }
    }
}
