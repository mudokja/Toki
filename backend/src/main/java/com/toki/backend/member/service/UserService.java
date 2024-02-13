package com.toki.backend.member.service;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.service.BadgeService;
import com.toki.backend.member.dto.OtherUserDTO;
import com.toki.backend.member.dto.UserDTO;
import com.toki.backend.member.dto.UpdateUserRequestDTO;
import com.toki.backend.member.entity.User;
import com.toki.backend.member.repository.UserRepository;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BadgeService badgeService ;


    //상세조회? : 닉네임, 유저태그, sns타입, 생성일자, 프로필 이미지 URL, 자기소개, 배지 (7개)

    public UserDTO getUserDetailInfo(String userPk) {
        // userPk로 회원 정보 조회
        User user = userRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 해당하는 회원이 없습니다 : " + userPk));

        // 배지 정보 가져오기
        List<BadgeDTO> badges = badgeService.getBadgesByUserPk(userPk);

        // Member 엔티티를 MemberDTO로 변환
        return convertEntityToDTO(user, badges);
    }

    // 유저 정보 간단 조회(닉네임, 고유번호, 프로필 사진)
    public UserDTO getUserSimpleInfo(String userPk) {
        // userPk로 회원 정보 조회
        User user = (User) userRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 해당하는 회원이 없습니다 :" + userPk));

        // Member 엔티티를 MemberDTO로 변환
        return convertEntityToSimpleDTO(user);
    }

    // 다른 사용자의 정보 조회
    public OtherUserDTO getOtherUserInfo(String userPk) {
        Optional<User> userOptional = userRepository.findByUserPk(userPk);
        if (!userOptional.isPresent()) {
            throw new EntityNotFoundException("UserPk에 해당하는 회원이 없습니다. " + userPk);
        }

        User user = userOptional.get();

        // 필요한 필드만 OtherUserDTO 객체에 설정하여 반환
        return OtherUserDTO.builder()
                .userRole(String.valueOf(user.getUserRole())) //오류 발생하여 valueOf로 감쌌습니다.
                .userNickName(user.getUserNickName())
                .userTag(user.getUserTag())
                .selfInfo(user.getSelfInfo()) // 자기소개 정보 추가
                .build();
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
                .selfInfo(user.getSelfInfo()) // 자기소개 정보 추가
                .build();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // 멤버 정보 수정
    public UserDTO updateUser(String userPk, UpdateUserRequestDTO request) {
        User user = (User) userRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 : " + userPk));

        // 업데이트할 정보 설정(닉네임,프로필URL,E-mail대신에 셀프인포(자기소개)로 대체)
        user.setUserNickName(request.getUserNickname());
        user.setProfileImageUrl(request.getProfileImageUrl());
        user.setSelfInfo(request.getSelfInfo()); // 자기소개 정보 추가

        // 엔티티 저장
        User updatedUser = userRepository.save(user);

        // 업데이트된 정보를 DTO로 변환하여 반환
        return convertEntityToDTO(updatedUser, badgeService.getBadgesByUserPk(userPk));
    }











}



