package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long store;
    private String content;
    private Float star;
    private LocalDate createdDate;
}
