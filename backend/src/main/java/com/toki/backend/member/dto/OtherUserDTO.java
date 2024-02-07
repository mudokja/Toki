package com.toki.backend.member.dto;


import lombok.*;

import java.time.LocalDateTime;

// 다른 사용자 정보 조회용 DTO 클래스
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtherUserDTO { //여기에선 타인의 정보를 조회할 시 반환해야할 필드를 적는다.
    //
    private String userRole;          // 사용자 역할 (ADMIN, USER 등)
    private String userNickName;    // 사용자 닉네임
    private Integer userTag;         // 사용자 식별을 위한 태그
    private String selfInfo;        // 자기소개

    //위와같이 4가지 반환.



}
