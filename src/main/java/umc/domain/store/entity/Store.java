package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.food.entity.Food;
import umc.domain.region.entity.Region;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="stores")
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="region_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Region region;

    @JoinColumn(name="food_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="owner_code")
    private String ownerCode;

}
