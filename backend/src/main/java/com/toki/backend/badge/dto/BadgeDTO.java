package com.toki.backend.badge.dto;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BadgeDTO {




    private int idx;
    private String name;
    private String imageUrl;

    //상세 유저 조회에서 필요한 내용?



}
