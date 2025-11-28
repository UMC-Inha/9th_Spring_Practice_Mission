package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {
    @Builder
    public static class ReviewDTO {
        private Long reviewId;
        private Long storeId;
        private String content;
        private Float star;
        private LocalDate createdDate;
    }

    // 리뷰의 정보
    @Builder
    public record ReviewPreViewListDTO(
           List<ReviewPreViewDTO> reviewList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {}

    @Builder
    public record ReviewPreViewDTO(
            String ownerNickname,
            Float score,
            String body,
            LocalDate createAt
    ) {}
}
