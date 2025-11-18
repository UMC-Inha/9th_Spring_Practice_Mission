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
}
