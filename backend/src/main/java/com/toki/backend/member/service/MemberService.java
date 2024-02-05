package com.toki.backend.member.service;


import com.toki.backend.badge.dto.BadgeDTO;
import com.toki.backend.badge.service.BadgeService;
import com.toki.backend.member.dto.MemberDTO;
import com.toki.backend.member.dto.UpdateMemberRequestDTO;
import com.toki.backend.member.entity.Member;
import com.toki.backend.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BadgeService badgeService ;

    // 유저 정보 상세 조회(이메일, 가입일자, 로그인타입, 배지)
    public MemberDTO getUserDetailInfo(String userPk) {
        // userPk로 회원 정보 조회
        Member member = (Member) memberRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 : " + userPk));

        // 배지 정보 가져오기
        List<BadgeDTO> badges = badgeService.getBadgesByUserPk(userPk);

        // Member 엔티티를 MemberDTO로 변환
        return convertEntityToDTO(member, badges);
    }

    // 유저 정보 간단 조회(닉네임, 고유번호, 프로필 사진)
    public MemberDTO getUserSimpleInfo(String userPk) {
        // userPk로 회원 정보 조회
        Member member = (Member) memberRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 :" + userPk));

        // Member 엔티티를 MemberDTO로 변환
        return convertEntityToSimpleDTO(member);
    }

    private MemberDTO convertEntityToSimpleDTO(Member member) {
        return MemberDTO.builder()
                .userNickName(member.getUserNickName())
                .userTag(member.getUserTag())
                .profileImageUrl(member.getProfileImageUrl())
                .build();
    }

    private MemberDTO convertEntityToDTO(Member member, List<BadgeDTO> badges) {
        return MemberDTO.builder()
                .userPk(member.getUserPk())
                .userId(member.getUserId())
                .userRole(member.getUserRole())
                .userName(member.getUserName())
                .userNickName(member.getUserNickName())
                .userTag(member.getUserTag())
                .createAt(member.getCreateAt())
                .updateAt(member.getUpdateAt())
                .deleteAt(member.getDeleteAt())
                .isDelete(member.getIsDelete())
                .snsType(member.getSnsType())
                .profileImageUrl(member.getProfileImageUrl())
                .birthYear(member.getBirthYear())
                .build();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////
    // 멤버 정보 수정
    public MemberDTO updateUser(String userPk, UpdateMemberRequestDTO request) {
        Member member = (Member) memberRepository.findByUserPk(userPk)
                .orElseThrow(() -> new EntityNotFoundException("UserPk에 맞는 회원이 없습니다 : " + userPk));

        // 업데이트할 정보 설정(닉네임,프로필URL,E-mail)
        member.setUserNickName(request.getUserNickname());
        member.setProfileImageUrl(request.getProfileImageUrl());
        member.setUserEmail(request.getUserEmail());

        // 엔티티 저장
        Member updatedMember = memberRepository.save(member);

        // 업데이트된 정보를 DTO로 변환하여 반환
        return convertEntityToDTO(updatedMember, badgeService.getBadgesByUserPk(userPk));
    }






}
