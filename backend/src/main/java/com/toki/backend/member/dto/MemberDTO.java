package com.toki.backend.member.dto;


import com.toki.backend.auth.dto.Role;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    private String userPk;          // 사용자의 주요 식별키
    private String userId;          // 사용자 아이디
    private String userRole;          // 사용자 역할 (ADMIN, USER 등)
    private String userName;        // 사용자 실명
    private String userNickName;    // 사용자 닉네임
    private String userEmail;        // 사용자 이메일 주소
    private String userTag;         // 사용자 식별을 위한 태그
    private Integer snsType;        // 소셜 미디어 타입
    private LocalDateTime createAt; // 사용자 생성일자
    private LocalDateTime updateAt; // 사용자 정보 수정일자
    private Integer isDelete;       // 사용자 삭제 여부 (0: 삭제 안 됨, 1: 삭제됨)
    private LocalDateTime deleteAt; // 사용자 삭제 일자
    private String birthYear;       // 사용자 생년월일
    private String profileImageUrl; // 프로필 이미지 URL


}


