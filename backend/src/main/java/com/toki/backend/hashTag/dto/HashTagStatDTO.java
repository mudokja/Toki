package com.toki.backend.hashTag.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HashTagStatDTO {


    private int idx;
    private String tagName;
    private LocalDateTime createAt;
    private int score; //해시태그가 없으면 새로 생성하고, 있으면 스코어를 증가시킨다.


}
