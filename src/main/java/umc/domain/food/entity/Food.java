package umc.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.store.entity.Store;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_name", nullable = false,  length = 50)
    private String foodName;

    @Builder.Default
    @OneToMany(mappedBy = "food")
    private List<Store> stores = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoods = new ArrayList<>();
}
