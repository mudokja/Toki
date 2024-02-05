package com.toki.backend.member;


import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.member.dto.MemberDTO;
import com.toki.backend.member.dto.UpdateMemberRequestDTO;
import com.toki.backend.member.service.MemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;


    //유저 상세 정보 조회
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
    }


    //유저 간단 정보 조회하기
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
    }


    //유저 정보 수정 ->닉네임, 프로필URL,이메일
    @PutMapping("/{userPk}")
    public CommonResponseDto<MemberDTO> updateUser(@PathVariable String userPk, @RequestBody UpdateMemberRequestDTO request) {
        try {
            MemberDTO result = memberService.updateUser(userPk, request);
            return CommonResponseDto.<MemberDTO>builder()
                    .resultCode(200)
                    .resultMessage("유저 정보 수정 성공")
                    .data(result)
                    .build();
        } catch (EntityNotFoundException e) {
            return CommonResponseDto.<MemberDTO>builder()
                    .resultCode(404)
                    .resultMessage("유저를 찾을 수 없습니다.")
                    .build();
        }
    }
}
