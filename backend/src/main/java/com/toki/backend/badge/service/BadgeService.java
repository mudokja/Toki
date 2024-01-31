package com.toki.backend.badge.service;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.entity.Badge;
import com.toki.backend.badge.repository.BadgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    /**
     * 모든 배지 조회
     *
     * @return 모든 배지 목록
     */
    public List<BadgeDTO> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 특정 배지 조회
     *
     * @param idx 배지 인덱스
     * @return 조회된 배지
     */
    public BadgeDTO getBadgeByIdx(int idx) {
        Badge badge = badgeRepository.findById(idx).orElse(null);
        return badge != null ? convertEntityToDTO(badge) : null;
    }


    /**
     * 배지 등록
     *
     * @param badgeDTO 등록할 배지 정보
     * @return 등록된 배지
     */
    public BadgeDTO saveBadge(BadgeDTO badgeDTO) {
        Badge newBadge = Badge.builder()
                .name(badgeDTO.getName())
                .imageUrl(badgeDTO.getImageUrl())
                .build();
        Badge savedBadge = badgeRepository.save(newBadge);
        return convertEntityToDTO(savedBadge);
    }


    /**
     * 배지 삭제
     *
     * @param idx 삭제할 배지의 인덱스
     */
    public void deleteBadge(int idx) {
        badgeRepository.deleteById(idx);
    }

    /**
     * Entity를 DTO로 변환하는 메서드
     *
     * @param badge 배지 Entity
     * @return 변환된 DTO
     */
    private BadgeDTO convertEntityToDTO(Badge badge) {
        return new BadgeDTO(
                badge.getIdx(),
                badge.getName(),
                badge.getImageUrl()
        );
    }
}
