package umc.domain.review.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

    public record ReviewCreateDTO(
            String content,
            BigDecimal starRating,
            List<String> photos
    ) {
    }
}
