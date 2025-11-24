package umc.domain.review.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewReqDTO {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReview {
        private String content;  // 리뷰 내용
        private Float star;    //별점
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewReqDTO{
        private Long storeId;
        private Integer minStar;
        private Integer maxStar;
        private umc.domain.review.service.ReviewSort sortKey;
    }
}
