package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.inquiry.entity.Inquiry;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.Reception;
import umc.domain.member.enums.Gender;
import umc.domain.region.entity.Region;
import umc.domain.review.entity.Review;
import umc.global.auth.enums.Role;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length =3, nullable = false)
    private String name;

    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "status" , nullable = false) //처음엔 true로
    @Builder.Default
    private Boolean status = true;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;//0으로

    @Column(name = "reception", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Reception reception = Reception.REFUSE;

    @Column(name = "address" ,length = 100)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email" ,length = 50)
    private String email;

    @Column(name = "secession_date")
    private LocalDateTime secession_date;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Alarm> alarms = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Inquiry> inquiries = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberMission> memberMissions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoods = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

}
