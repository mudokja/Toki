package com.toki.backend.badge.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeDTO {
//다른 곳으로 배지 정보를 전달할 때 사용

    private int idx; //배지 인덱스
    private String name; //배지 이름
    private String imageUrl; //배지 이미지 URL


}
