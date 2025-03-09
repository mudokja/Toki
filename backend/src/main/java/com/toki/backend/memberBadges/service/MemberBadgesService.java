//package com.toki.backend.memberBadges.service;
//
//import com.toki.backend.badge.dto.BadgeDTO;
//import com.toki.backend.badge.entity.Badge;
//import com.toki.backend.badge.service.BadgeService;
//import com.toki.backend.member.dto.UserDTO;
//import com.toki.backend.member.entity.User;
//import com.toki.backend.member.repository.UserRepository;
//import com.toki.backend.memberBadges.dto.MemberBadgesDTO;
//
//import com.toki.backend.memberBadges.entitiy.MemberBadges;
//import com.toki.backend.memberBadges.repository.MemberBadgesRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class MemberBadgesService{
//    private final MemberBadgesRepository memberBadgesRepository;
//    private final BadgeService badgeService;
//    private final UserRepository userRepository;
//
//    // 모든 멤버의 배지 조회
//    public List<MemberBadgesDTO> getAllMembersBadges() {
//        List<MemberBadges> memberBadgesList = memberBadgesRepository.findAll();
//        return memberBadgesList.stream()
//                .map(this::convertEntityToDTO)
//                .collect(Collectors.toList());
//    }
//
//
//
//    // 특정 멤버의 배지 조회
//    public List<BadgeDTO> getMemberBadges(String userTag) {
//        List<MemberBadges> memberBadgesList = memberBadgesRepository.findByUserTag(userTag);
//        return memberBadgesList.stream()
//                .map(MemberBadges::getBadge)
//                .map(badgeService::convertEntityToDTO)
//                .collect(Collectors.toList());
//    }
//
//
//
//    // 배지 등록
//    public MemberBadgesDTO addBadgeForMember(String userTag, Long badgeId) {
//        User user = userRepository.findByUserTag(userTag)
//                .orElseThrow(() -> new EntityNotFoundException("UserTag에 해당하는 회원이 없습니다. " + userTag));
//
//        Badge badge = badgeService.getBadgeById(badgeId);
//        if (badge == null) {
//            throw new EntityNotFoundException("배지를 찾을 수 없습니다. ID: " + badgeId);
//        }
//
//        MemberBadges memberBadges = new MemberBadges();
//        memberBadges.setUser(user);
//        memberBadges.setBadge(badge);
//        memberBadges.setCreateAt(LocalDateTime.now());
//
//        MemberBadges savedMemberBadges = memberBadgesRepository.save(memberBadges);
//        return convertEntityToDTO(savedMemberBadges);
//    }
//
//
//
//
//
//
//    // 배지 삭제
//    public void removeBadgeForMember(String userTag, Long badgeId) {
//        MemberBadges memberBadges = memberBadgesRepository.findByUserTagAndBadgeId(userTag, badgeId)
//                .orElseThrow(() -> new EntityNotFoundException("해당 사용자가 해당 배지를 가지고 있지 않습니다."));
//
//        memberBadgesRepository.delete(memberBadges);
//    }
//
//
//
//
//
//    // 엔티티를 DTO로 변환하는 메서드
//    private MemberBadgesDTO convertEntityToDTO(MemberBadges memberBadges) {
//        return MemberBadgesDTO.builder()
//                .id(memberBadges.getId())
//                .user(UserDTO.builder()
//                        .userTag(memberBadges.getUser().getUserTag())
//                        .build())
//                .badge(badgeService.convertEntityToDTO(memberBadges.getBadge()))
//                .createAt(memberBadges.getCreateAt())
//                .build();
//    }
//
//
//
//
//}