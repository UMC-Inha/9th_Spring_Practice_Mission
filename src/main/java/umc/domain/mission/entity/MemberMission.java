package umc.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.mission.enums.Status;
import umc.domain.member.entity.Member;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member_mission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}
