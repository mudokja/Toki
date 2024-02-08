//package com.toki.backend.memberBadges.repository;
//
//import com.toki.backend.badge.dto.BadgeDTO;
//import com.toki.backend.memberBadges.dto.MemberBadgesDTO;
//import com.toki.backend.memberBadges.entitiy.MemberBadges;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//
////@Repository
////public interface MemberBadgesRepository extends JpaRepository<MemberBadges, Long> {
////
////
////
////}
//
///*2024.02.07 다시 작성함.*/
//
//@Repository
//public interface MemberBadgesRepository extends JpaRepository<MemberBadges, Long> {
//
//    List<MemberBadges> findByUserPk(String userPk);
//
//    Optional<Object> findByUserPkAndBadgeId(String userPk, Long badgeId);
//
//    List<MemberBadges> findByUserTag(String userTag);
//
//    public List<BadgeDTO> getBadge();
//
//}
