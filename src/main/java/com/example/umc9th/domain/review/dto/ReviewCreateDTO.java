package com.example.umc9th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class ReviewCreateDTO {
    Long storeId;
    Float star;
    String content;
    String photo_url;
}
