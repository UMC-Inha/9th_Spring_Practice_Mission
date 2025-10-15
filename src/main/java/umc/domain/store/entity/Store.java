package umc.domain.store.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.domain.food.entity.Food;
import umc.domain.mission.entity.Mission;
import umc.domain.region.entity.Region;
import umc.domain.review.entity.Review;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",  nullable = false, length = 50)
    private String name;

    @Column(name = "info",  nullable = false, length = 1000)
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Officetime> officetimes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Mission> missions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;
}
