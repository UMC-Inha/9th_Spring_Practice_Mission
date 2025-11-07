package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.mission.entity.Mission;
import umc.domain.review.entity.Review;
import umc.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor


public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="name",nullable = false)
    private String name;

    @Column(name = "manager_number",nullable = false)
    private Long managerNumber;

    @Column(name = "detail_address",nullable = false)
    private String detailAddress;

    @OneToMany(mappedBy = "store")
    private List<Mission> missions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();



}
