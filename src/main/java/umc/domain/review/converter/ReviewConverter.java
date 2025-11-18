package umc.domain.review.converter;

import umc.domain.member.entity.Member;
import umc.domain.review.dto.req.ReviewReqDTO;
import umc.domain.review.dto.res.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;
//리뷰 작성 컨버터
public class ReviewConverter {
    // DTO -> 엔티티
    public static Review toReview(
            ReviewReqDTO.CreateReview req,
            Member member,
            Store store
    ) {
        return Review.createReview(
                req.getContent(),
                req.getStar(),
                member,
                store
        );
    }

    // 엔티티 -> DTO
    public static ReviewResDTO.ReviewInfo toReviewInfo(Review review) {
        return ReviewResDTO.ReviewInfo.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .memberId(review.getMember().getId())
                .memberName(review.getMember().getName())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
