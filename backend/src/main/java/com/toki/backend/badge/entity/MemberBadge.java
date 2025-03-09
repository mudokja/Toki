//package com.toki.backend.badge.entity;
//
//import com.toki.backend.member.entity.User;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "member_badge")
//@Builder
//public class MemberBadge {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private User user; //아직 수정 안되었음.....
//
//    @ManyToOne
//    @JoinColumn(name = "badge_id")
//    private Badge badge;
//
//    @Column
//    private LocalDateTime creatAt;
//}
