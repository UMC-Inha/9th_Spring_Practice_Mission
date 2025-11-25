package umc.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReviewResDTO {

    public record SearchDTO(

            Long reviewId,
            String storeName,
            BigDecimal star,
            String content,
            LocalDateTime createdAt

    ) {
    }
}
