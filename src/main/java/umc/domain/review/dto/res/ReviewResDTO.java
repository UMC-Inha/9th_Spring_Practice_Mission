package umc.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateResDTO(
            Long reviewId,
            LocalDateTime createAt
    ){}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Integer grade,
            String content,
            LocalDate createdAt
    ){}

    @Builder
    public record MemberReviewDTO(
            String memberName,
            String storeName,
            Integer grade,
            String content,
            LocalDate createdAt
    ){}
}
