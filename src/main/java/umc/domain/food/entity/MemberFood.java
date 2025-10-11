package umc.domain.food.entity;

import jakarta.persistence.*;
import umc.domain.user.entity.Member;

public class MemberFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;
}
