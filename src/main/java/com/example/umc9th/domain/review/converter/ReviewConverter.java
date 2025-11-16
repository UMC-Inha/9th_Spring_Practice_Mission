package com.example.umc9th.domain.review.converter;

import com.example.umc9th.domain.review.dto.res.ReviewResDTO;
import com.example.umc9th.domain.review.entity.Review;

import java.util.List;

import static com.example.umc9th.domain.review.dto.res.ReviewResDTO.*;

public class ReviewConverter {

    public static List<ReviewDTO> toDTOList(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewConverter::toDTO)
                .toList();
    }


    private static ReviewResDTO.ReviewDTO toDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .store(review.getStore().getId())
                .content(review.getContent())
                .star(review.getStar())
                .createdDate(review.getCreatedAt().toLocalDate())
                .build();
    }

}
