package umc.domain.review.service;

import com.querydsl.core.types.OrderSpecifier;

import static umc.domain.review.entity.QReview.review;

public enum ReviewSort {
    CREATED_DESC,   // 최신순
    STAR_DESC,      // 별점 높은순
    STAR_ASC;       // 별점 낮은순

    public OrderSpecifier<?>[] toOrders() {
        return switch (this) {
            case CREATED_DESC -> new OrderSpecifier[]{
                    review.createdAt.desc(),
                    review.id.desc()
            };
            case STAR_DESC -> new OrderSpecifier[]{
                    review.star.desc(),
                    review.id.desc()
            };
            case STAR_ASC -> new OrderSpecifier[]{
                    review.star.asc(),
                    review.id.desc()
            };
        };
    }
}