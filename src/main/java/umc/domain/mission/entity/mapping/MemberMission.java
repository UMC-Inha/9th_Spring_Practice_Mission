package umc.domain.mission.entity.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name ="member_mission")

public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="is_completed")
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
