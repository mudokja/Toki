package com.toki.backend.memberBadges.dto;

import com.toki.backend.badge.dto.BadgeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberBadgesDTO {


    private Long id;
    private MemberDTO member;
    private BadgeDTO badge;
    private LocalDateTime createAt;



}
