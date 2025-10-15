package umc.domain.photo.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.inquiry.entity.Inquiry;
import umc.domain.member.entity.Member;
import umc.domain.photo.enums.photoType;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;
import umc.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "photo")
public class Photo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "photo_type",  nullable = false)
    @Enumerated(EnumType.STRING)
    private photoType photoType;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;


}
