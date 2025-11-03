package umc.domain.member.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.State;
import umc.domain.mission.entity.Mission;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(nullable = false)
    private LocalDate deadline;

    // 생성 시 deadline 계산
    @PrePersist
    public void calculateDeadline() {
        this.deadline = this.getCreatedAt().toLocalDate().plusDays(mission.getPeriod());
    }
}
