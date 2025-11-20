package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.review.dto.ReviewCreateDTO;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.store.entity.Store;

public class ReviewCreateConverter {
    public static Review toReview(ReviewCreateDTO dto, Store store) {
        return Review.builder()
                .content(dto.getContent())
                .star(dto.getStar())
                .store(store)
                .build();
    }
}
