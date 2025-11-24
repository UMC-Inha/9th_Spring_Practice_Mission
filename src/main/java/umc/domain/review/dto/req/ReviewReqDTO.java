package umc.domain.review.dto.req;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    @Builder
    public static class CreateReview {
        private String content;  // 리뷰 내용
        private Float star;    //별점
    }

    @Getter
    @Builder
    public static class MyReviewReqDTO{
        private Long storeId;
        private Integer minStar;
        private Integer maxStar;
        private umc.domain.review.service.ReviewSort sortKey;
    }
}
