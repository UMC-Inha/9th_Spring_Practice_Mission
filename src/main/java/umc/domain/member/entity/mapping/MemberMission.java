package umc.domain.member.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.Status;
import umc.domain.misson.entity.Mission;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "end_date", nullable = false)
    private LocalDate end_date;

    @Column(name = "completion_date")
    private LocalDate completion_date;
}
