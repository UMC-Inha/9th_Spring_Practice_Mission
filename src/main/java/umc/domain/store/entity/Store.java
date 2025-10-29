package umc.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.review.entity.Review;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id") // 단방향 매핑 관계 설정
    private Region region;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name ="address", nullable = false, length = 255)
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();
}
