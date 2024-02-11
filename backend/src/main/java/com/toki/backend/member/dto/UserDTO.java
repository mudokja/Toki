package com.toki.backend.member.dto;


import com.toki.backend.badge.dto.BadgeDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO { //상세조회? : 닉네임, 유저태그, sns타입, 생성일자, 프로필 이미지 URL, 자기소개 (6개)
//사용자 정보를 담는 DTO 클래스
    private String userPk;          // 사용자의 주요 식별키
    private String userId;          // 사용자 아이디
    private String userRole;          // 사용자 역할 (ADMIN, USER 등)
    private String userName;        // 사용자 실명
    private String userNickName;    // 사용자 닉네임 -> 상세
    private String userEmail;        // 사용자 이메일 주소
    private Integer userTag;         // 사용자 식별을 위한 태그 -> 상세
    private Integer snsType;        // 소셜 미디어 타입 -> 상세
    private LocalDateTime createAt; // 사용자 생성일자 ->상세
    private LocalDateTime updateAt; // 사용자 정보 수정일자
    private Integer isDelete;       // 사용자 삭제 여부 (0: 삭제 안 됨, 1: 삭제됨)
    private LocalDateTime deleteAt; // 사용자 삭제 일자
    private String birthYear;       // 사용자 생년월일
    private String profileImageUrl; // 프로필 이미지 URL -> 상세
    private String selfInfo;        // 자기소개 -> 상세
    private List<BadgeDTO> badges;      // 사용자가 보유한 배지 목록


}


