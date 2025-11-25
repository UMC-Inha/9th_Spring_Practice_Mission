package umc.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record ReviewCreateDTO(
            Long reviewId,
            String content,
            BigDecimal starRating,
            List<String> photoUrls
    ) {
    }
}
