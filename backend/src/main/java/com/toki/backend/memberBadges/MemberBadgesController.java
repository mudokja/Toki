//package com.toki.backend.memberBadges;
//
//
//import com.toki.backend.common.dto.response.CommonResponseDto;
//import com.toki.backend.memberBadges.dto.MemberBadgesDTO;
//import com.toki.backend.memberBadges.service.MemberBadgesService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1")
//@RequiredArgsConstructor
//@CrossOrigin
//public class MemberBadgesController {
//
//    private final MemberBadgesService memberBadgesService;
//
//
//    // 모든 멤버 배지 정보 조회
//    @GetMapping("/badges")
//    public ResponseEntity<CommonResponseDto<List<MemberBadgesDTO>>> getAllMemberBadges() {
//        List<MemberBadgesDTO> allMemberBadges = memberBadgesService.getAllMemberBadges();
//        CommonResponseDto<List<MemberBadgesDTO>> response = CommonResponseDto.<List<MemberBadgesDTO>>builder()
//                .resultCode(200) //성공:200
//                .resultMessage("모든 멤버 배지 정보를 조회하였습니다.")
//                .data(allMemberBadges)
//                .build();
//        return ResponseEntity.ok(response);
//    }
//
//    // 특정 멤버의 모든 배지 정보 조회
//    @GetMapping("/badges/{1}") //지금은 API문서에 특정회원 조회에 대한 내용이 없음 임시로 작성해둔 URI
//    public ResponseEntity<CommonResponseDto<List<MemberBadgesDTO>>> getMemberBadgesByMemberId(@PathVariable Long memberId) {
//        List<MemberBadgesDTO> memberBadgesByMemberId = memberBadgesService.getMemberBadgesByMemberId(memberId);
//        CommonResponseDto<List<MemberBadgesDTO>> response = CommonResponseDto.<List<MemberBadgesDTO>>builder()
//                .resultCode(200)
//                .resultMessage("특정 멤버의 모든 배지 정보를 조회하였습니다.")
//                .data(memberBadgesByMemberId)
//                .build();
//        return ResponseEntity.ok(response);
//    }
//
//    // 멤버에게 배지 부여
//    @PostMapping("/admin/badges")
//    public ResponseEntity<CommonResponseDto<MemberBadgesDTO>> saveBadgeToMember(@RequestBody MemberBadgesDTO memberBadgesDTO) {
//        try {
//            MemberBadgesDTO awardedBadge = memberBadgesService.saveBadgeToMember(memberBadgesDTO);
//            CommonResponseDto<MemberBadgesDTO> response = CommonResponseDto.<MemberBadgesDTO>builder()
//                    .resultCode(200)
//                    .resultMessage("멤버에게 배지를 부여하였습니다.")
//                    .data(awardedBadge)
//                    .build();
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            CommonResponseDto<MemberBadgesDTO> response = CommonResponseDto.<MemberBadgesDTO>builder()
//                    .resultCode(400)
//                    .resultMessage("멤버에게 배지 부여에 실패하였습니다.")
//                    .build();
//            return ResponseEntity.badRequest().body(response);
//        }
//    }
//
//    // 멤버 배지 회수
//    @DeleteMapping("/revoke/{memberBadgeId}")
//    public ResponseEntity<CommonResponseDto<Void>> deleteMemberBadge(@PathVariable Long memberBadgeId) {
//        memberBadgesService.deleteMemberBadge(memberBadgeId);
//        CommonResponseDto<Void> response = CommonResponseDto.<Void>builder()
//                .resultCode(200)
//                .resultMessage("멤버 배지를 회수하였습니다.")
//                .build();
//        return ResponseEntity.ok(response);
//    }
//
//}
