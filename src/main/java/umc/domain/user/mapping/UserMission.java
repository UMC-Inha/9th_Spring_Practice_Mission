package umc.domain.user.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.mission.entity.Mission;
import umc.domain.user.entity.User;
import umc.domain.user.enums.MissionStatus;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="user_missions")
public class UserMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name="mission_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Mission mission;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}
