package com.toki.backend.member.entity;

import com.toki.backend.auth.dto.Role;
import com.toki.backend.badge.entity.Badge;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity //@Entity: JPA 엔티티 클래스임을 나타내는 어노테이션
@Getter //Lombok 어노테이션으로, Getter, Setter 및 ToString 메소드를 자동으로 생성
@Table(name="member") //@Table: 엔티티가 매핑될 테이블의 정보를 지정하는 어노테이션
@Setter //Lombok 어노테이션으로, Getter, Setter 및 ToString 메소드를 자동으로 생성
@ToString //Lombok 어노테이션으로, Getter, Setter 및 ToString 메소드를 자동으로 생성
@EntityListeners(AuditingEntityListener.class) //엔티티의 이벤트를 수신하는 리스너를 정의하는 어노테이션
@NoArgsConstructor //파라미터가 없는 생성자를 자동으로 생성
public class User {

	@Id
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	@GeneratedValue(strategy = GenerationType.UUID)
	String userPk;

	//    배지 정보를 가져오기 위해서 적음.
//	@OneToMany
//	private List<Badge> badges;

	@Column(nullable = false,unique = true)
	String userId;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false,length = 20)
	Role userRole;

	@Column(nullable = false, length = 40)
	String userName;

	String userNickName;
	@Column(nullable = true)
	String userEmail;
	Integer userTag;
	Integer snsType;

	@Column(updatable = false)
	@CreatedDate // Spring Data JPA에서 제공하는 어노테이션으로, 생성일자와 수정일자를 관리
	LocalDateTime createAt;

	@LastModifiedDate //pring Data JPA에서 제공하는 어노테이션으로, 생성일자와 수정일자를 관리
	LocalDateTime updateAt;
	@GeneratedValue(generator = "0")
	Integer isDelete;
	LocalDateTime deleteAt;
	String birthYear;
	String profileImageUrl;
	String selfInfo; //자기소개를 의미합니다.



	//User와 Badge를 연결하기 위함.
	@ManyToMany(fetch = FetchType.LAZY) //@ManyToMany: 다대다 관계를 정의하는 어노테이션
	@JoinTable(name = "user_badges", //@JoinTable: 연결 테이블을 지정 //user_badges : 연결테이블의 이름(다대다)
			joinColumns = @JoinColumn(name = "user_tag"),// 현재 엔티티의 외래 키 컬럼명 지정
			inverseJoinColumns = @JoinColumn(name = "badge_idx"))// 대상 엔티티의 외래 키 컬럼명 지정
	//유저 테이블의 유저태그를 가져와서 배지테이블의 배지 인덱스와 연결
	private List<Badge> badge = new ArrayList<>(); //배지들을 반환한다.




	@Builder
	public User(String userPk, String userNickName, String birthYear, String profileImageUrl, String userId, Role userRole, String userName, String userEmail, Integer userTag, Integer snsType) {
		this.userPk = userPk;
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userNickName = userNickName;
		this.userEmail = userEmail;
		this.birthYear=birthYear;
		this.snsType= snsType;
		this.profileImageUrl=profileImageUrl;
		this.userTag = userTag;
	}
}
