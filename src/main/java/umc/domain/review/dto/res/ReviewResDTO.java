package umc.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateResDTO(
            Long reviewId,
            LocalDateTime createAt
    ){}
}
