package umc.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.store.entity.Store;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="missions")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @Column(name="reward_point", nullable = false)
    private Integer rewardPoint;

    @Column(name="base_amount",nullable = false)
    private Integer baseAmount;

    @Column(name="deadline",nullable = false)
    private LocalDate deadline;

    @Column(name="is_active",nullable = false)
    private Boolean isActive;


}
