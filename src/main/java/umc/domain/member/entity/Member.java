package umc.domain.member.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.entity.mapping.MemberTerm;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.SnsType;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 11)
    private String phone_number;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "points", columnDefinition = "integer default -1")
    private Long points;

    @Column(name = "sns_type")
    @Enumerated(EnumType.STRING)
    private SnsType snsType;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
