package umc.domain.review.dto;

import umc.domain.review.service.ReviewSort;


public record MyReviewQuery(
        Long storeId,
        Float star,       // 단일 별점
        Float minStar,    // 최소 별점
        Float maxStar,    // 최대 별점
        String sort
) {
    public ReviewSort sortKey() { return ReviewSort.from(sort); }
}
