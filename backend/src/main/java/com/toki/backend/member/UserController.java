package com.toki.backend.member;

import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.member.dto.OtherUserDTO;
import com.toki.backend.member.dto.UpdateUserRequestDTO;
import com.toki.backend.member.dto.UserDTO;
import com.toki.backend.member.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {


//    2024,02,07 유저정보조회 수정하였음 -> 다른 사람 정보를 조회시, 민감정보는 제외하고 DTO에 담는다.
    private final UserService userService;

    // 유저 정보 조회
    @GetMapping("/{userPk}")
    public ResponseEntity<CommonResponseDto<UserDTO>> getUserInfo(
            @RequestParam(name = "info_type", defaultValue = "detail") String infoType,
            Principal principal
    ) {
        try {
            String currentUserPk = principal.getName();

            UserDTO result;
            if ("simple".equals(infoType)) {
                // 간단한 정보 조회 요청일 경우
                result = userService.getUserSimpleInfo(currentUserPk);
            } else {
                // 상세 정보 조회 요청일 경우
                result = userService.getUserDetailInfo(currentUserPk);
            }

            return ResponseEntity.ok(CommonResponseDto.<UserDTO>builder()
                    .resultCode(200)
                    .resultMessage("유저 정보를 조회합니다.")
                    .data(result)
                    .build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    // 요청한 유저를 찾을 수 없는 경우
                    .body(CommonResponseDto.<UserDTO>builder()
                            .resultCode(404)
                            .resultMessage("유저를 찾을 수 없습니다.")
                            .data(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    // 서버 에러 발생 시
                    .body(CommonResponseDto.<UserDTO>builder()
                            .resultCode(500)
                            .resultMessage("서버 에러: " + e.getMessage())
                            .data(null)
                            .build());
        }
    }

    // 다른 사용자 정보 조회
    @GetMapping("/others/{userPk}")
    public ResponseEntity<CommonResponseDto<OtherUserDTO>> getOtherUserInfo(@PathVariable String userPk) {
        try {
            OtherUserDTO result = userService.getOtherUserInfo(userPk);
            return ResponseEntity.ok(CommonResponseDto.<OtherUserDTO>builder()
                    .resultCode(200)
                    .resultMessage("다른 사용자 정보를 조회합니다.")
                    .data(result)
                    .build());
        } catch (EntityNotFoundException e) {
            // 요청한 유저를 찾을 수 없는 경우
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponseDto.<OtherUserDTO>builder()
                            .resultCode(404)
                            .resultMessage("유저를 찾을 수 없습니다.")
                            .data(null)
                            .build());
        } catch (Exception e) {
            // 서버 에러 발생 시
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.<OtherUserDTO>builder()
                            .resultCode(500)
                            .resultMessage("서버 에러: " + e.getMessage())
                            .data(null)
                            .build());
        }
    }

    // 유저 정보 수정
    @PutMapping("/{userPk}")
    public ResponseEntity<CommonResponseDto<UserDTO>> updateUser(@PathVariable String userPk, @RequestBody UpdateUserRequestDTO request) {
        try {
            UserDTO result = userService.updateUser(userPk, request);
            return ResponseEntity.ok(CommonResponseDto.<UserDTO>builder()
                    .resultCode(200)
                    .resultMessage("유저 정보를 수정합니다.")
                    .data(result)
                    .build());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponseDto.<UserDTO>builder()
                            .resultCode(404)
                            .resultMessage("유저를 찾을 수 없습니다.")
                            .data(null)
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(CommonResponseDto.<UserDTO>builder()
                            .resultCode(500)
                            .resultMessage("서버 에러: " + e.getMessage())
                            .data(null)
                            .build());
        }
    }
}
