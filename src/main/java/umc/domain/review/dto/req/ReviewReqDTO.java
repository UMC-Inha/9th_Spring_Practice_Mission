package umc.domain.review.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import umc.global.annotation.ExistStore;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            Long memberId,
            @NotNull(message = "평점은 필수 값입니다.")
            @Min(value = 1, message = "평점은 1점 이상이어야 합니다.")
            @Max(value = 5, message = "평점은 5점 이하여야 합니다.")
            Integer grade,
            String content,
            @ExistStore
            Long storeId
    ){}
}
