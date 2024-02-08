package com.toki.backend.badge.service;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.entity.Badge;
import com.toki.backend.badge.repository.BadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;


    //모든 배지 조회

    public List<BadgeDTO> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }


     //특정 회원이 가지고 있는 배지를 조회.
    public BadgeDTO getBadgeByIdx(String userTag) {
        Badge badge = badgeRepository.findById(Integer.valueOf(userTag)).orElse(null);
        return badge != null ? convertEntityToDTO(badge) : null;
    }


    //배지 등록
    public BadgeDTO saveBadge(BadgeDTO badgeDTO) {
        Badge newBadge = Badge.builder()
                .name(badgeDTO.getName())
                .imageUrl(badgeDTO.getImageUrl())
                .build();
        Badge savedBadge = badgeRepository.save(newBadge);
        return convertEntityToDTO(savedBadge);
    }


    //배지 삭제
    public void deleteBadge(int idx) {
        badgeRepository.deleteById(idx);
    }


    //특정 회원의 배지 조회하기
    public List<BadgeDTO> findByUserTag(String userTag) {
        List<Badge> badges = badgeRepository.findByUserTag(userTag);

        //배지 엔티티를 DTO로 바꿔서 반환하기.

        return badges.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

     // Entity를 DTO로 변환하는 메서드

    private BadgeDTO convertEntityToDTO(Badge badge) {
        return new BadgeDTO(
                badge.getIdx(),
                badge.getName(),
                badge.getImageUrl()
        );
    }

    public List<BadgeDTO> getBadgesByUserPk(String userPk) {
        BadgeDTO badge1 = BadgeDTO.builder()
                .idx(1)
                .name("활동왕")
                .imageUrl("사진1")
                .build();

        BadgeDTO badge2 = BadgeDTO.builder()
                .idx(2)
                .name("수다쟁이")
                .imageUrl("사진2")
                .build();

        BadgeDTO badge3 = BadgeDTO.builder()
                .idx(3)
                .name("척척박사")
                .imageUrl("사진3")  // 예시 지정해주어야 함
                .build();
        return Arrays.asList(badge1, badge2, badge3);
    }



}
