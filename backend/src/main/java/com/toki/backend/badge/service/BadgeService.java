package com.toki.backend.badge.service;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.entity.Badge;
import com.toki.backend.badge.repository.BadgeRepository;
import com.toki.backend.member.dto.UserDTO;
import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserRepository userRepository;
//API 명세서 : 1) 배지 정보 추가. 2) 전체 등록된 배지를 조회 3) 사용자 뱃지 등록

    //1.모든 배지 조회 : 데이터베이스에서 모든 배지를 조회하여 리스트로 반환

    public List<BadgeDTO> getAllBadges() {
        List<Badge> badges = badgeRepository.findAll();
        return badges.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }


    //2.배지 등록: 새로운 배지를 등록하고, 등록된 배지 정보를 반환
    public BadgeDTO saveBadge(BadgeDTO badgeDTO) {
        Badge newBadge = Badge.builder()
                .name(badgeDTO.getName())
                .imageUrl(badgeDTO.getImageUrl())
                .build();
        Badge savedBadge = badgeRepository.save(newBadge);
        return convertEntityToDTO(savedBadge);
    }


    //3.배지 삭제: 배지 인덱스에 해당하는 배지를 데이터베이스에서 삭제
    public void deleteBadge(int idx) {
        badgeRepository.deleteById(idx);
    }



     //4.특정 회원이 가지고 있는 배지를 조회 : 회원 태그에 해당하는 배지를 조회하여 반환
    public BadgeDTO getBadgeByIdx(String userTag) {
        Badge badge = badgeRepository.findById(Integer.valueOf(userTag)).orElse(null);
        return badge != null ? convertEntityToDTO(badge) : null;
    }

    // 5. 사용자에게 배지 추가 : 배지 인덱스와 사용자 태그에 해당하는 사용자에게 배지를 추가
    public void addBadgeToUser(int badgeIdx, String userTag) {
        Badge badge = badgeRepository.findById(badgeIdx)
                .orElseThrow(() -> new EntityNotFoundException(badgeIdx + " 인덱스를 찾을 수 없습니다."));

        // 배지가 존재하는 경우 사용자에게 배지를 추가
        User user = userRepository.findByUserTag(Integer.valueOf(userTag))//String타입이므로 감쌌습니다.
                .orElseThrow(() -> new EntityNotFoundException(userTag + " 유저태그를 찾을 수 없습니다."));

//       사용자의 배지 목록에 해당 배지를 추가하는 코드를 작성


        List<Badge> userBadges = user.getBadges(); // 사용자의 현재 배지 목록 가져오기
        userBadges.add(badge); // 배지 추가
        user.setBadges(userBadges); // 업데이트된 배지 목록 설정

        // 사용자 엔티티 저장 (배지 추가 반영)
        userRepository.save(user);
    }

    // 6. 사용자로부터 배지 제거 : 배지 인덱스와 사용자 태그에 해당하는 사용자로부터 배지를 제거
    public void deleteBadgeFromUser(int badgeIdx, String userTag) {
        Badge badge = badgeRepository.findById(badgeIdx)
                .orElseThrow(() -> new EntityNotFoundException(badgeIdx + " 인덱스를 찾을 수 없습니다."));

        // 배지가 존재하는 경우 사용자로부터 배지를 제거
        User user = userRepository.findByUserTag(Integer.valueOf(userTag))
                .orElseThrow(() -> new EntityNotFoundException(userTag + " 유저태그를 찾을 수 없습니다."));


        // 사용자로부터 배지 제거
        List<Badge> userBadges = user.getBadges(); // 사용자의 현재 배지 목록 가져오기
        userBadges.removeIf(b -> b.getIdx() == badgeIdx); // 배지 제거
        user.setBadges(userBadges); // 업데이트된 배지 목록 설정

        // 사용자 엔티티 저장 (배지 제거 반영)
        userRepository.save(user);
    }

    // 7. 특정 배지를 가진 모든 사용자 조회
    public List<UserDTO> getUsersByBadge(int badgeIdx) {
        // 배지 인덱스로 해당하는 배지를 찾기.
        Badge badge = badgeRepository.findById(badgeIdx)
                .orElseThrow(() -> new EntityNotFoundException(badgeIdx + " : 배지 인덱스를 찾을 수 없습니다."));

        // 해당 배지를 가진 모든 사용자 조회
        List<User> users = userRepository.findByBadge(badge);

        // 각 사용자를 DTO로 변환
        List<UserDTO> userDTOs = users.stream()
                .map(this::convertUserToDTO) // 엔티티를 DTO로 변환
                .collect(Collectors.toList());

        return userDTOs;
    }


    // 사용자 엔티티를 사용자 DTO로 변환하는 메서드
    private UserDTO convertUserToDTO(User user) {
        return new UserDTO(
                user.getUserTag(),
                user.getUserName(),
                user.getUserEmail(),
                user.getBadges().stream()
                        .map(badge -> new BadgeDTO(badge.getIdx(), badge.getName(), badge.getImageUrl()))
                        .collect(Collectors.toList())
        );
    }

    // Entity를 DTO로 변환하는 메서드

    private BadgeDTO convertEntityToDTO(Badge badge) {
        return new BadgeDTO(
                badge.getIdx(),
                badge.getName(),
                badge.getImageUrl()
        );



    }





}
