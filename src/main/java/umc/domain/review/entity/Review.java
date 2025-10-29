package umc.domain.review.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.Member;
import umc.domain.store.entity.Store;
import umc.global.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // 단방향 매핑 관계 설정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false) // 이건 양방향 매핑 관계로
    private Store store;

    @Builder.Default
    @Column(name = "star_rating", precision = 2, scale = 1, nullable = false)
    private BigDecimal star_rating = BigDecimal.ZERO;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReviewReply> reviewReplyList = new ArrayList<>();
}
