package umc.domain.review.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    public record SearchDTO(

            Long reviewId,
            String storeName,
            BigDecimal star,
            String content,
            LocalDateTime createdAt

    ) {
    }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            BigDecimal score,
            String body,
            LocalDate createdAt
    ){}
}
