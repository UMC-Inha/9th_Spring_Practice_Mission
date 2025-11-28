package umc.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Builder
    public record ReviewPreviewDTO(
            String ownerNickname,
            BigDecimal starRating,
            String content,
            LocalDate createdAt
    ) {
    }

    @Builder
    public record ReviewPreviewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }
}
