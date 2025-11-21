package com.example.umc9th.domain.review.dto.res;

import lombok.Builder;

import java.time.LocalDate;

public class ReviewResDTO {
    @Builder
    public static class ReviewDTO {
        private Long reviewId;
        private Long storeId;
        private String content;
        private Float star;
        private LocalDate createdDate;
    }
}
