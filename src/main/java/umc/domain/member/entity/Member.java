package umc.domain.member.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.SnsType;
import umc.global.auth.enums.Role;
import umc.global.entity.BaseEntity;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

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
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private SnsType snsType = SnsType.LOCAL;

    @Builder.Default
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
