package umc.domain.user.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.food.entity.Food;
import umc.domain.user.entity.User;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="user_foods")
public class UserFood extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @JoinColumn(name = "food_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;
}
