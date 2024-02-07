package com.toki.backend.message.api;


import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;
import com.toki.backend.message.dto.request.MessageSendRequestDto;
import com.toki.backend.message.dto.response.MessageByFromUserDto;
import com.toki.backend.message.dto.response.MessageByToUserDto;
import com.toki.backend.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    // 내 쪽지 목록 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<Object>> findMessageList(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
                                                                     @RequestParam String type, @RequestParam int page) {

        User myUser = userRepository.findById(userPrincipal.getName()).get();

        // 보낸 쪽지만 조회
        if (Objects.equals(type, "send")) {

            Page<MessageByFromUserDto> responseData = messageService.getMessageListByFromUser(myUser, page);

            return ResponseEntity.ok().body(CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("보낸 쪽지 조회에 성공했습니다.")
                    .data(responseData)
                    .build()
            );
        }
        // 받은 쪽지만 조회
        else if (Objects.equals(type, "receive")){

            Page<MessageByToUserDto> responseData = messageService.getMessageListByToUser(myUser, page);

            return ResponseEntity.ok().body(CommonResponseDto.builder()
                    .resultCode(200)
                    .resultMessage("보낸 쪽지 조회에 성공했습니다.")
                    .data(responseData)
                    .build()
            );
        }
        else {
            return ResponseEntity.ok().body(CommonResponseDto.builder()
                    .resultCode(400)
                    .resultMessage("잘못된 요청입니다.")
                    .build()
            );
        }
    }


    // 쪽지 보내기
    @PostMapping
    public ResponseEntity<?> sendMessage(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
                                         @RequestBody MessageSendRequestDto messageSendRequestDto) {
        String fromUserPk = userPrincipal.getName();

        messageService.saveMessage(messageSendRequestDto, fromUserPk);

        return ResponseEntity.ok().body(
                CommonResponseDto.builder()
                        .resultCode(204)
                        .resultMessage("쪽지를 성공적으로 전송하였습니다.")
                        .build()
        );
    }

    // 쪽지 상세 조회(쪽지 읽기)
    // 현재 쪽지 리스트에서 쪽지의 대부분을 반환해주고 있어서 실질적으로 "상세" 조회가 아님.
    // 읽음 처리를 해준다는게 유일한 기능인데 이런게 필요한가?

//    @GetMapping("/{messagePk}")
//    public ResponseEntity<?> findMessage(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
//                                         @PathVariable Long messagePk) {
//        String toUserPk = userPrincipal.getName();
//        messageService.updateMessageByIsRead
//        return ResponseEntity.ok().build();
//    }


    // 쪽지 삭제
    // 쪽지를 삭제?
//    @DeleteMapping("/{messagePk}")
//    public ResponseEntity<?> deleteMessage(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
//                                           @PathVariable Long messagePk) {
//
//        return ResponseEntity.ok().build();
//    }



}
