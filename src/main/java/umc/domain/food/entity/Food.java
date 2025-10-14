package umc.domain.food.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.food.enums.FoodName;
import umc.global.entity.BaseEntity;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Table(name="foods")
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private FoodName name;

}