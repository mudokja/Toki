package com.toki.backend.badge.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeDTO {




    private int idx; //배지 인덱스
    private String name; //배지 이름
    private String imageUrl; //배지 이미지 URL





}
