package umc.domain.review.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="reviews_comments")
public class ReviewComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id")
    private Review review;

    @Column(nullable = false)
    private String content;

    //추후에 사장 id 추가 가능
}
