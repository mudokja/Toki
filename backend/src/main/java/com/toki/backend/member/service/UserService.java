package com.toki.backend.member.service;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.service.BadgeService;
import com.toki.backend.member.dto.UserDTO;
import com.toki.backend.member.dto.UpdateUserRequestDTO;
import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BadgeService badgeService ;

    // 유저 정보 상세 조회(이메일, 가입일자, 로그인타입, 배지)
    public UserDTO getUserDetailInfo(String userPk) {
        // userPk로 회원 정보 조회
        User user = (User) userRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 : " + userPk));

        // 배지 정보 가져오기
        List<BadgeDTO> badges = badgeService.getBadgesByUserPk(userPk);

        // Member 엔티티를 MemberDTO로 변환
        return convertEntityToDTO(user, badges);
    }

    // 유저 정보 간단 조회(닉네임, 고유번호, 프로필 사진)
    public UserDTO getUserSimpleInfo(String userPk) {
        // userPk로 회원 정보 조회
        User user = (User) userRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 :" + userPk));

        // Member 엔티티를 MemberDTO로 변환
        return convertEntityToSimpleDTO(user);
    }

    private UserDTO convertEntityToSimpleDTO(User user) {
        return UserDTO.builder()
                .userNickName(user.getUserNickName())
                .userTag(user.getUserTag())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    private UserDTO convertEntityToDTO(User user, List<BadgeDTO> badges) {
        return UserDTO.builder()
                .userPk(user.getUserPk())
                .userId(user.getUserId())
                .userRole(user.getUserRole().toString()) // <- 이 부분이 오류발생하여 toString추가하였습니다.
                .userName(user.getUserName())
                .userNickName(user.getUserNickName())
                .userTag(user.getUserTag())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .deleteAt(user.getDeleteAt())
                .isDelete(user.getIsDelete())
                .snsType(user.getSnsType())
                .profileImageUrl(user.getProfileImageUrl())
                .birthYear(user.getBirthYear())
                .build();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // 멤버 정보 수정
    public UserDTO updateUser(String userPk, UpdateUserRequestDTO request) {
        User user = (User) userRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 : " + userPk));

        // 업데이트할 정보 설정(닉네임,프로필URL,E-mail)
        user.setUserNickName(request.getUserNickname());
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setUserEmail(request.getUserEmail());

        // 엔티티 저장
        User updatedUser = userRepository.save(user);

        // 업데이트된 정보를 DTO로 변환하여 반환
        return convertEntityToDTO(updatedUser, badgeService.getBadgesByUserPk(userPk));
    }









}
