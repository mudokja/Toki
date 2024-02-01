package com.toki.backend.memberBadges.service;


import com.toki.backend.badge.entity.Badge;
import com.toki.backend.badge.repository.BadgeRepository;
import com.toki.backend.memberBadges.dto.MemberBadgesDTO;
import com.toki.backend.memberBadges.entitiy.MemberBadges;
import com.toki.backend.memberBadges.repository.MemberBadgesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberBadgesService {

    private final MemberBadgesRepository memberBadgesRepository;
    private final MemberRepository memberRepository;
    private final BadgeRepository badgeRepository;

    /**
     * 모든 멤버 배지 정보 조회
     */
    public List<MemberBadgesDTO> getAllMemberBadges() {
        List<MemberBadges> memberBadgesList = memberBadgesRepository.findAll();
        return memberBadgesList.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }



    /**
     * 특정 멤버 배지 조회
     */
    public MemberBadgesDTO getMemberBadgesById(Long id) {
        MemberBadges memberBadges = memberBadgesRepository.findById(id).orElse(null);
        return memberBadges != null ? convertEntityToDTO(memberBadges) : null;
    }

    /**
     * 멤버 배지 등록
     */
    public MemberBadgesDTO saveMemberBadges(MemberBadgesDTO memberBadgesDTO) {
        Member member = memberRepository.findById(memberBadgesDTO.getMemberId()).orElse(null);
        Badge badge = badgeRepository.findById(memberBadgesDTO.getBadgeIdx()).orElse(null);

        if (member == null || badge == null) {
            // 멤버 또는 배지가 존재하지 않을 경우 예외 처리
            throw new IllegalArgumentException("무효한 회원이나 배지입니다.");
        }

        MemberBadges newMemberBadges = MemberBadges.builder()
                .member(member)
                .badge(badge)
                .createAt(memberBadgesDTO.getCreateAt())
                .build();

        MemberBadges savedMemberBadges = memberBadgesRepository.save(newMemberBadges);
        return convertEntityToDTO(savedMemberBadges);
    }

    /**
     * 멤버 배지 삭제
     *
     * @param id 삭제할 멤버 배지의 식별자
     */
    public void deleteMemberBadges(Long id) {
        memberBadgesRepository.deleteById(id);
    }


    /**
     * Entity를 DTO로 변환하는 메서드
     */
    private MemberBadgesDTO convertEntityToDTO(MemberBadges memberBadges) {
        return MemberBadgesDTO.builder()
                .id(memberBadges.getId())
                .memberId(memberBadges.getMember().getId())
                .badgeIdx(memberBadges.getBadge().getIdx())
                .createAt(memberBadges.getCreateAt())
                .build();
    }

    /**
     * DTO를 Entity로 변환하는 메서드
     */
    private MemberBadges convertDTOToEntity(MemberBadgesDTO memberBadgesDTO) {
        return MemberBadges.builder()
                .id(memberBadgesDTO.getId())
                .member(new Member(memberBadgesDTO.getMemberId()))
                .badge(new Badge(memberBadgesDTO.getBadgeIdx()))
                .createAt(memberBadgesDTO.getCreateAt())
                .build();
    }
}
