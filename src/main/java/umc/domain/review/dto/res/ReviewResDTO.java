package umc.domain.review.dto.res;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResDTO {

    @Getter
    @Builder
    public static class ReviewInfo {
        private Long reviewId;
        private String content;
        private Float star;

        private Long memberId;
        private String memberName;

        private Long storeId;
        private String storeName;

        private LocalDateTime createdAt;
    }

    public record MyReviewResDTO(
            Long reviewId,
            Long storeId,
            String storeName,
            Float star,
            String content,
            LocalDateTime createdAt
    ) {}
}
