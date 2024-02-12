package com.toki.backend.message.api;


import com.toki.backend.auth.service.CustomOAuth2User;
import com.toki.backend.common.dto.response.CommonResponseDto;
import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;
import com.toki.backend.message.dto.request.MessageSendRequestDto;
import com.toki.backend.message.dto.response.MessageResponseDto;
import com.toki.backend.message.entity.Message;
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

    // 내가 받은 쪽지 목록 조회
    @GetMapping
    public ResponseEntity<CommonResponseDto<Object>> findMessageList(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
                                                                     @RequestParam(required = false, defaultValue = "0") int page) {

        User myUser = userRepository.findById(userPrincipal.getName()).get();

        Page<MessageResponseDto> responseData = messageService.getMessageListByToUser(myUser, page);

        return ResponseEntity.ok().body(CommonResponseDto.builder()
                .resultCode(200)
                .resultMessage("받은 쪽지 조회에 성공했습니다.")
                .data(responseData)
                .build());
    }


    // 쪽지 보내기
    @PostMapping
    public ResponseEntity<CommonResponseDto<Object>> sendMessage(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
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



    @DeleteMapping("/{messagePk}")
    public ResponseEntity<?> deleteMessage(@AuthenticationPrincipal CustomOAuth2User userPrincipal,
                                           @PathVariable Long messagePk) {
        String toUserPk = userPrincipal.getName();
        // messagePk 로 조회,
        Message message = messageService.getMessageByMessagePk(messagePk);

        // 없는 pk로 조회했을 시
        if (message == null) {
            return ResponseEntity.ok().body(
                    CommonResponseDto.builder()
                            .resultCode(404)
                            .resultMessage("해당 메세지가 없습니다.")
                            .build());
        }

        // 사용자가 해당 메세지를 받은 사람이 아닐 때
        else if (!Objects.equals(message.getToUser().getUserPk(), toUserPk)) {
            return ResponseEntity.ok().body(
                    CommonResponseDto.builder()
                            .resultCode(403)
                            .resultMessage("허가된 사용자가 아닙니다. (당신이 받은 메세지가 아닙니다)")
                            .build());
        }

        else  {
            messageService.deleteMessage(messagePk);
            return ResponseEntity.ok().body(
                    CommonResponseDto.builder()
                            .resultCode(204)
                            .resultMessage("메세지가 성공적으로 삭제되었습니다.")
                            .build());
        }
    }



}
