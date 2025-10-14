package umc.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.user.enums.PointType;
import umc.domain.user.mapping.UserMission;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="point_histories")
public class PointHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id",nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @JoinColumn(name="user_mission_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private UserMission userMission;

    @Column(name="type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PointType type;

    @Column(name="point_amount",nullable = false)
    private Integer pointAmount;

}
