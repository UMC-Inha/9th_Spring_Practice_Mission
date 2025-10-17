package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberTerm;
import umc.domain.member.enums.Address;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Social_type;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.review.entity.Review;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "member")
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender= Gender.NONE;

    @Column(name= "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address;

    @Column(name = "detail_address", nullable = false)
    private String detail_address;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Social_type social_type;

    @Column(name = "social_uid", nullable = false)
    private String social_uid;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "isdeleted")
    private Boolean isdeleted;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoods = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberMission> MemberMissions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberTerm> member_terms = new ArrayList<>();



}
