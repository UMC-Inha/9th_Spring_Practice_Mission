package umc.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.food.enums.FoodName;
import umc.domain.member.entity.mapping.MemberFood;
import umc.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Getter
@Table(name = "food")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Food extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private FoodName name;

    @OneToMany(mappedBy = "food")
    private List<MemberFood> memberFoods = new ArrayList<>();


}
