package umc.domain.review.dto.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record AddReviewDTO(
            @NotNull
            Long storeId,

            @NotNull
            @Min(value = 0, message = "별점은 0점 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
            Integer rating,

            String content
    ){}
}
